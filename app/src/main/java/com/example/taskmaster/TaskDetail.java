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
        String taskName = getIntent.getExtras().getString("GetTask","Empty");
        textChange.setText(taskName);
    }
}