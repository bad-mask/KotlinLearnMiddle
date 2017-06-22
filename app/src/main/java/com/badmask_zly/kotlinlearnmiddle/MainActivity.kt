package com.badmask_zly.kotlinlearnmiddle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.badmask_zly.kotlinlearnmiddle.adapter.ForecastListAdapter
import com.badmask_zly.kotlinlearnmiddle.domain.commands.RequestForecastCommand
import com.badmask_zly.kotlinlearnmiddle.domain.commands.RequestForecastCommand2
import com.badmask_zly.kotlinlearnmiddle.extensions.toDateString
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  使用 Koltin Android Extensions 来修改我们的代码
        forecastList.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager

        doAsync {
            val result = RequestForecastCommand2(1816670).execute()
            uiThread {
                /**
                 * 如果 lambda 函数只接受一个参数，那我们可以使用 it 引用，而不用指定左边的参数
                 */
                forecastList.adapter = ForecastListAdapter(result) { toast(it.date.toDateString()) }
            }
        }

        

    }

}
