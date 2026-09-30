package com.rodrigo.domain.usecase

import com.rodrigo.domain.model.User
import com.rodrigo.domain.repository.AuthRepository
import javax.inject.Inject

class GetLoggedUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<User?>{
        return authRepository.getLoggedUser()
    }
}