@file:JvmName("ExtensionsUtils")

package com.waflia.keddit.commons.extensions

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.waflia.keddit.R

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return  LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImg(imageUrl:String){
    if(TextUtils.isEmpty(imageUrl)){
        Picasso.get().load(R.mipmap.ic_launcher).into(this)
    }else{
        Picasso.get().load(imageUrl).into(this)
    }
}