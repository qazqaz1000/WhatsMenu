package com.nckim.domain.repository

import com.nckim.domain.model.naver.Naver
import io.reactivex.Single

interface NaverRepository {
    fun getNaverPlace(query: String): Single<List<Naver>>
}