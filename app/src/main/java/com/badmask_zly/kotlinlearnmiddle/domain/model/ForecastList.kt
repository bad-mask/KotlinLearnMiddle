package com.badmask_zly.kotlinlearnmiddle.domain.model


/**
 * Created by badmask_zly on 2017/6/16.
 */
data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>) {
    operator fun get(position: Int): Forecast = dailyForecast[position]
    fun size(): Int = dailyForecast.size
}
