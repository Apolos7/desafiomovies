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

    /*
    * Chama o service que faz a requisição na API por meio do retrofit
    * Com base no conteudo da response, é retornado um Either de erro ou sucesso com o filme
    * */
    override suspend fun getMovie(): Either<Movie, Exception> {
        return try{
            val result = movieService.getMovie()
            Either.Success(result.toData())
        }
        catch (e:Exception){
            Either.Failure(ResponseError.IOErrorException)
        }
    }

    /* *
    * Busca todos os gêneros do filme na API
    * */
    override suspend fun getAllGenres(): Either<List<Genre>, Exception> {
        return try{
            val result = movieService.getAllGenres()
            val listaGeneros: List<Genre> = result.genres.map { it.toData() }
            Either.Success(listaGeneros)
        }
        catch (e:Exception){
            Either.Failure(ResponseError.IOErrorException)
        }
    }
}