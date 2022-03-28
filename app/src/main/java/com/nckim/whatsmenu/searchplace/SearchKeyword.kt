package com.nckim.whatsmenu.searchplace

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchKeyword {
    companion object{
        const val BASE_URL = "https://dapi.kakao.com/"
//        const val BASE_URL = "https://dapi.kakao.com/v2/local/search/keyword.json/"
        const val API_KEY = "KakaoAK d6cebb6d32092fd0d340cc2e99d7a9b7"

        fun searchKeyword(keyword : String, lat : String, lng : String, radius : Int, searchKeywordCallback: SearchKeywordCallback){
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = retrofit.create(KakaoAPI::class.java)
            val call = api.getSearchKeyword(API_KEY, keyword, lat, lng, radius)

            call.enqueue(object : Callback<ResultSearchKeyword> {
                override fun onResponse(
                    call: Call<ResultSearchKeyword>,
                    response: Response<ResultSearchKeyword>
                ) {
                    Log.d("Test", "Raw: ${response.raw()}")
                    Log.d("Test", "Body: ${response.body()}")
                    searchKeywordCallback.resultCallback(response.body())
                }

                override fun onFailure(call: Call<ResultSearchKeyword>, t: Throwable) {
                    Log.e("MainActivity", "통신 실패: ${t.message}")
                }

            })
        }
    }

    interface SearchKeywordCallback{
        fun resultCallback(result : ResultSearchKeyword?)
    }


}