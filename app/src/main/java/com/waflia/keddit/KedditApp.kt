package com.waflia.keddit

import android.app.Application
import com.waflia.keddit.di.AppModules
import com.waflia.keddit.di.DaggerNewsComponent
import com.waflia.keddit.di.NewsComponent

class KedditApp: Application(){
    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.create()
    }

}