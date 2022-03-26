package com.nckim.data.repository.naver

import com.nckim.data.api.NaverApiInterface
import com.nckim.data.model.naver.NaverResponse
import io.reactivex.Single

class NaverDataSourceImpl (
    private val naverApiInterface: NaverApiInterface
        ): NaverDataSource {
    override fun getSearchNaverPlace(query: String): Single<NaverResponse> {
         return naverApiInterface.getSearchNaverPlace(query)
    }
}