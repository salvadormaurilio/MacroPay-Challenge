package com.example.macropay.data

import com.example.macropay.data.datasource.remote.AuthRemoteDataSource
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class AuthRepository @Inject constructor(private val authRemoteDataSource: AuthRemoteDataSource) {

    fun isActiveSession(): Boolean = authRemoteDataSource.isActiveSession()

    fun signIn(email: String, password: String): Flow<Result<String>> = authRemoteDataSource.signIn(email, password)

    fun logOut() = authRemoteDataSource.logOut()
}
