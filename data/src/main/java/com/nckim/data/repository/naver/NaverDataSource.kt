package com.nckim.data.repository.naver

import com.nckim.data.model.naver.NaverResponse
import io.reactivex.Single

interface NaverDataSource {
    suspend fun getSearchNaverPlace(query: String): NaverResponse
}