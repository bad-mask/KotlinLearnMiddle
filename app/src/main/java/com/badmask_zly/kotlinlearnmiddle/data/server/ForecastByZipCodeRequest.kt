package com.badmask_zly.kotlinlearnmiddle.data.server

import android.util.Log
import com.badmask_zly.kotlinlearnmiddle.data.ForecastResult
import com.google.gson.Gson
import java.net.URL

/**
 * Created by badmask_zly on 2017/6/22.
 */
class ForecastByZipCodeRequest(val zipCode: Long, val gson: Gson = Gson()) {

    companion object {
        private val APP_ID = "a6584c8f1f35a3c0fdcd334784feea2c"
        private val URL1 = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL1&APPID=$APP_ID&id="
    }

    fun execute(): ForecastResult {
        Log.e("zly","访问网络")
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }

}