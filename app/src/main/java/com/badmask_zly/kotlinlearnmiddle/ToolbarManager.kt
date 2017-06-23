package com.badmask_zly.kotlinlearnmiddle

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.badmask_zly.kotlinlearnmiddle.extensions.ctx
import com.badmask_zly.kotlinlearnmiddle.extensions.slideEnter
import com.badmask_zly.kotlinlearnmiddle.extensions.slideExit
import org.jetbrains.anko.toast

/**
 * Created by badmask_zly on 2017/6/23.
 *
 * 接口可以帮助我们从类中提取出公共的代码来共享类似的行为。
 * 可以作为让我们嗲吗精炼合理简洁可复用的替代方案
 *
 * 作用：
 *     改变 title
 *     指定是否显示上一步的导航动作
 *     滚动时 toolbar 动画
 *     给所有的 activity 设置相同的菜单，甚至行为
 */
interface ToolbarManager {

    val toolbar: Toolbar //接口是无状态的，所以属性可以被定义，但是不能赋值

    /**
     * 另一方法，我们可以不使用重写来实现无状态的属性。
     * 也就是说，属性不需要维护一个 backup field
     */
    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> App.instance.toast("Settings")
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDraeable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDraeable() = with(DrawerArrowDrawable(toolbar.ctx)) {
        progress = 1f
        this
    }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }


}



































