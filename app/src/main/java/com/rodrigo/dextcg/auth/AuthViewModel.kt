package com.rodrigo.dextcg.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigo.domain.model.User
import com.rodrigo.domain.usecase.GetLoggedUserUseCase
import com.rodrigo.domain.usecase.SingInGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val singInGoogleUseCase: SingInGoogleUseCase,
    private val getLoggedUserUseCase: GetLoggedUserUseCase,
): ViewModel() {

    var authState by mutableStateOf<AuthState>(AuthState.Idle)
        private set

    var currentUserState by mutableStateOf<UserState>(UserState.Idle)
        private set

    init {
        getLoggedUser()
    }

    fun singInWithGoogle() {
        viewModelScope.launch {
            authState = AuthState.Loading
            singInGoogleUseCase()
                .onSuccess { authState = AuthState.Success }
                .onFailure { authState = AuthState.Error(it.message?:"error") }
        }
    }

    fun getLoggedUser() {
        viewModelScope.launch {
            currentUserState = UserState.Loading
            getLoggedUserUseCase()
                .onSuccess {
                    currentUserState = if (it != null)
                        if (it.name.isEmpty()) UserState.EmptyUser
                        else UserState.Success(it)
                     else
                        UserState.Idle
                }
                .onFailure {
                    currentUserState = UserState.Error(
                        it.message ?: "Error getting logged user"
                    )
                }
        }
    }
}

sealed class AuthState{
    object Idle: AuthState()
    object Loading: AuthState()
    object Success: AuthState()
    data class Error(val message:String): AuthState()
}

sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    object EmptyUser : UserState()
    data class Success(val user: User) : UserState()
    data class Error(val message: String) : UserState()
}
