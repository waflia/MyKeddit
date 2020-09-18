package com.waflia.keddit

import com.waflia.keddit.api.*
import com.waflia.keddit.commons.RedditNews
import com.waflia.keddit.features.NewsManager
import io.reactivex.observers.TestObserver
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Before
import org.junit.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

import retrofit2.Call
import retrofit2.Response
import java.util.*

@RunWith(JUnitPlatform::class)
class NewsManagerSpekTest: Spek({
    given("a NewsManager") {
        var testSub = TestObserver<RedditNews>()
        var apiMock = mock<NewsAPI>()
        var callMock = mock<Call<RedditNewsResponse>>()

        beforeEachTest {
            testSub = TestObserver<RedditNews>()
            apiMock = mock<NewsAPI>()
            callMock = mock<Call<RedditNewsResponse>>()
            Mockito.`when`(apiMock.getNews(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(callMock)
        }
        on("service returns smthg") {
            // prepare
            val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
            val response = Response.success(redditNewsResponse)

            Mockito.`when`(callMock.execute()).thenReturn(response)

            // call
            val newsManager = NewsManager(apiMock)
            newsManager.getNews("").subscribe(testSub)

            it("should recieve smth and no error") {
                // assert
                testSub.assertNoErrors()
                testSub.assertValueCount(1)
                testSub.assertComplete()
            }
        }

        on("service returns just one news") {

            val newsData = RedditNewsDataResponse(
                    "author",
                    "title",
                    10,
                    Date().time,
                    "thumbnail",
                    "url"
            )
            val newsResponse = RedditChildrenResponse(newsData)
            val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(newsResponse), null, null))
            val response = Response.success(redditNewsResponse)

            Mockito.`when`(callMock.execute()).thenReturn(response)

            // call
            val newsManager = NewsManager(apiMock)
            newsManager.getNews("").subscribe(testSub)

            it("should process only one news successfully") {
                testSub.assertNoErrors()
                testSub.assertValueCount(1)
                testSub.assertComplete()

                assert(testSub.values().get(0).news[0].author == newsData.author)
                assert(testSub.values().get(0).news[0].title == newsData.title)
            }
        }

        on("service returns error 500") {
            val response = Response.error<RedditNewsResponse>(500, ResponseBody
                    .create(MediaType.parse("application/json"), ""))
            Mockito.`when`(callMock.execute()).thenReturn(response)

            val newsManager = NewsManager(apiMock)
            newsManager.getNews("").subscribe(testSub)

            it("should receive an onError message") {
                assert(testSub.errorCount() == 1)
            }
        }
    }
})