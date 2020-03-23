package com.example.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.MyViewHolder> {

    private List<Task> mTasks;
    private final LayoutInflater mInflater;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ConstraintLayout constraintLayout;
        public MyViewHolder(ConstraintLayout v){
            super(v);
            constraintLayout = v;
        }
    }

    public  TaskListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        ConstraintLayout v = (ConstraintLayout) mInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        TextView namaMatkul = (TextView) holder.constraintLayout.getViewById(R.id.asd1);
        TextView deadLine = (TextView) holder.constraintLayout.getViewById(R.id.asd2);
        TextView deskripsiTugas = (TextView) holder.constraintLayout.getViewById(R.id.asd3);

        if (mTasks != null) {
            Task current = mTasks.get(position);
            namaMatkul.setText(current.getNamaMatkul());
            deadLine.setText(new SimpleDateFormat("dd MMM yyyy").format(current.getDeadLineTimestamp()));
            deskripsiTugas.setText(current.getDeskripsiTugas());
        } else {
            namaMatkul.setText("No Task");
        }
    }

    public  void setTasks(List<Task> tasks){
        Collections.sort(tasks);
        mTasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }

}
