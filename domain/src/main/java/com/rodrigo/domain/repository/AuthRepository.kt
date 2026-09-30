package com.rodrigo.domain.repository

import com.rodrigo.domain.model.User
import javax.naming.Context

interface AuthRepository {
    suspend fun signInWithGoogle(): Result<Boolean>
    suspend fun signOut()
    fun isUserLoggedIn(): Boolean
    suspend fun getLoggedUser(): Result<User?>
}