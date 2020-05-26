package com.hurist.wanandroid

import android.app.Application

class WanApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Application
            private set
    }
}