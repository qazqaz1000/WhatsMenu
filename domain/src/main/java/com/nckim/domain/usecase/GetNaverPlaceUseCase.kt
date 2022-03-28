package com.nckim.domain.usecase

import com.nckim.domain.model.naver.Naver
import com.nckim.domain.model.naver.NaverPlace
import com.nckim.domain.repository.NaverRepository
import io.reactivex.Single
import javax.inject.Inject

class GetNaverPlaceUseCase @Inject constructor(
     private val naverRepository: NaverRepository
){
    fun invoke(query: String): Single<List<NaverPlace>> = naverRepository.getNaverPlace(query)
}