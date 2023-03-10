package id.fannan.netflixclonedwithcompose.data

import android.content.Context
import id.fannan.netflixclonedwithcompose.data.local.LocalDataSource
import id.fannan.netflixclonedwithcompose.data.local.datastore.MovieDataStore
import id.fannan.netflixclonedwithcompose.data.local.room.MovieDatabase
import id.fannan.netflixclonedwithcompose.data.remote.AuthRepository
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
    val localDataSource: LocalDataSource
    val authRepository: AuthRepository
}

class DefaultAppMovieContainer(
    private val context: Context
) : AppMovieContainer {
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

    private val movieDatabase: MovieDatabase by lazy {
        MovieDatabase.getInstance(context)
    }

    private val dataStore: MovieDataStore by lazy {
        MovieDataStore(context)
    }

    override val remoteDataSource: RemoteDataSource
            by lazy {
                RemoteDataSource(movieService)
            }
    override val movieRepository: MovieRepository
            by lazy {
                MovieRepository(remoteDataSource)
            }

    override val localDataSource: LocalDataSource by lazy {
        LocalDataSource(movieDatabase.userDao(), dataStore)
    }

    override val authRepository: AuthRepository by lazy {
        AuthRepository(localDataSource)
    }

}