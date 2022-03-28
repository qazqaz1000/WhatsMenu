package com.nckim.whatsmenu.di

import com.nckim.data.api.KakaoApiInterface
import com.nckim.data.api.NaverApiInterface
import com.nckim.data.repository.kakao.KakaoDataSource
import com.nckim.data.repository.kakao.KakaoDataSourceImpl
import com.nckim.data.repository.naver.NaverDataSource
import com.nckim.data.repository.naver.NaverDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideKakaoDataSource(kakaoApiInterface: KakaoApiInterface): KakaoDataSource{
        return KakaoDataSourceImpl(kakaoApiInterface)
    }

    @Provides
    @Singleton
    fun provideNaverDataSource(naverApiInterface: NaverApiInterface): NaverDataSource {
        return NaverDataSourceImpl(naverApiInterface)
    }
}