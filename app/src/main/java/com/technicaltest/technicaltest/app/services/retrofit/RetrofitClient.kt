package com.technicaltest.technicaltest.app.services.retrofit

import com.technicaltest.technicaltest.BuildConfig
import com.technicaltest.technicaltest.dataSources.meep.Interfaces.MeetApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun provideRetrofitClientMeetApi(): MeetApi {
        val httpClientBuilder = OkHttpClient.Builder().cache(null)
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MEET_API_BASE_URL)
            .client(httpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create<MeetApi>(
            MeetApi::class.java)
    }
}