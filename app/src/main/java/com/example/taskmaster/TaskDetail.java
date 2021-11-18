package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        Intent getIntent = getIntent();
        TextView textChange = findViewById(R.id.TitleDetail);
        TextView bodyChange = findViewById(R.id.textViewDetail);
        TextView stateChange = findViewById(R.id.StateDetail);
        String taskName = getIntent.getExtras().getString("taskName");
        String taskState = getIntent.getExtras().getString("taskState");
        String taskBody = getIntent.getExtras().getString("taskBody");
        textChange.setText(taskName);
        bodyChange.setText(taskState);
        stateChange.setText(taskBody);
    }
}