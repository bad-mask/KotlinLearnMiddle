package com.badmask_zly.kotlinlearnmiddle.domain.commands

import android.util.Log
import com.badmask_zly.kotlinlearnmiddle.domain.ForecastDataMapper
import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList
import com.badmask_zly.kotlinlearnmiddle.http.ForecastRequest

/**
 * Created by badmask_zly on 2017/6/16.
 */
class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        Log.e("zly","RequestForecastCommand")
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())

    }
}