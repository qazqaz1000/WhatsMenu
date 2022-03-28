package com.nckim.domain.repository

import com.nckim.domain.model.naver.Naver
import com.nckim.domain.model.naver.NaverPlace
import io.reactivex.Single

interface NaverRepository {
    fun getNaverPlace(query: String): Single<List<NaverPlace>>
}