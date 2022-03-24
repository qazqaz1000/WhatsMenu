package com.nckim.data.api

import com.nckim.data.model.naver.NaverResponse
import io.reactivex.Single
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

}