package com.example.guhao.mytrail.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.guhao.mytrail.R;

public class Setting extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner spinner;
    private RadioButton cd;
    private RadioButton fd;

    private int tempUnit;
    private int background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        cd = (RadioButton) findViewById(R.id.cd);
        fd = (RadioButton) findViewById(R.id.fd);
        cd.setOnClickListener(this);
        fd.setOnClickListener(this);

        if (savedInstanceState != null) {
            tempUnit = savedInstanceState.getInt("Unit");
            background = savedInstanceState.getInt("Background");
            if (tempUnit == 0) {
                cd.setChecked(true);
            }
            else if (tempUnit == 1) {
                fd.setChecked(true);
            }
            spinner.setSelection(background);
        }
        else {
            cd.setChecked(true);
            tempUnit = 0;
            background = 0;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String color = parent.getItemAtPosition(position).toString();
        if (color.equals("Default")) {
            background = 0;

            SharedPreferences prefs = getSharedPreferences("Background", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("background", 0);
            editor.commit();
        }
        else if (color.equals("Black")) {
            background = 1;

            SharedPreferences prefs = getSharedPreferences("Background", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("background", 1);
            editor.commit();
        }
        else if (color.equals("Green")) {
            background = 2;

            SharedPreferences prefs = getSharedPreferences("Background", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("background", 2);
            editor.commit();
        }
        else if (color.equals("Blue")) {
            background = 3;

            SharedPreferences prefs = getSharedPreferences("Background", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("background", 3);
            editor.commit();
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cd) {

            tempUnit = 0;
            Toast.makeText(this, "Temperature Unit has been changed to Celsius", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.fd) {

            tempUnit = 1;
            Toast.makeText(this, "Temperature Unit has been changed to Fahrenheit", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Unit", tempUnit);
        outState.putInt("Background", background);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainTrail.class));
    }
}
