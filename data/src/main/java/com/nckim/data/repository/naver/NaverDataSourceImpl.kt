package com.nckim.data.repository.naver

import com.nckim.data.api.NaverApiInterface
import com.nckim.data.model.naver.NaverResponse
import io.reactivex.Single
import javax.inject.Inject

class NaverDataSourceImpl @Inject constructor(
    private val naverApiInterface: NaverApiInterface
        ): NaverDataSource {
    override suspend fun getSearchNaverPlace(query: String): NaverResponse {
         return naverApiInterface.getSearchNaverPlace(query)
    }
}