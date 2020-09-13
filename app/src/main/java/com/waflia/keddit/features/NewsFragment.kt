package com.waflia.keddit.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.waflia.keddit.R
import com.waflia.keddit.commons.InfiniteScrollListener
import com.waflia.keddit.commons.RedditNews
import com.waflia.keddit.commons.RxBaseFragment
import com.waflia.keddit.commons.extensions.inflate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.news_fragment.*


class NewsFragment: RxBaseFragment(){

    var subscription:Disposable? = null
    private var redditNews: RedditNews? = null
    private val newsManager by lazy{NewsManager()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.setHasFixedSize(true)
        //news_list.layoutManager = LinearLayoutManager(context)
        val linearLayout = LinearLayoutManager(context)
        news_list.layoutManager = linearLayout
        news_list.clearOnScrollListeners()
        news_list.addOnScrollListener(InfiniteScrollListener({requestNews()}, linearLayout))

        initAdapter()

        if(savedInstanceState == null){
            requestNews()
        }
    }

    private fun requestNews() {
        subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->

                            redditNews = retrievedNews
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                        },
                        { e ->
                            Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
    }

    override fun onPause() {
        super.onPause()
        if(!(subscription?.isDisposed?:false)){
            subscription!!.dispose()
        }
    }

    override fun onResume() {
        super.onResume()


    }

    private fun initAdapter(){
        if(news_list.adapter == null){
            news_list.adapter = NewsAdapter()
        }
    }
}