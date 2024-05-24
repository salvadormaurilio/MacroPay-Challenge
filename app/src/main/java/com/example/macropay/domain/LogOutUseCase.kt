package com.example.macropay.domain

import com.example.macropay.data.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun logOut() = authRepository.logOut()
}