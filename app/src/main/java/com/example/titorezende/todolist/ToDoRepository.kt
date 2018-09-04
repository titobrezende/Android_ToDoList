package com.example.titorezende.todolist

import android.arch.lifecycle.LiveData
import org.jetbrains.anko.doAsync

class ToDoRepository (mDatabase: ToDoRoomDatabase) {
    //private var mObservableToDos: MediatorLiveData<List<ToDoModel>>? = null

    /**
     * Get the list of ToDos from the database and get notified when the data changes.
     */
    //val toDos: LiveData<List<ToDoModel>> get() = mObservableToDos as LiveData<List<ToDoModel>>
    /*
    init {
        mObservableToDos = MediatorLiveData<List<ToDoModel>>()

        mObservableToDos?.addSource(mDatabase.toDoDao().toDoList()
        ) { entities ->
            if (mDatabase.isOpen) {
                mObservableToDos?.postValue(entities)
            }
        }
    }*/

    companion object {

        private var sInstance: ToDoRepository? = null

        fun getInstance(database: ToDoRoomDatabase): ToDoRepository {
            if (sInstance == null) {
                synchronized(ToDoRepository::class.java) {
                    if (sInstance == null) {
                        sInstance = ToDoRepository(database)
                    }
                }
            }
            return sInstance as ToDoRepository
        }
    }

    private val mDao = mDatabase.toDoDao();

    fun insertToDo(toDoModel: ToDoModel) {
        doAsync { mDao.insert(toDoModel) }
    }
    fun insertAllToDo(vararg toDos: ToDoModel) {
        doAsync { mDao.insertAll(*toDos) }
    }
    fun updateToDo(toDo: ToDoModel) {
        doAsync { mDao.update(toDo) }
    }
    fun deleteToDo(toDo: ToDoModel) {
        doAsync { mDao.delete(toDo) }
    }
    fun deleteAllToDo() {
        mDao.deleteAll()
    }
    fun getFromIdToDo(id: Long ) : LiveData<ToDoModel>{
        return mDao.getFromID(id)
    }
    fun toDoList(): LiveData<List<ToDoModel>> {
        return mDao.toDoList()
    }
}