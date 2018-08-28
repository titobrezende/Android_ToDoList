package com.example.titorezende.todolist

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

/*
 * Copyright (C) 2018 Motorola Mobility, Inc.
 * All Rights Reserved.
 * Motorola Mobility Confidential Restricted.
 */

/*
●	To_do item fields: Title, Description, CreatedDate, Done.
○	Done is marked when user complete the todo item;
○	Description is opcional;
○	Done is false by default;
○	Title must be checked if was typed before save the todo item;

 */

@Entity(tableName = "toDo_table")
data class ToDoModel(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "ID")
        var mId: Long = 0,
        @ColumnInfo(name = "Title")
        @NonNull
        var mTitle: String = "",
        @ColumnInfo(name = "Description")
        var mDescription: String = "",
        @ColumnInfo(name = "CreatedDate")
        @NonNull
        var mCreatedDate: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
        @ColumnInfo(name = "Done")
        @NonNull
        var mDone: Boolean = false
) {

}