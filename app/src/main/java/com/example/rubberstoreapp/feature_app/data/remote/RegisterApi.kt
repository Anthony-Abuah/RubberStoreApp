package com.example.rubberstoreapp.feature_app.data.remote

import com.example.rubberstoreapp.feature_app.data.remote.dto.RegistrationInfoDto
import com.example.rubberstoreapp.feature_app.domain.model.UserRegistrationResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RegisterApi {

    @GET("/register.php")
    suspend fun registerUser(
    ): UserRegistrationResponse


/*
    @POST("/register.php")
    suspend fun registerUser(@Body request: RegistrationInfoDto
    ): UserRegistrationResponse
*/

}