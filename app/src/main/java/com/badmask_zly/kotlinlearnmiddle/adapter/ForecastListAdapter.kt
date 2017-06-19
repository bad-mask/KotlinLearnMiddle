package com.badmask_zly.kotlinlearnmiddle.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.badmask_zly.kotlinlearnmiddle.R
import com.badmask_zly.kotlinlearnmiddle.domain.model.Forecast
import com.badmask_zly.kotlinlearnmiddle.domain.model.ForecastList
import com.badmask_zly.kotlinlearnmiddle.extensions.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * Created by badmask_zly on 2017/6/16.
 */

/**
 * Koltin 允许 Java 库的一些优化 ，Interface 中包含单个函数可以被替代为一个函数
 */
class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: (Forecast) -> Unit) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
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
            holder.bindForecast(weekForecast[position])
        }
    }

    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                /**
                 * 直接使用 parent.ctx 与 itemView.ctx 不会被编译成功。
                 * Anko 提供了大量的扩展函数来让 Android 编程更简单。
                 * 举个例子，activitys 、 fragments 以及其他包含了 ctx 这个属性，通过 ctx 这个属性来返回 context ，但是在 View 中缺少这个属性。
                 */
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = data
                itemView.description.text = descritpion
                itemView.maxTemperature.text = "${high.toString()}"
                itemView.minTemperature.text = "${low.toString()}"
                itemView.setOnClickListener { itemClick(forecast) }
            }
        }
    }

}