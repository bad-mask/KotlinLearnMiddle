package com.badmask_zly.kotlinlearnmiddle.domain.commands

import android.util.Log
import com.badmask_zly.kotlinlearnmiddle.domain.ForecastDataMapper
import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList
import com.badmask_zly.kotlinlearnmiddle.http.ForecastRequest

/**
 * Created by badmask_zly on 2017/6/16.
 */

/**
 * 用 private 来修饰属性，这时我们就创建了一个不可修改的属性 zipCode ，
 * 它的值我们只能去得到，不能去修改它。
 * 所以这个不大的改动让代码看起来更加清晰。
 * 如果我们在编写类的时候，你觉得某些属性因为是什么原因不能对别人可见，那就把它定义为 private 。
 */
class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())

    }
}