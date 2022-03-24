package com.nckim.domain.repository

import com.nckim.domain.model.kakao.Kakao
import com.nckim.domain.model.kakao.Place
import io.reactivex.Single

interface KakaoRepository {
    fun getKakaoPlace(key: String,
                      query: String,
                      x : String,
                      y : String,
                      radius : Int): Single<List<Place>>
}