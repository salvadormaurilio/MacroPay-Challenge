package com.example.macropay.domain

import com.example.macropay.data.AuthRepository
import javax.inject.Inject

class IsActiveSessionUseCase @Inject constructor(private val authRepository: AuthRepository) {

    fun isActiveSession() = authRepository.isActiveSession()
}