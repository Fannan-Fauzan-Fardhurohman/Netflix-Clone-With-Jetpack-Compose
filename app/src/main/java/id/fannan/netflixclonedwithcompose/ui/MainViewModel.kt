package id.fannan.netflixclonedwithcompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.fannan.netflixclonedwithcompose.MovieApplication
import id.fannan.netflixclonedwithcompose.data.remote.AuthRepository
import id.fannan.netflixclonedwithcompose.ui.screen.detail.MovieDetailViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> get() = _isLoggedIn

    private val _isSplash = MutableStateFlow(false)
    val isSplash get() = _isSplash
    fun getIsLoggiedInUser() {
        viewModelScope.launch {
            authRepository.getIsLoggedIn().collect {
                _isLoggedIn.value = it
            }
        }
    }

    fun setIsSplash(boolean: Boolean){
        viewModelScope.launch {
            _isSplash.value = boolean
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication
                val repository = application.appMovieContainer.authRepository
                MainViewModel(
                    repository
                )
            }
        }
    }

}