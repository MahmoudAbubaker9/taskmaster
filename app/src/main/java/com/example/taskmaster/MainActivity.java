package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTask = findViewById(R.id.addTask);
        Button allTasks = findViewById(R.id.allTasks);
        Button Settings = findViewById(R.id.SettingsBtn);
        Button UserNameSave = findViewById(R.id.UserNamebtn);
//        TaskDatabase db = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "database-name").allowMainThreadQueries().build();
//        TaskDao userDao = db.userDao();
//        List<Task> allTask = userDao.getAll();

        List<Task> allTask = new ArrayList<Task>();
        Task Task1 = new Task("Title1", "body1", State.IN_PROGRESS );
        Task Task2 = new Task("Title2", "body2", State.NEW );
        Task Task3 = new Task("Title3", "body3", State.NEW );
        Task Task4 = new Task("Title4", "body4", State.NEW );
        allTask.add(Task1);
        allTask.add(Task2);
        allTask.add(Task3);
        allTask.add(Task4);

        RecyclerView taskRecyclerView = findViewById(R.id.TaskRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskRecyclerView.setAdapter(new TaskAdapter(allTask));


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