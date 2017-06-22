package com.badmask_zly.kotlinlearnmiddle.domain.datasourece

import com.badmask_zly.kotlinlearnmiddle.domain.model.Forecast2
import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList2

/**
 * Created by badmask_zly on 2017/6/20.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList2?
    fun requestDayForecast(id: Long): Forecast2?
}