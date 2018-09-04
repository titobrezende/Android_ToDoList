package com.example.titorezende.todolist

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*


@Dao
public interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(toDo: ToDoModel): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg toDos: ToDoModel) :LongArray

    @Query("SELECT * from toDo_table WHERE ID = :id")
    fun getFromID(id: Long ) : LiveData<ToDoModel>

    @Delete
    fun delete(toDo: ToDoModel)

    @Update
    fun update(toDo: ToDoModel)

    @Query("DELETE from toDo_table")
    fun deleteAll()

    @Query("SELECT * from toDo_table ORDER BY ID ASC")
    fun toDoList() : LiveData<List<ToDoModel>>
}