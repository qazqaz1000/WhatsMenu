package com.nckim.data.repository.naver

import com.nckim.data.model.naver.NaverResponse
import io.reactivex.Single

interface NaverDataSource {
    fun getSearchNaverPlace(query: String): Single<NaverResponse>
}