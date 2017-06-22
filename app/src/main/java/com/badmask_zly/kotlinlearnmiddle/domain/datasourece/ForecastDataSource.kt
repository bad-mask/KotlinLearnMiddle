package com.badmask_zly.kotlinlearnmiddle.domain.datasourece

import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList2

/**
 * Created by badmask_zly on 2017/6/20.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList2?
}