package com.example.titorezende.todolist

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import java.time.LocalDateTime
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import java.time.ZoneOffset


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
public class ExampleInstrumentedTest {

    @Rule @JvmField
    public var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var mDatabase : ToDoRoomDatabase? = null
    private var toDoDao: ToDoDao? = null

    /*@Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.titorezende.todolist", appContext.packageName)
    }*/

    @Before
    fun createDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                ToDoRoomDatabase::class.java!!)
                .allowMainThreadQueries()
                .build()
        toDoDao = mDatabase?.toDoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        mDatabase?.close()
    }

    @Test
    fun insertToDoTest() {
        val toDo = ToDoModel(mTitle = "Test Title", mDescription = "Test Description",
                mCreatedDate = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), mDone = true)
        val toDoId = mDatabase?.toDoDao()?.insert(toDo)
        val toDoFromDb : ToDoModel = mDatabase?.toDoDao()?.toDoList()?.value!![0]
        assertEquals(toDoId,1)
        assertEquals(toDo,toDoFromDb)
    }

}
