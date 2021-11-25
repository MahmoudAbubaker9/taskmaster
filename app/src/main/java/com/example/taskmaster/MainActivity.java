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
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

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
//        TaskDatabase db = Room.databaseBuilder(getApplicationContext(),TaskDatabase.class, "database-task").allowMainThreadQueries().build();
//        List<Task> allTask = db.userDao().getAll();
//        System.out.println(allTask);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException failure) {
            Log.e("Tutorial", "Could not initialize Amplify", failure);
        }
        Amplify.DataStore.observe(Task.class,
                started -> Log.i("Tutorial", "Observation began."),
                change -> Log.i("Tutorial", change.item().toString()),
                failure -> Log.e("Tutorial", "Observation failed.", failure),
                () -> Log.i("Tutorial", "Observation complete.")
        );

        List<Task> allTask = new ArrayList<>();


        Amplify.DataStore.query(
                Task.class,
                response -> {
                    while (response.hasNext()){
                        Task item = response.next();
                        allTask.add(item);
                        Log.i("type of response", item.getId());
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

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


        List<Task> allTask = new ArrayList<>();
        Amplify.DataStore.query(
                Task.class,
                response -> {
                    while (response.hasNext()){
                        Task item = response.next();
                        allTask.add(item);
                        Log.i("type of response", item.getId());
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

    }
}