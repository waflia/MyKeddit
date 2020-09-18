package com.waflia.keddit.di

import android.content.Context
import com.waflia.keddit.KedditApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModules(val app:KedditApp) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideApplication(): KedditApp{
        return app
    }

}