package com.nckim.data.repository.naver

import com.nckim.domain.model.naver.Naver
import com.nckim.domain.repository.NaverRepository
import io.reactivex.Single

class NaverRepositoryImpl constructor(
    private val naverDataSource: NaverDataSource
): NaverRepository {
    override fun getNaverPlace(query: String): Single<List<Naver>> {

    }

}