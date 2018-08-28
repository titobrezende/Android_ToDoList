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

    @Test
    public void initDbTest(){
        Assert.assertNotNull(mDatabase);
        Assert.assertNotNull(mToDoDao);
    }

    ToDoModel FakeToDoItem() {
        ToDoModel toDo = new ToDoModel( "Title", "Description",
                                       LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), false);
        return toDo;
    }


    @Test
    public void insertToDoTest() throws InterruptedException {
        //Method to be tested
        ToDoModel toDo = FakeToDoItem();
        long toDoId = mToDoDao.insert(toDo);
        toDo.setMId(toDoId);
        //assertions
        ToDoModel toDoFromDb = LiveDataTestUtil.getValue(mToDoDao.getFromID(toDoId));
        Assert.assertEquals(toDo.getMId(),toDoId);
        Assert.assertEquals(toDo,toDoFromDb);
    }

    @Test
    public void updateToDoTest() throws InterruptedException {
        //prepare
        ToDoModel fakeToDo = FakeToDoItem();
        long toDoId = mToDoDao.insert(fakeToDo);
        fakeToDo.setMId(toDoId);
        ToDoModel toDoFromDbBefore = LiveDataTestUtil.getValue(mToDoDao.getFromID(toDoId));
        fakeToDo.setMTitle("New Title");
        fakeToDo.setMDescription("New description");
        Long newDateTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        fakeToDo.setMCreatedDate(newDateTime);
        fakeToDo.setMDone(true);
        //update
        mToDoDao.update(fakeToDo);

        //assertions
        ToDoModel toDoFromDbAfter = LiveDataTestUtil.getValue(mToDoDao.getFromID(toDoId));
        Assert.assertEquals(toDoFromDbAfter.getMTitle(),"New Title");
    }
}
