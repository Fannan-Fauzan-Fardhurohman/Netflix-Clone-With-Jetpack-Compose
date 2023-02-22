package id.fannan.netflixclonedwithcompose.data.remote

import android.util.Log
import id.fannan.netflixclonedwithcompose.data.remote.network.MovieService
import id.fannan.netflixclonedwithcompose.data.remote.response.toListMovie
import id.fannan.netflixclonedwithcompose.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val movieService: MovieService
) {
    suspend fun getNowPlayingMovie() = flow {
        movieService.getNowPlaying().toListMovie().let {
            emit(it)
        }
    }.catch {
        Log.d("RemoteDataSource", "getNowPlayingMovie:Failed = ${it.message}")
    }.flowOn(Dispatchers.IO)
}