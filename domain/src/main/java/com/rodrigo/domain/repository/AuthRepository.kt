package com.rodrigo.domain.repository

import javax.naming.Context

interface AuthRepository {
    suspend fun signInWithGoogle(): Result<Boolean>
    suspend fun signOut()
    fun isUserLoggedIn(): Boolean
}