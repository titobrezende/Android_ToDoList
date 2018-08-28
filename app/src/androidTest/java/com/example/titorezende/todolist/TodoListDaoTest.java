package com.example.titorezende.todolist;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Adapter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/*
 * Copyright (C) 2018 Motorola Mobility, Inc.
 * All Rights Reserved.
 * Motorola Mobility Confidential Restricted.
 */
@RunWith(AndroidJUnit4.class)
public class TodoListDaoTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private ToDoRoomDatabase mDatabase;
    private ToDoDao mToDoDao;

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                                                 ToDoRoomDatabase.class)
                        .allowMainThreadQueries()
                        .build();

        mToDoDao = mDatabase.toDoDao();
    }
    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }

    @Test void initDbTest(){
        Assert.assertNotNull(mDatabase);
    }

    @Test
    public void insertToDoTest() throws InterruptedException {
        ToDoModel toDo = new ToDoModel(1L, "Title", "Description", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), false);
        //Assert.assertNotNull(mDatabase);

        //Method to be tested
        long toDoId = mDatabase.toDoDao().insert(toDo);

        //assertions
        List<ToDoModel> toDoListFromDb = LiveDataTestUtil.getValue(mDatabase.toDoDao().toDoList());
        Assert.assertEquals(toDoId,1L);
        Assert.assertEquals(toDo,toDoListFromDb.get(0));
    }
}
