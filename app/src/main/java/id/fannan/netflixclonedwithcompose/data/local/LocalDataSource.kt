package id.fannan.netflixclonedwithcompose.data.local

import android.util.Log
import id.fannan.netflixclonedwithcompose.data.local.datastore.MovieDataStore
import id.fannan.netflixclonedwithcompose.data.local.room.dao.UserDao
import id.fannan.netflixclonedwithcompose.data.local.room.entity.toUser
import id.fannan.netflixclonedwithcompose.domain.model.User
import id.fannan.netflixclonedwithcompose.domain.model.toUserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class LocalDataSource constructor(
    private val userDao: UserDao,
    private val movieDataStore: MovieDataStore
) {

    suspend fun loginUser(email: String, password: String) = flow {
        emitAll(
            userDao.getUserByEmailAndPassword(email, password).map {
                it.map {
                    it.toUser()
                }
            }
        )
    }.catch {
        Log.e("LocalDataSource", "LoginUser Failed = ${it.message}")

    }.flowOn(Dispatchers.IO)

    suspend fun registerUser(user: User) = flow {
        userDao.storeUser(user.toUserEntity())
        emit(user)
    }.catch {
        Log.e("LocalDataSource", "RegisterUser: Failed = ${it.message}")
    }.flowOn(Dispatchers.IO)


    suspend fun isLoggedIn() = flow {
        movieDataStore.email.collect {
            emit(it.isNotEmpty())
        }
    }.catch {
        Log.e("LocalDataSource", "isLoggedIn: failed=${it.message}")
    }.flowOn(Dispatchers.IO)

    suspend fun storeEmail(email: String) = movieDataStore.storeData(MovieDataStore.EMAIL, email)
}