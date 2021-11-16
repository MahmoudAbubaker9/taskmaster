package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

            SharedPreferences ShareUserName = PreferenceManager.getDefaultSharedPreferences(Settings.this);
            ShareUserName.edit().putString("UserName",UserName).apply();
            Toast.makeText(getApplicationContext(), "submitted!",Toast.LENGTH_LONG).show();
        }
    });


    }

}