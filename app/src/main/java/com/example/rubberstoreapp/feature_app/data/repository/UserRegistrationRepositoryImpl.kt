package com.example.rubberstoreapp.feature_app.data.repository

import com.example.rubberstoreapp.core.util.Resource
import com.example.rubberstoreapp.feature_app.data.remote.RegisterApi
import com.example.rubberstoreapp.feature_app.data.remote.dto.RegistrationInfoDto
import com.example.rubberstoreapp.feature_app.domain.model.RegistrationInfo
import com.example.rubberstoreapp.feature_app.domain.model.UserRegistrationResponse
import com.example.rubberstoreapp.feature_app.domain.repository.UserRegistrationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UserRegistrationRepositoryImpl(
    private val api: RegisterApi
): UserRegistrationRepository {


    /*
    override fun registerUser(
        name: String,
        email: String,
        password: String
    ): Flow<Resource<UserRegistrationResponse>> = flow {

        emit(Resource.Loading())

        try {
            val userInfo =  RegistrationInfo(name, email, password)
            val registerResponse = api.registerUser(RegistrationInfoDto(name, email, password))
            emit(Resource.Success(registerResponse))

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))

        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "Could not connect to the Database server"))

        }

    }
*/
    override fun registerUser(): Flow<Resource<UserRegistrationResponse>> = flow {

        emit(Resource.Loading())

        try {
            val registerResponse = api.registerUser()
            emit(Resource.Success(registerResponse))

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))

        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "Could not connect to the Database server"))

        }

    }
}