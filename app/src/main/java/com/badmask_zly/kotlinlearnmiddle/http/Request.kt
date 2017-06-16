package com.badmask_zly.kotlinlearnmiddle.http

import android.util.Log
import java.net.URL

/**
 * Created by badmask_zly on 2017/6/16.
 */
public class Request(val url: String) {
    public fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }


}