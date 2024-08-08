package com.egeninsesi.news.di

import android.content.Context
import com.egeninsesi.news.data.network.ApiService
import com.egeninsesi.news.data.network.RetrofitClient
import com.egeninsesi.news.data.repository.Repository
import com.egeninsesi.news.domain.usecase.MainActivityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.apiService
    }

    @Provides
    @Singleton
    fun provideMainActivityUseCase(repository: Repository): MainActivityUseCase {
        return MainActivityUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        return Repository(apiService)
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}