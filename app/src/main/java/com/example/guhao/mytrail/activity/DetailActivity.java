package com.example.guhao.mytrail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.guhao.mytrail.R;
import com.example.guhao.mytrail.api.DownloadHelper;
import com.example.guhao.mytrail.data.DetailPlace;

public class DetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String place_id = intent.getStringExtra("place_id");

        Log.d("Place ID:", place_id);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public DetailPlace getPlaceDetail(String place_id){
        DownloadHelper downloadHelper = new DownloadHelper();
        String detail_url = downloadHelper.getUrlPlaceDetail(place_id);
        try{
            String Data = downloadHelper.getResponses(detail_url);
        } catch (Exception e){
            Log.d("Get Detail Error:", e.toString());
        }



    }
}
