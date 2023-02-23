package id.fannan.netflixclonedwithcompose.domain.repository

import id.fannan.netflixclonedwithcompose.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getNowPlayingMovie(): Flow<List<Movie>>
    suspend fun getMovieDetail(id: String): Flow<Movie>
}