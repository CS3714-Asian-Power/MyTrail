package com.example.guhao.mytrail.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.example.guhao.mytrail.R;
import com.example.guhao.mytrail.api.DownloadHelper;
import com.example.guhao.mytrail.api.GoogleAPIService;
import com.example.guhao.mytrail.data.DetailPlace;
import com.example.guhao.mytrail.data.Weather;
import com.example.guhao.mytrail.util.WeatherUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback{
    private TextView test_tv;
    private String place_id;
    private ResponseReceiver receiver;
    private ResponseReceiver weatherReceiver;
    private AppBarLayout appbar;
    private Toolbar toolbar;
    private DownloadHelper downloadHelper;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView ratings;
    private TextView address;
    private LinearLayout weatherLayout;
    private LinearLayout review_layout;
    private List<View> weatherList;
    private int favoriteState = 0;

    private GoogleMap mMap;
    double longitude = -80.43301769999999, lat = 37.2432963;
    private LatLng mDefaultLocation = new LatLng(37.18482189999999, -80.3763869);
    private static final int DEFAULT_ZOOM = 15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Intent intent = getIntent();
//        String place_id = intent.getStringExtra("place_id");
//
//        Log.d("Place ID:", place_id);
        setContentView(R.layout.activity_detail);

        findView();
        initView();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        startServiceBroadcaster();
    }

    public void initView(){
        weatherList = new ArrayList<>();
        Intent intent = getIntent();
        if (getIntent() != null){
            place_id = intent.getStringExtra("place_id");
            Log.d("place_id_detail", "onCreate: " + place_id);
            String name = intent.getStringExtra("name");
//            toolbar.setTitle(name);
            getSupportActionBar().setTitle(name);
            Log.d("place_name", "initView: " + name);
        }

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                if (favoriteState == 0) {
                    fab.setImageResource(R.mipmap.ic_star_white_24dp);
                    favoriteState = 1;
                }else if (favoriteState == 1){
                    fab.setImageResource(R.mipmap.ic_star_border_white_24dp);
                    favoriteState = 0;
                }
            }
        });

        for (int i = 0; i < 10; i++){
            View view = LayoutInflater.from(this).inflate(R.layout.layout_weather_view, null);
            weatherList.add(view);
            weatherLayout.addView(view);
        }

        appbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("touch", "onTouch: ");
                Intent intent = new Intent(DetailActivity.this, PhotoActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void startServiceBroadcaster(){
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver,filter);

        IntentFilter filter2 = new IntentFilter(ResponseReceiver.ACTION_WEATHER);
        filter2.addCategory(Intent.CATEGORY_DEFAULT);
        weatherReceiver = new ResponseReceiver();
        registerReceiver(weatherReceiver, filter2);

        downloadHelper = new DownloadHelper();
        String the_url = downloadHelper.getUrlPlaceDetail(place_id);
        Intent msgIntent = new Intent(this, GoogleAPIService.class);
        msgIntent.setAction(GoogleAPIService.GET_DETAIL);
        msgIntent.putExtra(GoogleAPIService.URL, the_url);
        startService(msgIntent);

    }

    public void findView(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appbar = findViewById(R.id.app_bar);
        weatherLayout = findViewById(R.id.weather_linearlayout);
        //ratingBar = findViewById(R.id.rating_bar);
//        test_tv = findViewById(R.id.test_tv);
//        test_iv = findViewById(R.id.test_iv);
        ratings = findViewById(R.id.detail_rating);
        address = findViewById(R.id.address);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        review_layout = findViewById(R.id.review_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(mDefaultLocation);
//        markerOptions.title(mDefaultLocation.latitude + " : " + mDefaultLocation.longitude);
//        mMap.addMarker(markerOptions);
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mDefaultLocation));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        Log.d("Detail View:", "Map Is ready");

    }

    public class ResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP =
                "Data_fetched";
        public static final String ACTION_WEATHER = "get_weather";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("intent_action", "onReceive: " + intent.getAction());
            Gson gson = new Gson();
            if (intent.getAction().equals(ACTION_RESP)) {
                String json = intent.getStringExtra("response");

                DetailPlace detailPlace = gson.fromJson(json, DetailPlace.class);
                Log.d("detail_activity", "onReceive: " + detailPlace.getStatus());

                DetailPlace.ResultBean.GeometryBean.LocationBean locationBean = detailPlace.getResult().getGeometry().getLocation();
                String weather_url = downloadHelper.getUrlWeather(locationBean.getLat(), locationBean.getLng());
                Intent msgIntent = new Intent(getApplicationContext(), GoogleAPIService.class);
                msgIntent.setAction(GoogleAPIService.GET_WEATHER);
                msgIntent.putExtra(GoogleAPIService.URL, weather_url);
                startService(msgIntent);

                ratings.setText(detailPlace.getResult().getRating() + "");
                address.setText(detailPlace.getResult().getFormatted_address());
                longitude = detailPlace.getResult().getGeometry().getLocation().getLng();
                lat = detailPlace.getResult().getGeometry().getLocation().getLat();
                // mDefaultLocation = new LatLng(lat, longitude);
                LatLng latLng = new LatLng(lat, longitude);
                Log.d("Detail View:", lat + "," + longitude);
                Log.d("Detail View:", "Detail Is ready");
                if (mMap != null) {
                    Log.d("Detail View:", "Detail Is ready");
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    mMap.addMarker(markerOptions);
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    //move map camera
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                }

                setupReview(detailPlace);
                changeBackgroundImage(detailPlace);
            }
            if (intent.getAction().equals(ACTION_WEATHER)){
                String json = intent.getStringExtra("response");
                Weather weather = gson.fromJson(json,Weather.class);
                Log.d("get_weather", "onReceive: " + weather.getCity().getName());
                List<Weather.ListBean> list = weather.getList();
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                Log.d("weekofday", "onReceive: " + day);
                for (int i = 0; i < list.size(); i++){
                    View view = weatherList.get(i);
                    TextView temp = view.findViewById(R.id.temperature);
                    ImageView im = view.findViewById(R.id.weather_condition);
                    TextView weekday = view.findViewById(R.id.weekday);
                    double min = list.get(i).getTemp().getMin();
                    double max = list.get(i).getTemp().getMax();
                    min = WeatherUtil.fromK(min);
                    max = WeatherUtil.fromK(max);
                    int temp_id = list.get(i).getWeather().get(0).getId();
                    Log.d("icon", "onReceive: " + temp_id);
                    int icon_id = WeatherUtil.getWeatherID(temp_id);
                    String t = (int)min + "˚ - " + (int)max + "˚";
                    temp.setText(t);
                    weekday.setText(WeatherUtil.getWeekDay((day+i)%7));
                    Picasso.with(getApplicationContext()).load(icon_id).into(im);
                }
            }
        }
    }

    public void setupReview(DetailPlace place){

        List<DetailPlace.ResultBean.ReviewsBean> reviews = place.getResult().getReviews();
        if (reviews != null) {
            for (DetailPlace.ResultBean.ReviewsBean review : reviews) {
                View view = LayoutInflater.from(this).inflate(R.layout.layout_detail_review, null);
                TextView name = view.findViewById(R.id.reviewer);
                RatingBar ratingBar = view.findViewById(R.id.rating);
                TextView time = view.findViewById(R.id.review_time);
                TextView review_content = view.findViewById(R.id.layout_detail_review_review);
                TextView rating = view.findViewById(R.id.layout_detail_review_rating);
                name.setText(review.getAuthor_name());
                time.setText(review.getRelative_time_description());
                //do not show if there is no text
                review_content.setText(review.getText());
                Log.d("review", "setupReview: " + review.getRating());
                ratingBar.setRating(review.getRating());
                rating.setText(review.getRating() + "");
                review_layout.addView(view);
            }
        }
    }

    public void changeBackgroundImage(DetailPlace place){
        if (place.getResult().getPhotos()!= null) {
            Log.d("background_image", "changeBackgroundImage: ");
            String thumbnail_URL = downloadHelper.getPhotoURL(800, place.getResult().getPhotos().get(0).getPhoto_reference());
            Log.d("photo_url", "changeBackgroundImage: " + thumbnail_URL);
//            Picasso.with(this).setLoggingEnabled(true);

            Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    Log.d("get_image", "onBitmapLoaded: get image from picasso" );
                    BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
//                    drawable.setGravity(ImageView.ScaleType.CENTER_CROP.ordinal());
                    appbar.setBackground(drawable);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    Log.d("get_image", "onBitmapFailed: ");
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };
            appbar.setTag(target);
//            final ImageView imageView = new ImageView(this);
            Picasso.with(this).load(thumbnail_URL).into(target);
        }
//        Picasso.with(this).load(thumbnail_URL).into(test_iv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
