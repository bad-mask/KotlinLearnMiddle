package com.badmask_zly.kotlinlearnmiddle.data.server

import com.badmask_zly.kotlinlearnmiddle.data.db.ForecastDb
import com.badmask_zly.kotlinlearnmiddle.domain.datasourece.ForecastDataSource
import com.badmask_zly.kotlinlearnmiddle.domain.model.Forecast2
import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList2

/**
 * Created by badmask_zly on 2017/6/20.
 * 从服务器端接收到数据后使用 ForecastDb 去保存到数据库。用这种方式，就可以缓存这些数据到数据库中提供给以后的查询
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestDayForecast(id: Long): Forecast2? = throw UnsupportedOperationException()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList2? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

}