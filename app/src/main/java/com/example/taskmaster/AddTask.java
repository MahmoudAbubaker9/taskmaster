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

        Button messageShow = findViewById(R.id.addtask_btn);
        messageShow.setOnClickListener(new View.OnClickListener() {

            EditText addTitle = findViewById(R.id.addtitle);
            String titles = addTitle.getText().toString();

            EditText addBody = findViewById(R.id.addbody);
            String bodys = addBody.getText().toString();

            EditText addState = findViewById(R.id.addstate);
            String states = addState.getText().toString();

            Task addtask = new Task(titles,bodys,State.NEW);

            @Override
            public void onClick(View v) {

                TaskDatabase db = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "database-name").build();
                TaskDao taskDao = db.userDao();
                taskDao.insertAll(addtask);
                Intent goToMain = new Intent(AddTask.this, MainActivity.class);
                startActivity(goToMain);
            }
        });

    }


}