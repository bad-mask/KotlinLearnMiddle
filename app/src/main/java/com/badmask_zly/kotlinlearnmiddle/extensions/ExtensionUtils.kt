package com.badmask_zly.kotlinlearnmiddle.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by badmask_zly on 2017/6/22.
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}










































