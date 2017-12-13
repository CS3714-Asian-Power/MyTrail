package com.example.guhao.mytrail.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.guhao.mytrail.R;
import com.example.guhao.mytrail.api.DownloadHelper;
import com.example.guhao.mytrail.api.GoogleAPIService;
import com.example.guhao.mytrail.data.DetailPlace;
import com.google.gson.Gson;


public class DetailActivity extends AppCompatActivity {
    private TextView test_tv;
    private String place_id;
    private ResponseReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Intent intent = getIntent();
//        String place_id = intent.getStringExtra("place_id");
//
//        Log.d("Place ID:", place_id);
        setContentView(R.layout.activity_detail);

        initView();
        if (getIntent() != null){
            place_id = getIntent().getStringExtra("place_id");
            Log.d("place_id_detail", "onCreate: " + place_id);
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        startServiceBroadcaster();
    }

    public void startServiceBroadcaster(){
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver,filter);

        DownloadHelper downloadHelper = new DownloadHelper();
        String the_url = downloadHelper.getUrlPlaceDetail(place_id);
        Intent msgIntent = new Intent(this, GoogleAPIService.class);
        msgIntent.setAction(GoogleAPIService.GET_DETAIL);
        msgIntent.putExtra(GoogleAPIService.URL, the_url);
        startService(msgIntent);
    }

    public void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        test_tv = findViewById(R.id.test_tv);

    }

    public class ResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP =
                "Data_fetched";

        @Override
        public void onReceive(Context context, Intent intent) {
            String json = intent.getStringExtra("response");

            Gson gson = new Gson();
            DetailPlace detailPlace = gson.fromJson(json, DetailPlace.class);
            Log.d("detail_activity", "onReceive: " + detailPlace.getStatus());

        }
    }

}
