package com.badmask_zly.kotlinlearnmiddle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.badmask_zly.kotlinlearnmiddle.adapter.ForecastListAdapter
import com.badmask_zly.kotlinlearnmiddle.domain.commands.RequestForecastCommand
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

        async {
            val result = RequestForecastCommand("BeiJing,CN").execute()
            uiThread {
                /**
                 * 如果 lambda 函数只接受一个参数，那我们可以使用 it 引用，而不用指定左边的参数
                 */
                forecastList.adapter = ForecastListAdapter(result) { toast(it.data) }
            }
        }

    }

}
