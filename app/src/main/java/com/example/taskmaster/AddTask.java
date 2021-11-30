package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class AddTask extends AppCompatActivity {

    private String imgName;
    private Uri imgData;
    List<Task> allTask = new ArrayList<>();
    Handler handler;
    RecyclerView taskRecyclerView;
    String teamSelection;
    Team team;
    Intent chooseFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        EditText addTitle = findViewById(R.id.addtitle);
        EditText addBody = findViewById(R.id.addbody);
        EditText addState = findViewById(R.id.addstate);
        Button addTask = findViewById(R.id.addtask_btn);
        Button uploadImg = findViewById(R.id.uploadimg_btn);
        RadioButton radioButton1=findViewById(R.id.radioTeam1);
        RadioButton radioButton2=findViewById(R.id.radioTeam2);
        RadioButton radioButton3=findViewById(R.id.radioTeam3);

        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fileChoose = new Intent(Intent.ACTION_GET_CONTENT);
                fileChoose.setType("*/*");
                fileChoose = Intent.createChooser(fileChoose, "choose file");
                startActivityForResult(fileChoose, 1234);
            }
        });



        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titles = addTitle.getText().toString();
                String bodys = addBody.getText().toString();
                String states = addState.getText().toString();

                if (imgData != null) {
                    try {
                        InputStream exampleInputStream = getContentResolver().openInputStream(imgData);
                        Amplify.Storage.uploadInputStream(
                                imgName,
                                exampleInputStream,
                                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
                        );
                    } catch (FileNotFoundException error) {
                        Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
                    }
                }

                handler = new Handler(Looper.getMainLooper(),
                        new Handler.Callback() {
                            @Override
                            public boolean handleMessage(@NonNull Message message) {
                                Task task = Task.builder().title(titles).body(bodys).state(states).teamId(team.getId()).image(imgName).build();
                                Amplify.API.mutate(
                                        ModelMutation.create(task),
                                        saved -> Log.i("teamUpdate", "Team Update"),
                                        failure -> Log.e("teamUpdate", "STeam failure", failure)
                                );
                                return false;
                            }
                        });

                if(radioButton1.isChecked()){
                    teamSelection="Team 1";
                }
                else if(radioButton2.isChecked()){
                    teamSelection="Team 2";
                }
                else if(radioButton3.isChecked()){
                    teamSelection="Team 3";
                }

                getTeam();
                Intent goToMain = new Intent(AddTask.this, MainActivity.class);
                startActivity(goToMain);
            }
        });


    }

    public void getTeam(){
        Amplify.API.query(
                ModelQuery.list(Team.class, Team.NAME.contains(teamSelection)),
                response -> {
                    for (Team team : response.getData()) {
                        this.team = team;
                        Log.i("MyAmplifyApp", team.getName());

                    }
                    handler.sendEmptyMessage(1);
                    Log.i("track", "after message");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = new File(data.getData().getPath());
        imgName = file.getName();
        imgData = data.getData();
    }




}