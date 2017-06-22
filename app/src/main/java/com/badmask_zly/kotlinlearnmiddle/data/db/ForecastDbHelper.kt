package com.badmask_zly.kotlinlearnmiddle.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.badmask_zly.kotlinlearnmiddle.App
import org.jetbrains.anko.db.*

/**
 * Created by badmask_zly on 2017/6/19.
 */
class ForecastDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx, ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CityForcastTable.NAME, true)
        db.dropTable(DayForecastTable.NAME, true)
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(CityForcastTable.NAME, true,
                CityForcastTable.ID to INTEGER + PRIMARY_KEY,
                CityForcastTable.CITY to TEXT,
                CityForcastTable.COUNTRY to TEXT)

        db.createTable(DayForecastTable.NAME, true,
                DayForecastTable.ID to INTEGER + PRIMARY_KEY ,
                DayForecastTable.DATE to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to TEXT,
                DayForecastTable.CITY_ID to INTEGER)
    }

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance: ForecastDbHelper by lazy { ForecastDbHelper() }
    }
}