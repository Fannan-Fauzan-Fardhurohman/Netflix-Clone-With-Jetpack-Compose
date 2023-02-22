package id.fannan.netflixclonedwithcompose.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.fannan.netflixclonedwithcompose.data.MovieDatasource
import id.fannan.netflixclonedwithcompose.domain.model.Movie

class MovieViewModel constructor(
    private val dataSource: MovieDatasource
) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MovieViewModel(
                    MovieDatasource
                )
            }
        }
    }

    fun getMovies(keyword: String) {
        _movies.value = dataSource.getNowPlayingMovie().filter { movie ->
            movie.title.contains(keyword, true) || movie.description.contains(keyword, true)
        }
    }
}