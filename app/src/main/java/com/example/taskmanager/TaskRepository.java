package com.example.taskmanager;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;


    TaskRepository(Application application){
        TaskDB db = TaskDB.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getTasks();
    }

    LiveData<List<Task>> getmAllTasks() { return mAllTasks; }

    void insert(Task task){
        TaskDB.databaseWriterExecutor.execute(()-> { mTaskDao.insert(task);});
    }

    void update(Task task) {
        TaskDB.databaseWriterExecutor.execute(()-> { mTaskDao.update(task);});
    }
}
