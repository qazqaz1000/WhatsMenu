package com.nckim.whatsmenu.di

import com.nckim.data.repository.kakao.KakaoDataSource
import com.nckim.data.repository.kakao.KakaoRepositoryImpl
import com.nckim.data.repository.naver.NaverDataSource
import com.nckim.data.repository.naver.NaverRepositoryImpl
import com.nckim.domain.repository.KakaoRepository
import com.nckim.domain.repository.NaverRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideKakaoRepository(kakaoDataSource: KakaoDataSource): KakaoRepository{
        return KakaoRepositoryImpl(kakaoDataSource)
    }

    @Provides
    @Singleton
    fun provideNaverRepository(naverDataSource: NaverDataSource): NaverRepository{
        return NaverRepositoryImpl(naverDataSource)
    }

}