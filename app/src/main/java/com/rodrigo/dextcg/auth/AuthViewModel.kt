package com.rodrigo.dextcg.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigo.domain.usecase.SingInGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val singInGoogleUseCase: SingInGoogleUseCase): ViewModel() {

    var authState by mutableStateOf<AuthState>(AuthState.Idle)
        private set

    fun singInWithGoogle() {
        viewModelScope.launch {
            authState = AuthState.Loading
            singInGoogleUseCase()
                .onSuccess { authState = AuthState.Success }
                .onFailure { authState = AuthState.Error(it.message?:"error") }
        }
    }
}

sealed class AuthState{
    object Idle: AuthState()
    object Loading: AuthState()
    object Success: AuthState()
    data class Error(val message:String): AuthState()
}
