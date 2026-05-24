package com.graeseo.app.di

import com.graeseo.app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("feedUrl")
    fun provideFeedUrl(): String = BuildConfig.FEED_URL
}
