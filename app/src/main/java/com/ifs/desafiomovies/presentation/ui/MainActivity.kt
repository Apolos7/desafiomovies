package com.ifs.desafiomovies.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ifs.desafiomovies.presentation.viewmodels.MainViewModel
import com.ifs.desafiomovies.R
import com.ifs.desafiomovies.data.exception.ResponseError
import com.ifs.desafiomovies.databinding.MainActivityBinding
import com.ifs.desafiomovies.domain.model.Movie
import com.ifs.desafiomovies.presentation.extensions.*
import com.ifs.desafiomovies.presentation.mapper.exception
import com.ifs.desafiomovies.presentation.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val progressDialog by lazy { createProgressDialog() }
    private lateinit var binding: MainActivityBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val moviesSimilarListAdapter by lazy { MovieSimilarListAdapter() }
    private var voteCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.listMovies.adapter = moviesSimilarListAdapter
        binding.listMovies.rvMovies.adapter = moviesSimilarListAdapter
        mainViewModel.getMovieDetail()
        lifecycle(mainViewModel.uiState, ::handleGetMovie)
        observe(mainViewModel.movieData, ::handleMovie)
    }

    private fun handleGetMovie(uiState: UiState) {
        when (uiState) {
            // Mostrar os dados na tela
            is UiState.Success -> {
                progressDialog.dismiss()
            }
            // Mostrar dialog de carregamento
            is UiState.Loading -> {
                progressDialog.show()
            }
            // Travar o aplicativo
            is UiState.Failure -> {
                progressDialog.dismiss()
                createDialog {
                    setMessage(exception(uiState.exception))
                }.show()
            }
            else -> Unit
        }
    }


    private fun handleMovie(movieItemUiState: Movie) {
        setupMovieDetail(movieItemUiState)
        setupListener()
        handleGetSimilarMovies()
    }


    /* *
    * Recebe o filme e seta todos os seus dados na tela
    * */
    private fun setupMovieDetail(movie: Movie) {
        voteCount = movie.vote_count
        if (mainViewModel.isFavorite()) {
            voteCount++
            binding.favorite()
        } else {
            binding.disfavor()
        }

        // Atualizando o titulo, contador votos e popularidade do filme na tela
        binding.tvMovieName.text = movie.title
        binding.favCount.text = voteCount.toString()
        binding.visualizeCount.text = movie.popularity.toString()

        // Glide é responsável por carregar o poster do filme na tela
        Glide.with(binding.root.context)
            .load(movie.poster_path)
            .placeholder(R.drawable.photo_load)
            .fallback(R.drawable.broken_image)
            .into(binding.ivMoviePosterLg)
    }

    private fun setupListener() {
        binding.btnFavorita.setOnClickListener {
            println(mainViewModel.isFavorite())
            if (mainViewModel.isFavorite()) {
                voteCount--
                binding.favCount.text = voteCount.toString()
                mainViewModel.disfavor()
                binding.disfavor() // Muda a imagem do botão para o coração vazio
            } else {
                voteCount++
                mainViewModel.favorite()
                binding.favCount.text = voteCount.toString()
                binding.favorite() // Muda a imagem do botão para o coração cheio
            }
        }
    }

    private fun handleGetSimilarMovies() {
        lifecycleScope.launch{
            mainViewModel.moviesSimilarPaging().collectLatest { response ->
                moviesSimilarListAdapter.submitData(response)
            }
        }
    }

}