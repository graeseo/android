package com.graeseo.core.data.di

import com.graeseo.core.data.repository.StockEventRepositoryImpl
import com.graeseo.core.data.repository.ScenarioRepositoryImpl
import com.graeseo.core.domain.repository.ScenarioRepository
import com.graeseo.core.domain.repository.StockEventRepository
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
    abstract fun bindStockEventRepository(
        impl: StockEventRepositoryImpl,
    ): StockEventRepository

    @Binds
    @Singleton
    abstract fun bindScenarioRepository(
        impl: ScenarioRepositoryImpl,
    ): ScenarioRepository
}
