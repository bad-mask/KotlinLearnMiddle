package com.badmask_zly.kotlinlearnmiddle

import android.app.Application
import com.badmask_zly.kotlinlearnmiddle.extensions.DelegatesExt

/**
 * Created by badmask_zly on 2017/6/19.
 */
class App : Application() {
    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}