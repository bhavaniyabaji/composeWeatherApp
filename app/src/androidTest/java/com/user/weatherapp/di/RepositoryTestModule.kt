package com.user.weatherapp.di

import com.user.weatherapp.data.FakeWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import com.user.weatherapp.data.repository.WeatherRepository
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object RepositoryTestModule {

    @Singleton
    @Provides
    fun provideRepository(): WeatherRepository = FakeWeatherRepository
}
