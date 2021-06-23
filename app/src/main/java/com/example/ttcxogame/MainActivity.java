package com.example.ttcxogame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.ttcxogame";
    protected String Xname;
    protected String Oname;
    protected boolean HVHOnOff;

    public void GameMode(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.ttcxogame",Context.MODE_PRIVATE);

        RadioButton HVH = findViewById(R.id.RBHVH);
        RadioButton HVB = findViewById(R.id.RBHVB);
        if(HVH.isChecked())
            this.HVHOnOff = true;
        this.HVHOnOff = false;
        if(HVH.isChecked()||HVB.isChecked()) {

            sharedPreferences.edit().putBoolean("GameModeHVH", HVH.isChecked()).apply();
            sharedPreferences.edit().putBoolean("GameModeHVB", HVB.isChecked()).apply();
        }


    }
    public void ToastName(View view){
        Toast.makeText(this,"I P",Toast.LENGTH_SHORT).show();
    }

    public void startGame(View view) {
        EditText XName = findViewById(R.id.ETTXName);
        EditText OName = findViewById(R.id.ETTOName);
        RadioButton HVH = findViewById(R.id.RBHVH);
        if(HVH.isChecked())
            this.HVHOnOff = true;
        this.HVHOnOff = false;

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.ttcxogame",Context.MODE_PRIVATE);
        try{
            sharedPreferences.edit().putString("Userx",XName.getText().toString()).apply();
            sharedPreferences.edit().putString("UserO",OName.getText().toString()).apply();
            this.Xname = XName.getText().toString();
            this.Oname = OName.getText().toString();
            Log.i("Pref saved", "Names has been saved");

        }catch (Exception e){
            Log.e("Exception", "Failed to save Names");
        }
        Intent intent = new Intent(this, GameScreenActivity.class);

        intent.putExtra("Mode.cad", HVH.isChecked());
        intent.putExtra("com.example.ttcxogame", this.Oname);
        intent.putExtra("cocm.example.ttcxogame", this.Xname);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Xname",Xname);
        savedInstanceState.putString("Oname",Oname);
        savedInstanceState.putBoolean("GameMode",HVHOnOff);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        RadioButton HVH = findViewById(R.id.RBHVH);
        EditText XName = findViewById(R.id.ETTXName);
        EditText OName = findViewById(R.id.ETTOName);
        HVH.setChecked(savedInstanceState.getBoolean("GameMode"));
        XName.setText(savedInstanceState.getString("Xname"));
        OName.setText(savedInstanceState.getString("Oname"));

     }

}













































