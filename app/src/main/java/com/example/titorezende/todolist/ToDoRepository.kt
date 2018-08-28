package com.example.titorezende.todolist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData


class ToDoRepository private constructor(private val mDatabase: ToDoRoomDatabase) {
    private var mObservableToDos: MediatorLiveData<List<ToDoModel>>? = null

    /**
     * Get the list of ToDos from the database and get notified when the data changes.
     */
    val toDos: LiveData<List<ToDoModel>>
        get() = mObservableToDos as LiveData<List<ToDoModel>>

    init {
        mObservableToDos = MediatorLiveData<List<ToDoModel>>()

        mObservableToDos?.addSource(mDatabase.toDoDao().toDoList()
        ) { entities ->
            if (mDatabase.isOpen) {
                mObservableToDos?.postValue(entities)
            }
        }
    }

    fun loadToDo(id: Long): LiveData<ToDoModel> {
        return mDatabase.toDoDao().getFromID(id)
    }

    companion object {

        private var sInstance: ToDoRepository? = null

        fun getInstance(database: ToDoRoomDatabase): ToDoRepository? {
            if (sInstance == null) {
                synchronized(ToDoRepository::class.java) {
                    if (sInstance == null) {
                        sInstance = ToDoRepository(database)
                    }
                }
            }
            return sInstance
        }
    }
}