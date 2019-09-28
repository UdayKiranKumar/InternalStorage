package com.example.internalstorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2;
    CheckBox check;
    TextView txt1;
    final String filename = "Uday.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.edit1);
        ed2 = (EditText) findViewById(R.id.edit2);
        check = (CheckBox) findViewById(R.id.check);
        txt1 = findViewById(R.id.textview);
    }
    public void dowrite(View view) {
        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            String s = ed1.getText().toString();
            String s2 = ed2.getText().toString();
            fos.write(s.getBytes());
            fos.write(s2.getBytes());
            Toast.makeText(MainActivity.this, "write success", Toast.LENGTH_SHORT).show();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doread(View view) {
        try {
            FileInputStream fis = openFileInput(filename);
            int i = 0;
            String s = " ";
            String s2 = "";
            while ((i = fis.read()) != -1) {
                s = s +s2+ (char) i;
            }
            txt1.setText(s+s2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
        String s1 = sharedPreferences.getString("k1", "");
        String s2 = sharedPreferences.getString("k2", "");
        ed1.setText(s1);
        ed2.setText(s2);
    }

    public void onmethod(View view) {
        if ((check = (CheckBox) view).isChecked()) {
            SharedPreferences sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
            SharedPreferences.Editor spE = sharedPreferences.edit();
            spE.putString("k1", ed1.getText().toString());
            spE.putString("k2", ed2.getText().toString());
            spE.commit();
        }
    }
}
