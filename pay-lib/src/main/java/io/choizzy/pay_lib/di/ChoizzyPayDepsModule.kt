package io.choizzy.pay_lib.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.choizzy.pay_lib.api.ChoizzyApi
import io.choizzy.pay_lib.repository.ChoizzyRepository
import io.choizzy.pay_lib.usecase.PortfolioUseCase
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val ENVIRONMENT = "https://choizzy.io/api/"

@Module
class ChoizzyPayDepsModule {

    @Provides
    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ENVIRONMENT)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    @Provides
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .retryOnConnectionFailure(true).build()
    }

    @Provides
    fun provideChoizzyApi(retrofit: Retrofit): ChoizzyApi {
        return retrofit.create(ChoizzyApi::class.java)
    }

    @Provides
    fun provideJson(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}