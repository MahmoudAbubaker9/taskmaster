package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import java.io.File;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        Intent getIntent = getIntent();
        TextView textChange = findViewById(R.id.TitleDetail);
        TextView bodyChange = findViewById(R.id.bodyDetail);
        TextView stateChange = findViewById(R.id.StateDetail);
        String taskName = getIntent.getExtras().getString("taskName");
        String taskState = getIntent.getExtras().getString("taskState");
        String taskBody = getIntent.getExtras().getString("taskBody");
        ImageView imageView = findViewById(R.id.imageupload);
        textChange.setText(taskName);
        bodyChange.setText(taskBody);
        stateChange.setText(taskState);

        Intent intent = getIntent();

        //get Image from storage
        if (intent.getExtras().getString("taskImage") != null) {
            Amplify.Storage.downloadFile(
                    intent.getExtras().getString("taskImage"),
                    new File(getApplicationContext().getFilesDir() + "/" + intent.getExtras().getString("taskImage") + ".jpg"),
                    result -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(result.getFile().getPath());
                        imageView.setImageBitmap(bitmap);
                        Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName());
                    },
                    error -> Log.e("MyAmplifyApp", "Download Failure", error)
            );
        }
    }
}