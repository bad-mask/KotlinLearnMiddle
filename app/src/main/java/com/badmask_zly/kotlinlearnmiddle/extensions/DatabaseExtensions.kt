package com.badmask_zly.kotlinlearnmiddle.extensions

import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

/**
 * Created by badmask_zly on 2017/6/19.
 */

/**
 * 创建一个接收 lambda 函数返回一个 MapRowParser 的函数 。
 */
fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T): List<T> =
        parseList(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })


fun <T : Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T): T? =
        parseOpt(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })


/**
 * 创建一个 SQLiteDatabase 的扩展函数来让我们可以像 SQL 查询一样来执行他。
 */

fun SQLiteDatabase.clear(tableName : String){
    execSQL("delete from $tableName")

}





