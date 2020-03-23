package com.example.taskmanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("SELECT * from tasks ORDER BY deadLine ASC")
    LiveData<List<Task>> getTasks();

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Task task);

    @Query("DELETE FROM tasks")
    void deleteAll();
}
