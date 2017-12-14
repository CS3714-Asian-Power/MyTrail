package com.example.guhao.mytrail.activity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.guhao.mytrail.R;
import com.example.guhao.mytrail.adapter.MyAdapter;
import com.example.guhao.mytrail.api.DownloadHelper;
import com.example.guhao.mytrail.api.GoogleAPIService;
import com.example.guhao.mytrail.data.Place;
import com.example.guhao.mytrail.database.DBOpenHelper;
import com.example.guhao.mytrail.database.DatabaseManager;
import com.example.guhao.mytrail.listener.RecyclerItemClickListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.Locale;

public class MainTrail extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MenuItem filterItem;

    //add G Map

    DownloadHelper downloadHelper;
    double longitude = -80.43301769999999, lat = 37.2432963;
    String activity = "hiking";
    private ResponseReceiver receiver;
    private final static int MY_PERMISSION_ACCESS_COURSE_LOCATION=1;
    private FusedLocationProviderClient mFusedLocationClient;

    DatabaseManager manager;

    View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trail);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkPermission();
        startServiceBroadcaster();
        initView();

        view1 = this.getWindow().getDecorView();
        SharedPreferences setting = getSharedPreferences("Background", Context.MODE_PRIVATE);
        if (setting.getInt("background", Color.WHITE) == Color.BLACK) {
            view1.setBackgroundColor(Color.BLACK);
        }
        else if (setting.getInt("background", Color.WHITE) == Color.GREEN) {
            view1.setBackgroundColor(Color.GREEN);
        }
        else if (setting.getInt("background", Color.WHITE) == Color.BLUE) {
            view1.setBackgroundColor(Color.BLUE);
        }
        else {
            view1.setBackgroundColor(Color.WHITE);
        }
    }

    public void startServiceBroadcaster(){
        //registering a local broadcast receiver that is activated when "movies_fetched"
        //action happens
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);

        downloadHelper = new DownloadHelper();
        String the_url = downloadHelper.getUrlCoordinate(lat,longitude,1000,activity);
        Log.d("URl", the_url);
        Intent msgIntent = new Intent(this, GoogleAPIService.class);
        msgIntent.setAction(GoogleAPIService.GET_RESULT);
        msgIntent.putExtra(GoogleAPIService.URL, the_url);
        startService(msgIntent);
    }

    public void startCityIntent(String city){

        String new_url = downloadHelper.getUrlCityName(city,1000,activity);
        Intent msgIntent = new Intent(this, GoogleAPIService.class);
        msgIntent.setAction(GoogleAPIService.GET_RESULT);
        msgIntent.putExtra(GoogleAPIService.URL, new_url);
        startService(msgIntent);

    }

    public void startFilterIntent(String activity, String radius) {
        int r = Integer.parseInt(radius);
        String the_url = downloadHelper.getUrlCoordinate(lat,longitude,r,activity);
        Intent msgIntent = new Intent(this, GoogleAPIService.class);
        msgIntent.setAction(GoogleAPIService.GET_RESULT);
        msgIntent.putExtra(GoogleAPIService.URL, the_url);
        startService(msgIntent);
    }


    public void checkPermission(){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkLocationPermission()){
                mFusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    // Logic to handle location object
                                    double latitude = location.getLatitude();
                                    double lon = location.getLongitude();
//                                    Toast.makeText(getApplicationContext(),lat + " " + lon, Toast.LENGTH_SHORT).show();
                                    longitude = lon;
                                    lat = latitude;
                                }
                            }
                        });
            }
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayService()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }
    }

    public void initView(){
        manager = new DatabaseManager(this);
        manager.open();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainTrail.this, MapView.class);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", lat);
                //  intent.putExtra("place_id", temp.get(position).getPlace_id());
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainTrail.this, DetailActivity.class);
                intent.putExtra("place_id", mAdapter.getPlace(position).getPlace_id());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

//        //test
//        List<Place> temp;
//        temp = manager.getAllRecords(DBOpenHelper.RESULT_TABLE_ID);
//
//     //   Log.d("Place List", temp.get(0).getName());
//
//        mAdapter = new MyAdapter(temp, this);
//        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_trail, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        filterItem = menu.findItem(R.id.filter);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchItem.getActionView();

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // This is your adapter that will be filtered
//                Toast.makeText(getApplicationContext(),"textChanged :"+newText,Toast.LENGTH_LONG).show();

                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                // **Here you can get the value "query" which is entered in the search box.**

//                Toast.makeText(getApplicationContext(),"searchvalue :"+query,Toast.LENGTH_LONG).show();
                startCityIntent(query);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.filter) {
            AlertDialog.Builder mDialog = new AlertDialog.Builder(MainTrail.this);
            final View dialogView = LayoutInflater.from(MainTrail.this)
                    .inflate(R.layout.layout_setting_dialog,null);
            final EditText editText_radius = dialogView.findViewById(R.id.edittext_radius);
            final CheckBox cb_hiking = dialogView.findViewById(R.id.hiking);
            final CheckBox cb_biking = dialogView.findViewById(R.id.biking);
            final CheckBox cb_camping = dialogView.findViewById(R.id.camping);
            final CheckBox cb_trailing = dialogView.findViewById(R.id.climbing);

            mDialog.setTitle(R.string.preference);
            mDialog.setView(dialogView);
            mDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d("dialog", "onClick: ok");
                    String r = editText_radius.getText().toString();
                    String a = "";
                    if (cb_hiking.isChecked())
                        a = a + "hiking+";
                    if (cb_biking.isChecked())
                        a = a + "biking+";
                    if (cb_camping.isChecked())
                        a = a + "camping+";
                    if (cb_trailing.isChecked())
                        a = a + "climbing";
                    if (a.substring(a.length() - 1).equals("+"))
                        a = a.substring(0, a.length() - 1);

                    Log.d("filter", "onClick: " + a);
                    activity = a;
                    startFilterIntent(activity,r);
                }
            });
            mDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d("dialog", "onClick: cancel");

                }
            });
            mDialog.show();
            return true;
        }else if (id == R.id.search){
            filterItem.setVisible(false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private boolean CheckGooglePlayService(){
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;

    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_ACCESS_COURSE_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_ACCESS_COURSE_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    public class ResponseReceiver extends BroadcastReceiver{
        public static final String ACTION_RESP =
                "Data_fetched";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("demoapp","Data is fetched");
           // displayMovies(1, DBOpenHelper.COLUMN_NAME_RELEASE_DATE);
            Log.d("broadcast", "onReceive: " + "done");
//            unregisterReceiver(receiver);
            List<Place> temp;
            temp = manager.getAllRecords(DBOpenHelper.RESULT_TABLE_ID);

         //   Log.d("Place List", temp.get(0).getName());
            Log.d("broadcast", "onReceive: " + temp.size());

//            for (int i = 0; i < temp.size(); i++){
//                double lat = Double.parseDouble(temp.get(i).getLatitude());
//                double lon = Double.parseDouble(temp.get(i).getLongitude());
//                temp.get(i).setAddress(getCityName(lat,lon));
//            }
            mAdapter = new MyAdapter(temp, getApplicationContext());
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public String getCityName(double lat, double lon){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
            String cityName = addresses.get(0).getAddressLine(0);
            return cityName;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
