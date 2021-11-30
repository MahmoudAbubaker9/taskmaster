package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.HashMap;
import java.util.Map;

public class Settings extends AppCompatActivity {

    String teamSelection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioButton radioButton1=findViewById(R.id.radioSitting1);
        RadioButton radioButton2=findViewById(R.id.radioSitting2);
        RadioButton radioButton3=findViewById(R.id.radioSitting3);


        Map< String,String> teamList = new HashMap<>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Team.class),
                response -> {

                    for (Team team : response.getData()) {
                        Log.i("type of response", team.getName());
                        teamList.put(team.getName(),team.getId());

                    }
                },
                error -> Log.e("MasterTask", error.toString(), error)
        );


    Button UserNameSave = findViewById(R.id.UserNamebtn);

    UserNameSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            EditText UserNameEdit = findViewById(R.id.UserNameEdit);
//            String UserName = UserNameEdit.getText().toString();





            if(radioButton1.isChecked()){
                teamSelection="Team 1";
            }
            else if(radioButton2.isChecked()){
                teamSelection="Team 2";
            }
            else if(radioButton3.isChecked()){
                teamSelection="Team 3";
            }



            SharedPreferences ShareUserName = PreferenceManager.getDefaultSharedPreferences(Settings.this);
            ShareUserName.edit().putString("UserName", Amplify.Auth.getCurrentUser().getUsername() +"  Tasks").apply();

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
            sharedPreferences.edit().putString("teamId",teamSelection).apply();

            //            ShareUserName.edit().putString("teamId",teamSelection ).apply();
//            ShareUserName.edit().putString("teamId", teamList.get(teamSelection)).apply();
            Log.i("test33", teamSelection);
            Toast.makeText(getApplicationContext(), "submitted!",Toast.LENGTH_LONG).show();
        }
    });

    }

}