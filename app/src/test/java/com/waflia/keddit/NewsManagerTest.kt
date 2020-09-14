package com.waflia.keddit

import com.waflia.keddit.api.*
import com.waflia.keddit.commons.RedditNews
import com.waflia.keddit.features.NewsManager
import io.reactivex.observers.TestObserver
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Call
import retrofit2.Response
import java.util.*

class NewsManagerTest{

    var testSub = TestObserver<RedditNews>()
    var apiMock = mock<NewsAPI>()
    var callMock = mock<Call<RedditNewsResponse>>()

    @Before
    fun setup(){
        testSub = TestObserver<RedditNews>()
        apiMock = mock<NewsAPI>()
        callMock = mock<Call<RedditNewsResponse>>()
        `when`(apiMock.getNews(anyString(), anyString())).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic(){
        // prepare
        val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
        val response = Response.success(redditNewsResponse)

        `when`(callMock.execute()).thenReturn(response)

        // call
        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()
    }

    @Test
    fun testSuccess_checkOneNews(){
        // prepare
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

        `when`(callMock.execute()).thenReturn(response)

        // call
        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()

        assert(testSub.values().get(0).news[0].author == newsData.author)
        assert(testSub.values().get(0).news[0].title == newsData.title)
    }

    @Test
    fun testError(){
        val response = Response.error<RedditNewsResponse>(500, ResponseBody
                .create(MediaType.parse("application/json"),""))
        `when`(callMock.execute()).thenReturn(response)

        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        assert(testSub.errorCount() == 1)
    }
}
inline fun <reified T:Any> mock():T = Mockito.mock(T::class.java)