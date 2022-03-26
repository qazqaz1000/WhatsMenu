package com.nckim.domain.usecase

import com.nckim.domain.model.kakao.KakaoPlace
import com.nckim.domain.repository.KakaoRepository
import io.reactivex.Single

class GetKakaoPlaceUseCase constructor(
    private val kakaoRepository: KakaoRepository
){
    fun invoke(key: String,
    query: String,
    x: String,
    y: String,
    radius: Int): Single<List<KakaoPlace>> = kakaoRepository.getKakaoPlace(key, query, x, y, radius)
}