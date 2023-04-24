package com.example.rubberstoreapp.feature_app.domain.repository

import com.example.rubberstoreapp.core.util.Resource
import com.example.rubberstoreapp.feature_app.data.remote.dto.RegistrationInfoDto
import com.example.rubberstoreapp.feature_app.domain.model.UserRegistrationResponse
import kotlinx.coroutines.flow.Flow

interface UserRegistrationRepository {

    fun registerUser(): Flow<Resource<UserRegistrationResponse>>
    //fun registerUser(name: String, email: String, password: String): Flow<Resource<UserRegistrationResponse>>
}