package com.nckim.data.api

import com.nckim.data.api.ApiClient.NAVER_BASE_URL
import com.nckim.data.api.ApiClient.NAVER_CLIENT_ID
import com.nckim.data.api.ApiClient.NAVER_CLIENT_SECRET
import com.nckim.data.model.naver.NaverResponse
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApiInterface {
    @GET("v1/search/local.json")
    fun getSearchNaverPlace(
        @Query("query") query: String,
        @Query("display") display : Int = 5,
        @Query("start") start : Int = 1
    ): Single<NaverResponse>


    companion object{
        fun createNaverApi(): NaverApiInterface{
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val interceptor = Interceptor{chain ->
                with(chain){
                    val newRequest = request().newBuilder()
                        .addHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
                        .addHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                        .build()
                    proceed(newRequest)
                }
            }
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(NAVER_BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverApiInterface::class.java)
        }
    }
}