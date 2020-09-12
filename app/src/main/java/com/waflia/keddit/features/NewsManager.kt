package com.waflia.keddit.features

import com.waflia.keddit.commons.RedditNewsItem
import io.reactivex.Observable

class NewsManager(){

    fun getNews(): Observable<List<RedditNewsItem>> {
        return Observable.create{
            subscriber ->
            val news = mutableListOf<RedditNewsItem>()
            for(i in 1..10){
                news.add(RedditNewsItem(
                        "author $i",
                        "Title $i",
                        i,
                        1457207701L - i * 200,
                        "",
                        "url"
                ))
            }
            subscriber.onNext(news)
        }
    }
}