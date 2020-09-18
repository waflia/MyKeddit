package com.waflia.keddit.di

import com.waflia.keddit.di.news.NewsModule
import com.waflia.keddit.features.NewsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModules::class,
        NewsModule::class,
        NetworkModule::class
))
interface NewsComponent {
    fun inject(newsFragment: NewsFragment)
}