package com.badmask_zly.kotlinlearnmiddle.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by badmask_zly on 2017/6/22.
 */

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)