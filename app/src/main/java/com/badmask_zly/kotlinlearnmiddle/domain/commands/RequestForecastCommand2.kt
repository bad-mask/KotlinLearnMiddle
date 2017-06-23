package com.badmask_zly.kotlinlearnmiddle.domain.commands

import com.badmask_zly.kotlinlearnmiddle.domain.datasourece.ForecastProvider
import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList2

/**
 * Created by badmask_zly on 2017/6/22.
 * 用新的写法替换原来的 RequestForecastCommand
 * 相较与原来的内容，RequestForecastCommand2 不会再直接与服务端交互，也不会转换数据到 doamin model
 */
class RequestForecastCommand2(val zipCode: Int,
                              val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList2> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList2 {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }

}