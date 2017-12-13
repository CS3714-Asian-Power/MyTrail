package com.example.guhao.mytrail.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guhao.mytrail.R;
import com.example.guhao.mytrail.api.DownloadHelper;
import com.example.guhao.mytrail.api.GoogleAPIService;
import com.example.guhao.mytrail.data.DetailPlace;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class DetailActivity extends AppCompatActivity {
    private TextView test_tv;
    private String place_id;
    private ResponseReceiver receiver;
    private AppBarLayout appbar;
    private DownloadHelper downloadHelper;
    private ImageView test_iv;

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

        downloadHelper = new DownloadHelper();
        String the_url = downloadHelper.getUrlPlaceDetail(place_id);
        Intent msgIntent = new Intent(this, GoogleAPIService.class);
        msgIntent.setAction(GoogleAPIService.GET_DETAIL);
        msgIntent.putExtra(GoogleAPIService.URL, the_url);
        startService(msgIntent);
    }

    public void initView(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appbar = findViewById(R.id.app_bar);
        test_tv = findViewById(R.id.test_tv);
//        test_iv = findViewById(R.id.test_iv);

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
            changeBackgroundImage(detailPlace);
        }
    }

    public void changeBackgroundImage(DetailPlace place){
        String thumbnail_URL = downloadHelper.getPhotoURL(400, place.getResult().getPhotos().get(0).getPhoto_reference());
        Log.d("photo_url", "changeBackgroundImage: " + thumbnail_URL);
        Picasso.with(this).load(thumbnail_URL).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                appbar.setBackground(new BitmapDrawable(getResources(),bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

//        Picasso.with(this).load(thumbnail_URL).into(test_iv);
    }

}
