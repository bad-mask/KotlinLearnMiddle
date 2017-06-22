package com.badmask_zly.kotlinlearnmiddle.extensions


/**
 * Created by badmask_zly on 2017/6/19.
 */

/**
 * 把 map 转换成一个 vararg 数组。
 * 它是支持可 null 的值的「这是 map delegate 的条件」，把它转换为非 null 值的 Array 所组成的 Pairs
 */
fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()


/**
 * 在 ForecastProvider 中使用.
 * 该函数接收一个断言函数，它接受一个 T 类型的对象然后返回一个 R？ 类型的值 。
 * 这表示 predicate 可以返回 null 类型，但是我们的 firstResult 不能返回 null。这就是为什么返回 R 的原因。
 */

inline fun <T, R : Any> Iterable<T>.firstResult(predicate: (T) -> R?): R {
    for (element in this) {
        val result = predicate(element)
        if (result != null) return result
    }
    throw NoSuchElementException("No element matching predicate was found .")
}






























