package com.badmask_zly.kotlinlearnmiddle.data.db

import com.badmask_zly.kotlinlearnmiddle.domain.model.Forecast2
import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList2


/**
 * Created by badmask_zly on 2017/6/19.
 */
class DbDataMapper {

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList2(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast2(_id, date, description, high, low, iconUrl)
    }

    fun convertFromDomain(forecast: ForecastList2) = with(forecast) {
        val daily = this.dailyForecast.map {
            convertDayFromDomain(id, it)
        }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast2: Forecast2) = with(forecast2) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }
}