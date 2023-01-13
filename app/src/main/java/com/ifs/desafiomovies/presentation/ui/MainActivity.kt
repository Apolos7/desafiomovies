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
        binding.listMovies.rvMovies.adapter = moviesSimilarListAdapter
        mainViewModel.getMovieDetail()
        lifecycle(mainViewModel.uiState, ::handleGetMovie)
        observe(mainViewModel.movieData, ::handleMovie)
    }
    private fun handleGetMovie(uiState: UiState) {
        when (uiState) {

        }
    }
    private fun handleMovie(movieItemUiState: Movie){
        setupMovieDetail(movieItemUiState)
        setupListener()
        handleGetSimilarMovies()
    }


    private fun setupMovieDetail(movie: Movie){

    }

    private fun setupListener(){

    }

    private fun handleGetSimilarMovies() {

    }

}