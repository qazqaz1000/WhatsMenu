package com.nckim.whatsmenu.di

import com.nckim.data.api.KakaoApiInterface
import com.nckim.data.api.NaverApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Singleton
    @Provides
    fun provideKakaoApiInterface(): KakaoApiInterface{
        return KakaoApiInterface.createKakaoApi()
    }

    @Singleton
    @Provides
    fun provideNaverApiInterface(): NaverApiInterface{
        return NaverApiInterface.createNaverApi()
    }
}