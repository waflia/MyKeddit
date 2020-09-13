package com.waflia.keddit.features

import com.waflia.keddit.api.RestAPI
import com.waflia.keddit.commons.RedditNews
import com.waflia.keddit.commons.RedditNewsItem
import io.reactivex.Observable

class NewsManager(private val api:RestAPI = RestAPI()){

    fun getNews(after: String, limit:String = "10"): Observable<RedditNews> {
        return Observable.create{
            subscriber ->
            val callResponse = api.getNews(after, limit)
            val response = callResponse.execute()

            if(response.isSuccessful){
                val dataResponse = response.body()!!.data
                val news = dataResponse.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments, item.created,
                    item.thumbnail, item.url)
                }
                val redditNews = RedditNews(
                        dataResponse.after?: "",
                        dataResponse.before?: "",
                        news
                )
                subscriber.onNext(redditNews)
                subscriber.onComplete()
            }else{
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}