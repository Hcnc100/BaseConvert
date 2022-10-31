package com.d34th.nullpointer.baseconvert.inject

import com.d34th.nullpointer.baseconvert.domain.SettingsRepoImpl
import com.d34th.nullpointer.baseconvert.domain.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideSettingsRepository(
        settingsRepoImpl: SettingsRepoImpl
    ): SettingsRepository

}