package com.example.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private TaskViewModel mTaskViewModel;

    int request_Code = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.tasks);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final TaskListAdapter mAdapter = new TaskListAdapter(this);
        recyclerView.setAdapter(mAdapter);

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mTaskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                mAdapter.setTasks(tasks);
            }
        });

    }
    public void onClick(View view){
        Intent i = new Intent("com.example.taskmanager.SecondActivity");
        Bundle b = new Bundle();
        i.putExtras(b);
        startActivityForResult(i, request_Code);
    }
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                Task newTask = (Task) data.getSerializableExtra("newTask");
                mTaskViewModel.insert(newTask);
                Toast.makeText(this, "Task Berhasil Terbuat", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        for(int i = 0; i < mTaskViewModel.getAllTasks().getValue().size(); i++){
            Task t = mTaskViewModel.getAllTasks().getValue().get(i);
            mTaskViewModel.update(t);
        }
    }
}

