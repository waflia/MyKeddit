package features

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waflia.keddit.R
import commons.adapter.ViewType
import commons.adapter.ViewTypeDelegateAdapter
import commons.inflate

class LoadingDelegateAdapter: ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item_loading)
    )

}