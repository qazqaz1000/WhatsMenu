package com.nckim.domain.usecase

import com.nckim.domain.model.naver.Naver
import com.nckim.domain.model.naver.NaverPlace
import com.nckim.domain.repository.NaverRepository
import io.reactivex.Single

class GetNaverPlaceUseCase constructor(
    private val naverRepository: NaverRepository
){
    fun invoke(query: String): Single<List<NaverPlace>> = naverRepository.getNaverPlace(query)
}