package com.example.rubberstoreapp.feature_app.domain.model

import com.example.rubberstoreapp.feature_app.data.remote.dto.RegistrationInfoDto

data class RegistrationInfo(
    val name: String,
    val email: String,
    val password: String
){
    fun toRegistrationInfoDto(): RegistrationInfoDto{
        return RegistrationInfoDto(
            name = name,
            email = email,
            password = password
        )
    }
}
