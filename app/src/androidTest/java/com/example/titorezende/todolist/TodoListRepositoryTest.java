package com.example.titorezende.todolist;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * Copyright (C) 2018 Motorola Mobility, Inc.
 * All Rights Reserved.
 * Motorola Mobility Confidential Restricted.
 */
@RunWith(AndroidJUnit4.class)
public class TodoListRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private ToDoRoomDatabase mDatabase;
    //private ToDoDao mToDoDao;
    private ToDoRepository mRepository;

    @Before
    public void initDb() throws Exception {

        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                                                 ToDoRoomDatabase.class)
                        .allowMainThreadQueries()
                        .build();
        mRepository = new ToDoRepository(mDatabase);
        //mToDoDao = mDatabase.toDoDao();
    }
    @After
    public void closeDb() throws Exception {
        mDatabase.close(); //TODO deve ter uma forma melhor
    }

    @Test
    public void initDbTest(){
        Assert.assertNotNull(mRepository);
    }

    private ToDoModel FakeToDoItem() {
        Date date = DateTypeConverter.toDate(Calendar.getInstance().getTimeInMillis());
        return new ToDoModel("Title", "Description",
                             date, false);
    }


    @Test
    public void insertToDoTest() throws InterruptedException {
        List<ToDoModel> toDosFromDb_before = LiveDataTestUtil.getValue(mRepository.toDoList());
        //Method to be tested
        ToDoModel toDo = FakeToDoItem();
        //long toDoId = mToDoDao.insert(toDo);
        mRepository.insertToDo(toDo);
        //assertions
        List<ToDoModel> toDosFromDb_after = LiveDataTestUtil.getValue(mRepository.toDoList());

    }

    @Test
    public void updateToDoTest() throws InterruptedException {
        /*
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
        Assert.assertEquals(toDoFromDbAfter.getMTitle(),"New Title");*/
    }
}
