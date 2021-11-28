package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


    Button UserNameSave = findViewById(R.id.UserNamebtn);

    UserNameSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText UserNameEdit = findViewById(R.id.UserNameEdit);
            String UserName = UserNameEdit.getText().toString();
            RadioButton radioButton1=findViewById(R.id.radioSitting1);
            RadioButton radioButton2=findViewById(R.id.radioSitting2);
            RadioButton radioButton3=findViewById(R.id.radioSitting3);

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


            SharedPreferences ShareUserName = PreferenceManager.getDefaultSharedPreferences(Settings.this);
            ShareUserName.edit().putString("UserName",UserName +"  Tasks").apply();
            ShareUserName.edit().putString("Team",teamSelection ).apply();
            Toast.makeText(getApplicationContext(), "submitted!",Toast.LENGTH_LONG).show();
        }
    });

    }

}