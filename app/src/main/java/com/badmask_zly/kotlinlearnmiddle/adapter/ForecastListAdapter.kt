package com.badmask_zly.kotlinlearnmiddle.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList

/**
 * Created by badmask_zly on 2017/6/16.
 */

class ForecastListAdapter(val weekForecast: ForecastList) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun getItemCount() = weekForecast.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //public inline fun <T, R> with(receiver: T, block: T.() -> R): R = receiver.block()
        /**
         * with 是一个非常有用的函数，它包含在 Kolitn 的标准库中。
         * 它接收一个对象和一个扩展函数作为它的参数，然后使这个对象扩展这个函数。
         * 这表示所有在括号中编写的代码都是作为对象的一个扩展函数，我们可以就像作为 this 一样使用所有它的 public 方法和属性。
         * 当我们针对同一个对象做很多操作的时候这个非常有利于简化代码
         */
        with(weekForecast[position]) {
            holder.textView.text = "$data - $descritpion - $high/$low "
        }
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}