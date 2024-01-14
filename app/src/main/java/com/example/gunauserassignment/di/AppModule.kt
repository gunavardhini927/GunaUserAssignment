package com.example.gunauserassignment.di

import com.example.gunauserassignment.data.AuthRepository
import com.example.gunauserassignment.data.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideFireBaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}