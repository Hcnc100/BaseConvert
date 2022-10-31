package com.d34th.nullpointer.baseconvert.inject

import android.content.Context
import com.d34th.nullpointer.baseconvert.data.local.SettingsDataSource
import com.d34th.nullpointer.baseconvert.data.local.SettingsDataSourceImpl
import com.d34th.nullpointer.baseconvert.domain.SettingsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettingsDataSource(
        @ApplicationContext context: Context
    ): SettingsDataSource = SettingsDataSourceImpl(context)


    @Provides
    @Singleton
    fun provideSettingsRepository(
        settingsDataSource: SettingsDataSource
    ): SettingsRepoImpl = SettingsRepoImpl(settingsDataSource)
}