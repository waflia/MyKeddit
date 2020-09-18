package com.waflia.keddit.di.news

import com.waflia.keddit.api.NewsAPI
import com.waflia.keddit.api.NewsRestAPI
import com.waflia.keddit.api.RedditApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsAPI(redditApi: RedditApi):NewsAPI{
        return NewsRestAPI(redditApi)
    }

    @Provides
    @Singleton
    fun provideRedditAPI(retrofit: Retrofit):RedditApi{
        return retrofit.create(RedditApi::class.java)
    }
}