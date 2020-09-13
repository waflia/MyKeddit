package com.waflia.keddit.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestAPI {

    private val redditAPI:RedditApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        redditAPI = retrofit.create(RedditApi::class.java)
    }

    fun getNews(after:String, limit:String): Call<RedditNewsResponse>{
        return redditAPI.getTop(after, limit)
    }
}