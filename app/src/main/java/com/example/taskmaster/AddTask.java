package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        EditText addTitle = findViewById(R.id.addtitle);
        EditText addBody = findViewById(R.id.addbody);
        EditText addState = findViewById(R.id.addstate);
        Button addTask = findViewById(R.id.addtask_btn);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titles = addTitle.getText().toString();
                String bodys = addBody.getText().toString();
                String states = addState.getText().toString();
                Task addNewtask = new Task(titles,bodys,State.IN_PROGRESS);
                TaskDatabase db = Room.databaseBuilder(getApplicationContext(),TaskDatabase.class, "database-task").allowMainThreadQueries().build();
                TaskDao taskDao = db.userDao();
                taskDao.savedata(addNewtask);
                Intent goToMain = new Intent(AddTask.this, MainActivity.class);
                startActivity(goToMain);
            }
        });

    }


}