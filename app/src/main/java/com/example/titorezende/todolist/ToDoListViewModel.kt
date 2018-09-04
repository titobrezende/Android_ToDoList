package com.example.titorezende.todolist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

/*
 * Copyright (C) 2018 Motorola Mobility, Inc.
 * All Rights Reserved.
 * Motorola Mobility Confidential Restricted.
 */
class ToDoListViewModel(application: Application) : AndroidViewModel(application) {
    val mRepository: ToDoRepository
    var mToDoList: LiveData<List<ToDoModel>>

    init {
        mRepository = ToDoRepository.getInstance(ToDoRoomDatabase.getInstance(application))
        mToDoList = mRepository.toDoList()
    }

    fun getAllWords(): LiveData<List<ToDoModel>> {
        return mToDoList
    }

    fun insert(toDo: ToDoModel) {

    }

}