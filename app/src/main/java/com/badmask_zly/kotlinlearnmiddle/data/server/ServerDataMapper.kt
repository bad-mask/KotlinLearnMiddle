package com.badmask_zly.kotlinlearnmiddle.data.server

import com.badmask_zly.kotlinlearnmiddle.data.Forecast
import com.badmask_zly.kotlinlearnmiddle.data.ForecastResult
import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList2
import java.util.*
import java.util.concurrent.TimeUnit
import com.badmask_zly.kotlinlearnmiddle.domain.model.Forecast2 as ModelForecast

/**
 * Created by badmask_zly on 2017/6/22.
 */
class ServerDataMapper {

    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList2(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(-1, dt, weather[0].description, temp.max.toInt(), temp.min.toInt(), generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}