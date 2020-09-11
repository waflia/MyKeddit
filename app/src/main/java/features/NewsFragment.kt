package features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.waflia.keddit.R
import commons.inflate
import kotlinx.android.synthetic.main.news_fragment.*


class NewsFragment: Fragment(){

    //private var newsList: RecyclerView? = null
//    private val newsList by lazy {
//        news_list
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        newsList.setHasFixedSize(true)
////        newsList.layoutManager = LinearLayoutManager(context)
        news_list.setHasFixedSize(true)
        news_list.layoutManager = LinearLayoutManager(context)

        initAdapter()
    }

    private fun initAdapter(){
        if(news_list.adapter == null){
            news_list.adapter = NewsAdapter()
        }
    }
}