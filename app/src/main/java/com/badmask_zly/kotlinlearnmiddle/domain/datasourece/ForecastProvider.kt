package com.badmask_zly.kotlinlearnmiddle.domain.datasourece

import com.badmask_zly.kotlinlearnmiddle.data.db.ForecastDb
import com.badmask_zly.kotlinlearnmiddle.data.server.ForecastServer
import com.badmask_zly.kotlinlearnmiddle.domain.model.Forecast2
import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList2
import com.badmask_zly.kotlinlearnmiddle.extensions.firstResult

/**
 * Created by badmask_zly on 2017/6/20.
 * 作用：接受一个 zip code 和一个 date ，然后根据那一天返回一周的天气预报
 */
class ForecastProvider(val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Int, days: Int): ForecastList2 = sources.firstResult {
        requestSource(it, days, zipCode)
    }

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Int): ForecastList2? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id: Int): Forecast2 = requestToSources {
        it.requestDayForecast(id)
    }

    /**
     * 计算今天毫秒级的时间
     */
    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS


    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

}