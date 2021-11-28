package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        EditText addTitle = findViewById(R.id.addtitle);
        EditText addBody = findViewById(R.id.addbody);
        EditText addState = findViewById(R.id.addstate);
        Button addTask = findViewById(R.id.addtask_btn);
        RadioButton radioButton1=findViewById(R.id.radioTeam1);
        RadioButton radioButton2=findViewById(R.id.radioTeam2);
        RadioButton radioButton3=findViewById(R.id.radioTeam3);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titles = addTitle.getText().toString();
                String bodys = addBody.getText().toString();
                String states = addState.getText().toString();




                String teamSelection = null;
                if(radioButton1.isChecked()){
                    teamSelection="1";
                }
                else if(radioButton2.isChecked()){
                    teamSelection="2";
                }
                else if(radioButton3.isChecked()){
                    teamSelection="3";
                }


                Task task = Task.builder().title(titles).body(bodys).state(states).teamId(teamSelection).build();

                Amplify.DataStore.save(
                        task,
                        success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
                );

                Intent goToMain = new Intent(AddTask.this, MainActivity.class);
                startActivity(goToMain);
            }
        });

    }


}