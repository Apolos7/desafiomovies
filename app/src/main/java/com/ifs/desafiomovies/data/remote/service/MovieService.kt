package com.ifs.desafiomovies.data.remote.service

import com.ifs.desafiomovies.data.remote.model.MovieResponse
import com.ifs.desafiomovies.data.remote.model.ResultGenreResponse
import com.ifs.desafiomovies.data.remote.model.ResultMovieSimilarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    companion object{
        const val MOVIE_ID = 40096
    }

}