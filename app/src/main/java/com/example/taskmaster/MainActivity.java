package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;

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
        TaskDatabase db = Room.databaseBuilder(getApplicationContext(),TaskDatabase.class, "database-task").allowMainThreadQueries().build();
        List<Task> allTask = db.userDao().getAll();
        System.out.println(allTask);

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

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