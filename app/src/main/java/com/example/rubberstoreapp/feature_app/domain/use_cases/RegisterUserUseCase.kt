package com.example.rubberstoreapp.feature_app.domain.use_cases


import com.example.rubberstoreapp.core.util.Resource
import com.example.rubberstoreapp.feature_app.domain.model.UserRegistrationResponse
import com.example.rubberstoreapp.feature_app.domain.repository.UserRegistrationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase(
    private val repository: UserRegistrationRepository
) {
    /*
    operator fun invoke(name: String, email: String, password:  String): Flow<Resource<UserRegistrationResponse>>{

        if (name.isBlank() || email.isBlank() || password.isBlank()){
            return flow {  }
        }

        return repository.registerUser(name, email, password)
    }
    */
    operator fun invoke(): Flow<Resource<UserRegistrationResponse>>{

        return repository.registerUser()
    }
}