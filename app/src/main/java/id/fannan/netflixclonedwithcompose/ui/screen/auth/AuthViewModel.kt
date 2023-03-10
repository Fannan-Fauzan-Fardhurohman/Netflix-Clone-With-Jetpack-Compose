package id.fannan.netflixclonedwithcompose.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.fannan.netflixclonedwithcompose.MovieApplication
import id.fannan.netflixclonedwithcompose.data.remote.AuthRepository
import id.fannan.netflixclonedwithcompose.domain.model.User
import id.fannan.netflixclonedwithcompose.ui.MainViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _userLogin = MutableStateFlow<User?>(null)
    val userLogin: StateFlow<User?> = _userLogin

    private val _userRegister = MutableStateFlow<User?>(null)
    val userRegister: StateFlow<User?> = _userRegister


    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect { users ->
                if (users.isNotEmpty()) {
                    _userLogin.value = users[0]
                }
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            authRepository.register(User(name = "", email = email, password = password))
                .collect { user ->
                    _userRegister.value = user
                }
        }
    }

    fun storeEmail(email: String) {
        viewModelScope.launch {
            authRepository.storeEmail(email)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication
                val repository = application.appMovieContainer.authRepository
                AuthViewModel(
                    repository
                )
            }
        }
    }

}