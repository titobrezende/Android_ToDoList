package com.example.titorezende.todolist

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import android.text.format.DateUtils
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*



/*
●	To_do item fields: Title, Description, CreatedDate, Done.
○	Done is marked when user complete the todo item;
○	Description is opcional;
○	Done is false by default;
○	Title must be checked if was typed before save the todo item;

 */

@Entity(tableName = "toDo_table")
data class ToDoModel(
        @ColumnInfo(name = "Title")
        @NonNull
        var mTitle: String = "",
        @ColumnInfo(name = "Description")
        var mDescription: String = "",
        @ColumnInfo(name = "CreatedDate")
        var mCreatedDate: Date = Date(),
        @ColumnInfo(name = "Done")
        @NonNull
        var mDone: Boolean = false
) {
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "ID")
        var mId: Long = 0

        fun toDateString(): String {
                return DateUtils.getRelativeTimeSpanString(mCreatedDate.time, DateUtils.DAY_IN_MILLIS,
                        DateUtils.DAY_IN_MILLIS,
                        DateUtils.FORMAT_ABBREV_ALL) as String
        }
}