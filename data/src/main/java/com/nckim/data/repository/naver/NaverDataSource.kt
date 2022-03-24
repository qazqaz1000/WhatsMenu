package com.nckim.data.repository.naver

interface NaverDataSource {
    fun getSearchNaverPlace(query: String)
}