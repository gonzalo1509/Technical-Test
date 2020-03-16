package com.technicaltest.technicaltest.app.services.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public DSPIdentityApi provideRetrofitClientDspIdentity() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().cache(null);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.DSP_IDENTITY_BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(DSPIdentityApi.class);
    }

    public DSPOauthApi provideRetrofitClientDspOauth() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().cache(null);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.DSP_OAUTH_BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(DSPOauthApi.class);
    }

    public DSPIdpApi provideRetrofitClientIdp() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().cache(null);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.DSP_IDP_BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(DSPIdpApi.class);
    }

    public DSPApi provideRetrofitClientDspApi() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().cache(null);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(httpLoggingInterceptor);
        httpClientBuilder.addInterceptor(new RetrofitInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.DSP_API_BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(DSPApi.class);
    }
}
