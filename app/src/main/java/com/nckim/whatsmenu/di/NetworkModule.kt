package com.nckim.whatsmenu.di

import android.content.Context
import com.nckim.whatsmenu.utils.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager{
        return NetworkManager(context)
    }
}