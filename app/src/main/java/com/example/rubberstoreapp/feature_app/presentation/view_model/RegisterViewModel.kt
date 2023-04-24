package com.example.rubberstoreapp.feature_app.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubberstoreapp.core.util.Resource
import com.example.rubberstoreapp.feature_app.domain.use_cases.RegisterUserUseCase
import com.example.rubberstoreapp.feature_app.presentation.view_model.states.RegisterResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUser: RegisterUserUseCase
):ViewModel() {

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password


    private val _registerResponse = mutableStateOf(RegisterResponseState(false))
    val registerResponse: State<RegisterResponseState> = _registerResponse

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var registerJob: Job? = null


    fun register(
        name: String,
        email: String,
        password: String
    ) {

        /*_name.value = name
        _email.value = email
        _password.value = password*/

        registerJob = viewModelScope.launch{

            registerUser()
                .onEach { result->
                    when(result){
                        is Resource.Success ->{
                            _registerResponse.value = registerResponse.value.copy(
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(
                                result.message ?: "Successfully Connected"
                            ))
                        }
                        is Resource.Error ->{
                            _registerResponse.value = registerResponse.value.copy(
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(
                                result.message ?: "Unknown Error"
                            ))
                        }
                        is Resource.Loading->{
                            _registerResponse.value = registerResponse.value.copy(
                                isLoading = true
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(
                                result.message ?: "Getting the Request"
                            ))
                        }
                    }
                }.launchIn(this)

            /*
            registerUser(name, email, password)
                .onEach { result->
                    when(result){
                        is Resource.Success ->{
                            _registerResponse.value = registerResponse.value.copy(
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(
                                result.message ?: "Successfully Registered"
                            ))
                        }
                        is Resource.Error ->{
                            _registerResponse.value = registerResponse.value.copy(
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(
                                result.message ?: "Unknown Error"
                            ))
                        }
                        is Resource.Loading->{
                            _registerResponse.value = registerResponse.value.copy(
                                isLoading = true
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(
                                result.message ?: "Sending User Details"
                            ))
                        }
                    }
                }.launchIn(this)
            */

        }
    }




    sealed class UIEvent{
        data class ShowSnackBar(val message: String): UIEvent()
    }
}