package com.gubadev.injectproject.dagger

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gubadev.injectproject.rest.GithubRepositoriesService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule(val baseUri: String) {


    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache {
        val cacheSize: Long = 15 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUri)
                .client(client)
                .build()
    }


    @Provides
    @Singleton
    fun provideGithubService(retrofit: Retrofit) : GithubRepositoriesService {
        return retrofit.create(GithubRepositoriesService::class.java)
    }


}