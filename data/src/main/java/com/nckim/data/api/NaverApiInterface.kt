package com.nckim.data.api

import com.nckim.domain.model.kakao.Kakao
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApiInterface {
//    val apiURL =
//        "https://openapi.naver.com/v1/search/local.json?query=$text&display=10&start=2" // json 결과


    @GET("v1/search/local.json")    // Keyword.json의 정보를 받아옴
    fun getSearchNaverPlace(
        @Query("query") query: String,             // 검색을 원하는 질의어 [필수]
        @Query("x") x : String,
        @Query("y") y : String,
        @Query("radius") radius : Int
        // 매개변수 추가 가능
        // @Query("category_group_code") category: String
    ): Single<Kakao>    // 받아온 정보가 ResultSearchKeyword 클래스의 구조로 담김

}