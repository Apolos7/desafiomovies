package com.ifs.desafiomovies.data.remote.apidatasource.retrofit


import com.ifs.desafiomovies.core.Either
import com.ifs.desafiomovies.data.exception.ResponseError
import com.ifs.desafiomovies.data.remote.model.MovieResponse
import com.ifs.desafiomovies.data.remote.model.ResultGenreResponse
import com.ifs.desafiomovies.data.remote.apidatasource.MovieApiDataSource
import com.ifs.desafiomovies.data.remote.helper.safeCall
import com.ifs.desafiomovies.data.remote.service.MovieService
import com.ifs.desafiomovies.domain.model.Genre
import com.ifs.desafiomovies.domain.model.Movie
import javax.inject.Inject

class MovieApiDataSourceRetro @Inject constructor(
    private val movieService: MovieService
) : MovieApiDataSource {

    // TODO: Finalizar retrofit posteriormente

    override suspend fun getMovie(): Either<Movie, Exception> {
        return Either.Success(Movie(1056735, "Minecraft: The Movie", 4.8, 987, "19/01/2023", "/qYzI2mKmj8mG54l1nhUunZnjiKw.jpg"))
    }

    override suspend fun getAllGenres(): Either<List<Genre>, Exception>{
        val listaGeneros: List<Genre> = listOf(Genre(1,"Filme"))
        return Either.Success(listaGeneros)
    }
}