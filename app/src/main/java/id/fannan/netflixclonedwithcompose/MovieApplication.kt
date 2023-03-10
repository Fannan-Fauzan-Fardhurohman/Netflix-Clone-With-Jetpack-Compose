package id.fannan.netflixclonedwithcompose

import android.app.Application
import id.fannan.netflixclonedwithcompose.data.AppMovieContainer
import id.fannan.netflixclonedwithcompose.data.DefaultAppMovieContainer

class MovieApplication : Application() {
    lateinit var appMovieContainer: AppMovieContainer
    override fun onCreate() {
        super.onCreate()
        appMovieContainer = DefaultAppMovieContainer(context = applicationContext)
    }




}