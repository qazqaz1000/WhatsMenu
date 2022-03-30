package com.nckim.domain.repository

import com.nckim.domain.model.naver.Naver
import com.nckim.domain.model.naver.NaverPlace
import io.reactivex.Single

interface NaverRepository {
    suspend fun getNaverPlace(query: String): List<NaverPlace>
}