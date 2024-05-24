package com.example.macropay.di

import com.example.macropay.core.coroutines.CoroutinesDispatchers
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideCoroutinesDispatchers() = CoroutinesDispatchers()

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor = Interceptor { chain: Interceptor.Chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header(AUTHORIZATION_HEADER, AUTHENTICATION_VALUE)
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(okHttpInterceptor: Interceptor): OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(okHttpInterceptor)
        .addInterceptor(HttpLoggingInterceptor())

    @Provides
    @Singleton
    fun provideOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient = okHttpClientBuilder.build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(okHttpClient)

    private const val AUTHORIZATION_HEADER = "Authorization"
    private const val AUTHENTICATION_VALUE =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMDgyMzkzNDQzODA3NWQ2M2YxZGJkYTQwMjNlNzZmYyIsInN1YiI6IjY1MDBmNzJkNTU0NWNhMDExYmE2N2RkYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4QxbpZq9Tj3uzhA8uv2qLNcCA7NIcGBHDzoC4bWv9t8"
}
