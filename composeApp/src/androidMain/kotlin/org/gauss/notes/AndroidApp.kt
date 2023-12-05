package org.gauss.notes

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import org.koin.android.ext.koin.androidContext
import di.initKoin

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AndroidApp)
        }
    }
}

fun Context.isDebug() = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
