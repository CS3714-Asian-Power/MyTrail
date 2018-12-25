package guhao.mytrail.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import guhao.mytrail.R;

public class Setting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;

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
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String color = parent.getItemAtPosition(position).toString();
        if (color.equals("Default")) {
            SharedPreferences pref = getSharedPreferences("Background", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("background", Color.WHITE);
            editor.commit();
        }
        else if (color.equals("Black")) {
            SharedPreferences pref = getSharedPreferences("Background", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("background", Color.BLACK);
            editor.commit();
        }
        else if (color.equals("Green")) {
            SharedPreferences pref = getSharedPreferences("Background", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("background", Color.GREEN);
            editor.commit();
        }
        else if (color.equals("Blue")) {
            SharedPreferences pref = getSharedPreferences("Background", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("background", Color.BLUE);
            editor.commit();
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }
}
