package com.nckim.domain.usecase

import com.nckim.domain.model.kakao.Kakao
import com.nckim.domain.repository.KakaoRepository
import io.reactivex.Single

class GetKakaoPlaceUseCase constructor(
    private val kakaoRepository: KakaoRepository
){
    fun invoke(): Single<List<Kakao>> = kakaoRepository.getKakaoPlace()
}