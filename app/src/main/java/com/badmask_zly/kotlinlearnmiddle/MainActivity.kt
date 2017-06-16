package com.badmask_zly.kotlinlearnmiddle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.badmask_zly.kotlinlearnmiddle.adapter.ForecastListAdapter
import com.badmask_zly.kotlinlearnmiddle.domain.commands.RequestForecastCommand
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Mon 6/24 - Foggy - 21/8",
            "Mon 6/25 - Cloudy - 22/17",
            "Mon 6/26 - Rainy - 18/11",
            "Mon 6/27 - Foggy - 21/10",
            "Mon 6/28 - TRAPPED IN WEATHERASTATION - 23/18",
            "Mon 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  使用 Anko 来简化一些代码
        val forecastList: RecyclerView = find(R.id.forecase_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        // forecastList.adapter = ForecastListAdapter(items)

        async() {
            val result = RequestForecastCommand("BeiJing,CN").execute()
            uiThread { forecastList.adapter = ForecastListAdapter(result) }
        }

    }

}
