package com.example.titorezende.todolist

import android.arch.persistence.room.TypeConverter
import java.util.Date;


/*
 * Copyright (C) 2018 Motorola Mobility, Inc.
 * All Rights Reserved.
 * Motorola Mobility Confidential Restricted.
 */
object DateTypeConverter {

    @TypeConverter
    @JvmStatic
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun toLong(value: Date?): Long? {
        return value?.time
    }
}