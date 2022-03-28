package com.nckim.data.api

import com.nckim.data.api.ApiClient.KAKAO_BASE_URL
import com.nckim.data.model.kakao.KakaoResponse
import com.nckim.domain.model.kakao.Kakao
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApiInterface {
    @GET("v2/local/search/keyword.json")    // Keyword.json의 정보를 받아옴
    fun getSearchKakaoPlace(
        @Header("Authorization") key: String,     // 카카오 API 인증키 [필수]
        @Query("query") query: String,             // 검색을 원하는 질의어 [필수]
        @Query("x") x : String,
        @Query("y") y : String,
        @Query("radius") radius : Int
        // 매개변수 추가 가능
        // @Query("category_group_code") category: String
    ): Single<KakaoResponse>    // 받아온 정보가 ResultSearchKeyword 클래스의 구조로 담김

    companion object {
        fun createKakaoApi(): KakaoApiInterface{
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(KAKAO_BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KakaoApiInterface::class.java)
        }
    }

}