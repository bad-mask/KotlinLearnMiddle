package com.badmask_zly.kotlinlearnmiddle.domain.commands

import com.badmask_zly.kotlinlearnmiddle.domain.datasourece.ForecastProvider
import com.badmask_zly.kotlinlearnmiddle.domain.model.Forecast2

/**
 * Created by badmask_zly on 2017/6/22.
 */
class RequestDayForecastCommand(val id: Int, val forecastProvider: ForecastProvider = ForecastProvider()) : Command<Forecast2> {
    override fun execute() = forecastProvider.requestForecast(id)
}