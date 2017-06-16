package com.badmask_zly.kotlinlearnmiddle.domain.commands

/**
 * Created by badmask_zly on 2017/6/16.
 */

public interface Command<T> {
    fun execute(): T
}



















