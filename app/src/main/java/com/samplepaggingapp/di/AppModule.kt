package com.samplepaggingapp.di

import com.samplepaggingapp.network.ApiService
import com.samplepaggingapp.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {
    @Provides
    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().retryOnConnectionFailure(false).build()
    }

    @Provides
    private fun provideRetrofit(
        okhttpClient: OkHttpClient
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


}