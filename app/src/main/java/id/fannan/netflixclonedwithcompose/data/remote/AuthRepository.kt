package id.fannan.netflixclonedwithcompose.data.remote

import id.fannan.netflixclonedwithcompose.data.local.LocalDataSource
import id.fannan.netflixclonedwithcompose.domain.model.User
import id.fannan.netflixclonedwithcompose.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepository(
    private val localDataSource: LocalDataSource
) : IAuthRepository {
    override suspend fun login(email: String, password: String): Flow<List<User>> {
        return localDataSource.loginUser(email, password)
    }

    override suspend fun register(user: User): Flow<User> {
        return localDataSource.registerUser(user)
    }

    override suspend fun getIsLoggedIn(): Flow<Boolean> {
        return localDataSource.isLoggedIn()
    }

    override suspend fun storeEmail(email: String) {
        localDataSource.storeEmail(email)
    }
}