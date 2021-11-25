package com.example.taskmaster;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    List<Task> allTask = new ArrayList<Task>();

    public TaskAdapter(List<Task> allTask) {
        this.allTask = allTask;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public Task task;
        public View itemView;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.task = allTask.get(position);
        TextView titleText = holder.itemView.findViewById(R.id.Titleholder);
        TextView bodyText = holder.itemView.findViewById(R.id.bodyholder);
        TextView stateText = holder.itemView.findViewById(R.id.stateholder);

        titleText.setText(holder.task.getTitle());
        bodyText.setText(holder.task.getBody());
        stateText.setText(holder.task.getState().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent taskDetailsIntent = new Intent(v.getContext(), TaskDetail.class);
                taskDetailsIntent.putExtra("taskName",holder.task.getTitle());
                taskDetailsIntent.putExtra("taskBody", holder.task.getBody());
                taskDetailsIntent.putExtra("taskState", holder.task.getState().toString());
                v.getContext().startActivity(taskDetailsIntent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return allTask.size();
    }
}