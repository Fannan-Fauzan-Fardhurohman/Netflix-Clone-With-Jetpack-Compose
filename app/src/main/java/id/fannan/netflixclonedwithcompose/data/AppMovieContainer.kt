package id.fannan.netflixclonedwithcompose.data

import id.fannan.netflixclonedwithcompose.data.remote.RemoteDataSource
import id.fannan.netflixclonedwithcompose.data.remote.network.MovieService
import id.fannan.netflixclonedwithcompose.domain.repository.MovieRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppMovieContainer {
    val remoteDataSource: RemoteDataSource
    val movieRepository: MovieRepository
}

class DefaultAppMovieContainer : AppMovieContainer {
    private val BASE_URL = "https://api.themoviedb.org/3/"

    //    Print response json
    private val interceptor: OkHttpClient
        get() {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(interceptor)
        .build()

    private val movieService: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }

    override val remoteDataSource: RemoteDataSource
            by lazy {
                RemoteDataSource(movieService)
            }
    override val movieRepository: MovieRepository
            by lazy {
                MovieRepository(remoteDataSource)
            }

}