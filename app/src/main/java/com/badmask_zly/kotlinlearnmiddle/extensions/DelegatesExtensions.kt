package com.badmask_zly.kotlinlearnmiddle.extensions

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by badmask_zly on 2017/6/19.
 */

/**
 * 现在创建一个对象，然后添加函数使用自定义的委托
 */
object DelegatesExt {
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()

    fun longPreference(context: Context, name: String, default: Int) = LongPreference(context, name, default)
}


/**
 * 创建一个自定义的委托：
 * 创建一个 notNull 的委托，它只能被赋值一次，如果第二次赋值，它就会抛出异常
 */
private class NotNullSingleValueVar<T>() : ReadWriteProperty<Any?, T> {

    private var value: T? = null

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value else throw IllegalStateException("${property.name} already initialized")
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} " + "not initialized")
    }

}

/**
 * 创建一个 Long 属性的委托
 */
class LongPreference(val context: Context, val name: String, val default: Int) : ReadWriteProperty<Any?, Int> {

    val prefs by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }


    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return prefs.getInt(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        prefs.edit().putInt(name, value).apply()
    }

}





































