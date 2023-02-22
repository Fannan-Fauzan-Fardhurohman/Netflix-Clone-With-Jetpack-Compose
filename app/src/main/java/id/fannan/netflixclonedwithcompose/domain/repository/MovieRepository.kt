package id.fannan.netflixclonedwithcompose.domain.repository

import id.fannan.netflixclonedwithcompose.data.remote.RemoteDataSource
import id.fannan.netflixclonedwithcompose.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository {
    override suspend fun getNowPlayingMovie(): Flow<List<Movie>> =
        remoteDataSource.getNowPlayingMovie()
}