package com.waflia.keddit.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class NewsRestAPI @Inject constructor(private val redditApi: RedditApi):NewsAPI {


    override fun getNews(after:String, limit:String): Call<RedditNewsResponse>{
        return redditApi.getTop(after, limit)
    }
}