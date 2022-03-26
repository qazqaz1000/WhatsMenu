package com.nckim.domain.repository

import com.nckim.domain.model.kakao.KakaoPlace
import io.reactivex.Single

interface KakaoRepository {
    fun getKakaoPlace(key: String,
                      query: String,
                      x : String,
                      y : String,
                      radius : Int): Single<List<KakaoPlace>>
}