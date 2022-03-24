package com.nckim.domain.usecase

import com.nckim.domain.model.naver.Naver
import com.nckim.domain.repository.NaverRepository
import io.reactivex.Single

class GetNaverPlaceUseCase constructor(
    private val naverRepository: NaverRepository
){
    fun invoke(): Single<List<Naver>> = naverRepository.getNaverPlace()
}