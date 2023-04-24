package com.example.rubberstoreapp.feature_app.di

import com.example.rubberstoreapp.core.util.Constants
import com.example.rubberstoreapp.feature_app.data.remote.RegisterApi
import com.example.rubberstoreapp.feature_app.data.repository.UserRegistrationRepositoryImpl
import com.example.rubberstoreapp.feature_app.domain.repository.UserRegistrationRepository
import com.example.rubberstoreapp.feature_app.domain.use_cases.RegisterUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserAuthenticationModule {

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(repository: UserRegistrationRepository): RegisterUserUseCase{
        return RegisterUserUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideUserRegistrationRepository(api: RegisterApi): UserRegistrationRepository{
        return UserRegistrationRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideRegisterApi(): RegisterApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.client(OkHttpClient.Builder().build())
            .build()
            .create(RegisterApi::class.java)
    }



}