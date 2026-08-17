package com.rodrigo.domain.usecase

import com.rodrigo.domain.repository.AuthRepository
import javax.inject.Inject

class SingInGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Boolean>{
        return authRepository.signInWithGoogle()
    }
}