package com.badmask_zly.kotlinlearnmiddle.data.db

import kotlin.collections.HashMap

/**
 * Created by badmask_zly on 2017/6/19.
 */

/**
 * 通过 map 的使用，我们可以用很简单的方式把类转换为数据表，反之依然。
 */
class CityForecast(val map: MutableMap<String, Any?>, val dailyForecast: List<DayForecast>) {
    var _id: Int by map
    var city: String by map
    var country: String by map

    constructor(id: Int, city: String, country: String, dailyForecast: List<DayForecast>) : this(HashMap(), dailyForecast) {
        this._id = id
        this.city = city
        this.country = country
    }
}


class DayForecast(var map: MutableMap<String, Any?>) {
    var _id: Int by map
    var date: Long by map
    var description: String by map
    var high: Int by map
    var low: Int by map
    var iconUrl: String by map
    var cityId: Int by map

    constructor(date: Long, description: String, high: Int, low: Int, iconUrl: String, cityId: Int) : this(HashMap()) {
        this.date = date
        this.description = description
        this.high = high
        this.low = low
        this.iconUrl = iconUrl
        this.cityId = cityId
    }

}
