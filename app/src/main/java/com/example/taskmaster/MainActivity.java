package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String teamId = "";

    Handler handler;

    RecyclerView taskRecyclerView;

    List<Task> allTask = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTask = findViewById(R.id.addTask);
        Button allTasks = findViewById(R.id.allTasks);
        Button Settings = findViewById(R.id.SettingsBtn);
        Button SignIn = findViewById(R.id.sginin_btn);
        Button logOut = findViewById(R.id.signout_btn);
        taskRecyclerView = findViewById(R.id.TaskRecyclerView);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException failure) {
            Log.e("Tutorial", "Could not initialize Amplify", failure);
        }

        handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message message) {
                        if (message.what==1){
                            taskRecyclerView.getAdapter().notifyDataSetChanged();
                        }
                        return false;
                    }
                });





//        Team teamOne = Team.builder().name("Team 1").build();
//        Team teamTwo = Team.builder().name("Team 2").build();
//        Team teamThree = Team.builder().name("Team 3").build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(teamOne),
//                response -> Log.i("TaskMaster", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("TaskMaster", "Create failed", error)
//        );
//        Amplify.API.mutate(
//                ModelMutation.create(teamTwo),
//                response -> Log.i("TaskMaster", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("TaskMaster", "Create failed", error)
//        );
//        Amplify.API.mutate(
//                ModelMutation.create(teamThree),
//                response -> Log.i("TaskMaster", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("TaskMaster", "Create failed", error)
//        );



//        Amplify.DataStore.observe(Task.class,
//                started -> Log.i("Tutorial", "Observation began."),
//                change -> Log.i("Tutorial", change.item().toString()),
//                failure -> Log.e("Tutorial", "Observation failed.", failure),
//                () -> Log.i("Tutorial", "Observation complete.")
//        );

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signInWithWebUI(
                        MainActivity.this,
                        result -> Log.i("AuthQuickStart", result.toString()),
                        error -> Log.e("AuthQuickStart", error.toString())
                );

            }
        });

        uploadFile();

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
                SharedPreferences ShareUserName = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                ShareUserName.edit().putString("UserName", "No User").apply();
                String defultName = ShareUserName.getString("UserName","User Name Not Found");
                TextView UserViewMain = findViewById(R.id.UserNameDisplay);
                UserViewMain.setText(defultName);
            }
        });



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

    private void uploadFile() {
        File exampleFile = new File(getApplicationContext().getFilesDir(), "ExampleKey");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
            writer.append("Example file contents");
            writer.close();
        } catch (Exception exception) {
            Log.e("MyAmplifyApp", "Upload failed", exception);
        }

        Amplify.Storage.uploadFile(
                "ExampleKey",
                exampleFile,
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }


    public void createTasksList() {



        Log.i("track", "after Handler");
        Amplify.API.query(
                ModelQuery.list(Team.class, Team.NAME.contains(teamId)),
                response -> {
                    for (Team team : response.getData()) {
                        allTask = team.getTasks();
                        Log.i("test1", team.getName());

                    }
                    handler.sendEmptyMessage(1);
                    Log.i("track", "after message");
                    Log.i("MyAmplifyApp111", allTask.toString());

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        Button SignIn = findViewById(R.id.sginin_btn);
        Button logOut = findViewById(R.id.signout_btn);


        SharedPreferences ShareUserName = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String defultName = ShareUserName.getString("UserName","No Data");
        TextView UserViewMain = findViewById(R.id.UserNameDisplay);
        UserViewMain.setText(defultName);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String teamId = sharedPreferences.getString("teamId", "the user didn't add a name yet!");
        this.teamId = teamId;



        createTasksList();


        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskRecyclerView.setAdapter(new TaskAdapter(allTask));

        Amplify.Auth.fetchAuthSession(
                result -> {
                    if (result.isSignedIn()){
                        SignIn.setVisibility(View.INVISIBLE);

                        SharedPreferences ShareUserName1 = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        ShareUserName.edit().putString("UserName", Amplify.Auth.getCurrentUser().getUsername() +"  Tasks").apply();
                        String defultName1 = ShareUserName1.getString("UserName","User Name Not Found");
                        TextView UserViewMain1 = findViewById(R.id.UserNameDisplay);
                        UserViewMain1.setText(defultName1);

                    }
                    else logOut.setVisibility(View.INVISIBLE);
                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
    }
}