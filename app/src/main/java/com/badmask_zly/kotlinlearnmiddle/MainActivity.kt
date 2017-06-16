package com.badmask_zly.kotlinlearnmiddle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.badmask_zly.kotlinlearnmiddle.adapter.ForecastListAdapter
import com.badmask_zly.kotlinlearnmiddle.domain.commands.RequestForecastCommand
import com.badmask_zly.kotlinlearnmiddle.domain.model.Forecast
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  使用 Anko 来简化一些代码
        val forecastList: RecyclerView = find(R.id.forecase_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        // forecastList.adapter = ForecastListAdapter(items)

        async() {
            val result = RequestForecastCommand("BeiJing,CN").execute()
            uiThread {
                /**
                 * 第二个参数采用的是对象表达式的写法，对象表达式是在使用他们的地方立即执行「及初始化」的
                 */
                forecastList.adapter = ForecastListAdapter(result, object : ForecastListAdapter.OnItemClickListener {
                    override fun invoke(forecast: Forecast) {
                        toast(forecast.data)
                    }

                })
            }
        }

    }

}
