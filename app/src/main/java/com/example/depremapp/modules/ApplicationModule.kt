package com.example.depremapp.modules

import android.app.Application
import android.content.Context
import com.example.depremapp.api.DepremApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalSerializationApi
@InstallIn(SingletonComponent :: class)
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun context(application: Application): Context = application.applicationContext

    private  val BASE_URL = "https://turkiyedepremapi.herokuapp.com/"
    private  val API_KEY = "AIzaSyD34qvWmmqXkPRQfZdZJ25ITLfkMbTf3Bg"
    private  val connectionTimeout: Int = 10000

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(connectionTimeout.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    //.addQueryParameter("language", LANGUAGE)
                    .build()
                val requestBuilder = original.newBuilder()
                    .url(url)
                chain.proceed(requestBuilder.build())
            }
            .build()
    }
    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): DepremApi {
        return retrofit.create(DepremApi::class.java)
    }

}