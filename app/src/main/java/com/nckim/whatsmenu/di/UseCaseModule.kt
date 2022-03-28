package com.nckim.whatsmenu.di

import com.nckim.domain.repository.KakaoRepository
import com.nckim.domain.repository.NaverRepository
import com.nckim.domain.usecase.GetKakaoPlaceUseCase
import com.nckim.domain.usecase.GetNaverPlaceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetKakaoPlaceUseCase(kakaoRepository: KakaoRepository): GetKakaoPlaceUseCase{
        return GetKakaoPlaceUseCase(kakaoRepository)
    }

    @Provides
    @Singleton
    fun provideGetNaverPlaceUseCase(naverRepository: NaverRepository): GetNaverPlaceUseCase{
        return GetNaverPlaceUseCase(naverRepository)
    }
}