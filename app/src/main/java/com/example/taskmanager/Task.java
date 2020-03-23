package com.example.taskmanager;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

@Entity(tableName = "tasks")
public class Task implements Comparable<Task>, Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name="namaMatkul")
    private String namaMatkul;

    @ColumnInfo(name="deadLine")
    private String deadLine;

    @ColumnInfo(name="deskripsiTugas")
    private String deskripsiTugas;

    @ColumnInfo(name="isDone")
    private boolean isDone;

    public Task(String namaMatkul, String deadLine, String deskripsiTugas, boolean isDone, int id){
        this(namaMatkul, Timestamp.valueOf(deadLine), deskripsiTugas, isDone, id);
    }

    public Task(String namaMatkul, Timestamp deadLine, String deskripsiTugas){ this(namaMatkul, deadLine, deskripsiTugas, false);}
    public Task(String namaMatkul, Timestamp deadLine, String deskripsiTugas, boolean isDone){this(namaMatkul, deadLine, deskripsiTugas, isDone, 0);}
    public Task(String namaMatkul, Timestamp deadLine, String deskripsiTugas, boolean isDone ,int id) {
        this.namaMatkul = namaMatkul;
        this.deadLine = deadLine.toString();
        this.deskripsiTugas = deskripsiTugas;
        this.isDone=isDone;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }
    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public Timestamp getDeadLineTimestamp(){ return Timestamp.valueOf(deadLine);}
    public String getDeadLine() {return this.deadLine;}
    public void setDeadLine(Timestamp deadLine) {this.deadLine = deadLine.toString();}

    public String getDeskripsiTugas() {
        return deskripsiTugas;
    }
    public void setDeskripsiTugas(String deskripsiTugas) {
        this.deskripsiTugas = deskripsiTugas;
    }

    public boolean isDone() { return isDone; }
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public int getId(){ return id;}
    public void setId(int id){ this.id = id;}

    public Task getTask() { return  this;}


    @Override
    public int compareTo(Task task) {
        return deadLine.compareTo(task.deadLine);
    }
}
