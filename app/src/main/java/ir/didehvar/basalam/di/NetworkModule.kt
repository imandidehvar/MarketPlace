package ir.didehvar.basalam.di

import android.os.Environment
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ir.didehvar.basalam.data.remote.API
import ir.didehvar.basalam.util.Constants
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    @Named("cached")
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .cache(Cache(Environment.getDownloadCacheDirectory(), 10 * 1024 * 1024))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Singleton
    @Provides
    @Named("non_cached")
    fun provideNonCachedOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, @Named("non_cached") client: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    fun provideApi(builder: Retrofit.Builder): API =
        builder.baseUrl(Constants.HTTPS_URL)
            .build()
            .create(API::class.java)

}