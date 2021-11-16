package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTask = findViewById(R.id.addTask);
        Button allTasks = findViewById(R.id.allTasks);
        Button Settings = findViewById(R.id.SettingsBtn);
        Button UserNameSave = findViewById(R.id.UserNamebtn);
        Button TaskDetail1 = findViewById(R.id.TaskDetail1);
        Button TaskDetail2 = findViewById(R.id.TaskDetail2);
        Button TaskDetail3 = findViewById(R.id.TaskDetail3);


        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTask = new Intent (MainActivity.this , AddTask.class);
                startActivity(goToAddTask);
            }
        });
        allTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTask = new Intent (MainActivity.this , AllTasks.class);
                startActivity(goToAllTask);
            }
        });

        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent (MainActivity.this , Settings.class);
                startActivity(goToSettings);
            }
        });

        TaskDetail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTaskDetail = new Intent (MainActivity.this , TaskDetail.class);
                String Task1 = TaskDetail1.getText().toString();
                goToTaskDetail.putExtra("GetTask",Task1);
                startActivity(goToTaskDetail);
            }
        });

        TaskDetail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTaskDetail = new Intent (MainActivity.this , TaskDetail.class);
                String Task1 = TaskDetail2.getText().toString();
                goToTaskDetail.putExtra("GetTask",Task1);
                startActivity(goToTaskDetail);
            }
        });

        TaskDetail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTaskDetail = new Intent (MainActivity.this , TaskDetail.class);
                String Task1 = TaskDetail3.getText().toString();
                goToTaskDetail.putExtra("GetTask",Task1);
                startActivity(goToTaskDetail);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences ShareUserName = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String defultName = ShareUserName.getString("UserName","User Name Not Found");
        TextView UserViewMain = findViewById(R.id.UserNameDisplay);
        UserViewMain.setText(defultName);

    }
}