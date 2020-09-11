package com.waflia.keddit.features

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waflia.keddit.R
import com.waflia.keddit.commons.adapter.ViewType
import com.waflia.keddit.commons.adapter.ViewTypeDelegateAdapter
import com.waflia.keddit.commons.extensions.inflate

class LoadingDelegateAdapter: ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item_loading)
    )

}