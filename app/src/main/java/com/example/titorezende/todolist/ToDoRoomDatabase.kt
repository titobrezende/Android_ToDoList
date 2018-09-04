package com.example.titorezende.todolist

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context


@Database(entities = arrayOf(ToDoModel::class),version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class ToDoRoomDatabase : RoomDatabase() {
    abstract fun toDoDao() : ToDoDao

    companion object {
        private var sToDoRoomDatabase: ToDoRoomDatabase? = null

        fun getInstance(context: Context): ToDoRoomDatabase {
            if (sToDoRoomDatabase == null) {
                synchronized(ToDoRoomDatabase::class) {
                    if (sToDoRoomDatabase == null) {
                        sToDoRoomDatabase = Room.databaseBuilder(context.applicationContext,
                                ToDoRoomDatabase::class.java, "ToDo_database").build()
                    }
                }
            }
            return sToDoRoomDatabase as ToDoRoomDatabase
        }

        fun destroyInstance() {
            sToDoRoomDatabase = null
        }

    }
}