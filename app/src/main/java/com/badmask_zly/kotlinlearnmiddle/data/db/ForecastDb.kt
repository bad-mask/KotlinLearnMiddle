package com.badmask_zly.kotlinlearnmiddle.data.db

import com.badmask_zly.kotlinlearnmiddle.domain.datasourece.ForecastDataSource
import com.badmask_zly.kotlinlearnmiddle.domain.model.Forecast2
import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList2
import com.badmask_zly.kotlinlearnmiddle.extensions.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.HashMap


/**
 * Created by badmask_zly on 2017/6/19.
 *
 * 作用：保存数据到数据库，从数据库中查询数据
 */
class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    override fun requestDayForecast(id: Long): Forecast2? = forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME).byId(id).parseOpt { DayForecast(HashMap(it)) }
        if (forecast != null ) dataMapper.convertDayToDomain(forecast) else null
    }

    /**
     * 使用 use 函数返回的结果作为这个函数返回的结果
     */
    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {

        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME).whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForcastTable.NAME).whereSimple("${CityForcastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    /**
     * saveForecast 函数只是从数据库中清楚数据，然后转换 domain model 为数据库 model，
     * 然后插入每一天的 forecast 和 city forecast 。
     */
    fun saveForecast(forecast: ForecastList2) = forecastDbHelper.use {
        clear(CityForcastTable.NAME)
        clear(DayForecastTable.NAME)
        with(dataMapper.convertFromDomain(forecast)) {

            insert(CityForcastTable.NAME, *map.toVarargArray())
            //在 toVarargArray 函数结果前使用「*」表示这个 array 会被分解成为一个 vararg 参数。
            //这个在 Java 中是自动处理的，但是我们需要在 Kotlin 中明确指明。

            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }


}



























