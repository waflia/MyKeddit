package com.waflia.keddit.commons

import androidx.fragment.app.Fragment
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposable

open class RxBaseFragment(): Fragment() {

    protected var subscriptions:Disposable? = null
//    override fun onResume() {
//        super.onResume()
//        //subscriptions = CompositeSubscription()
//    }
//
//    override fun onPause() {
//        super.onPause()
////        if(!subscriptions.isUnsubscribed){
////            subscriptions.unsubscribe()
////        }
////        subscriptions.clear()
//    }
}