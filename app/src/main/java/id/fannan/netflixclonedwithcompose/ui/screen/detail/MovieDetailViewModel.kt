package id.fannan.netflixclonedwithcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.fannan.netflixclonedwithcompose.MovieApplication
import id.fannan.netflixclonedwithcompose.domain.model.Movie
import id.fannan.netflixclonedwithcompose.domain.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> get() = _movie

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication
                val repository = application.appMovieContainer.movieRepository
                MovieDetailViewModel(
                    repository
                )
            }
        }
    }

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            movieRepository.getMovieDetail(id).collect {
                _movie.value = it
            }
        }
    }

}