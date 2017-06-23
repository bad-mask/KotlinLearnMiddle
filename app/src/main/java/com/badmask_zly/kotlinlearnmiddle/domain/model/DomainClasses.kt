package com.badmask_zly.kotlinlearnmiddle.domain.model

data class ForecastList2(val id: Int, val city: String, val country: String,
        val dailyForecast: List<Forecast2>) {

    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int) = dailyForecast[position]
}

data class Forecast2(val id: Int, val date: Long, val description: String, val high: Int,
        val low: Int,
                    val iconUrl: String)