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
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
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
import com.example.guhao.mytrail.api.AddressService;
import com.example.guhao.mytrail.api.DownloadHelper;
import com.example.guhao.mytrail.api.GoogleAPIService;
import com.example.guhao.mytrail.data.Constants;
import com.example.guhao.mytrail.data.Place;
import com.example.guhao.mytrail.database.DBOpenHelper;
import com.example.guhao.mytrail.database.DatabaseManager;
import com.example.guhao.mytrail.listener.RecyclerItemClickListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainTrail extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, android.location.LocationListener {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AddressResultReceiver mResultReceiver;
    private MenuItem filterItem;

    //add G Map

    DownloadHelper downloadHelper;
    double longitude = -80.43301769999999, lat = 37.2432963;
    String activity = "hiking+campground";
    private ResponseReceiver receiver;
    private final static int MY_PERMISSION_ACCESS_COURSE_LOCATION=99;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLastKnownLocation;
    private boolean mLocationPermissionGranted;
    private Map<String, Boolean> activityMap;
    private int radius = 10;

    private final static long LOCATION_REFRESH_TIME = 0;
    private final static long LOCATION_REFRESH_DISTANCE = 0;
    private final static double MILE_TO_METER = 1609.344;

    DatabaseManager manager;
    LocationManager locationManager;

    View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trail);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        activityMap = new HashMap<>();
        activityMap.put("hiking", true);
        activityMap.put("camping", true);
        activityMap.put("climbing", false);
        activityMap.put("biking", false);
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }

        getLocationPermission();

        // Turn on the My Location layer and the related control on the map.
        //updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
      //  checkLocationPermission();
      //  getDeviceLocation();
        if(mLocationPermissionGranted){
            Log.d("Location: ", "granted");
        }
        else{
            Log.d("Location: ", "denied");
        }
        startServiceBroadcaster();
        initView();
        mResultReceiver = new AddressResultReceiver(new Handler());
        AddressIntentService(longitude, lat);

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

    @Override
    public void onLocationChanged(Location location) {
        Log.d("location_change", "onLocationChanged: " + location.getLongitude() + location.getLatitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void startServiceBroadcaster(){
        //registering a local broadcast receiver that is activated when "movies_fetched"

        //action happens
       // int radiusinMile = radius*1600;
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);

        downloadHelper = new DownloadHelper();
        double m = radius * MILE_TO_METER;
        String the_url = downloadHelper.getUrlCoordinate(lat,longitude,(int)m,activity);
        Log.d("URl_main", the_url);
        Intent msgIntent = new Intent(this, GoogleAPIService.class);
        msgIntent.setAction(GoogleAPIService.GET_RESULT);
        msgIntent.putExtra(GoogleAPIService.URL, the_url);
        startService(msgIntent);
    }

    public void startCityIntent(String city){
        double m = radius * MILE_TO_METER;
        String new_url = downloadHelper.getUrlCityName(city,(int)m,activity);
        Intent msgIntent = new Intent(this, GoogleAPIService.class);
        msgIntent.setAction(GoogleAPIService.GET_RESULT);
        msgIntent.putExtra(GoogleAPIService.URL, new_url);
        startService(msgIntent);

    }
    public void startGetLatLngIntent(String address){

        String new_url = downloadHelper.getLatLngURL(address);
        Intent msgIntent = new Intent(this, GoogleAPIService.class);
        msgIntent.setAction(GoogleAPIService.GET_LATLONG);
        msgIntent.putExtra(GoogleAPIService.URL, new_url);
        msgIntent.putExtra(GoogleAPIService.RECEIVER, mResultReceiver);
        startService(msgIntent);

    }
    private void AddressIntentService(double lng, double lat) {
        // Create an intent for passing to the intent service responsible for fetching the address.
        Log.d("IntentService","call address");
        Intent intent = new Intent(this, GoogleAPIService.class);
        intent.setAction(GoogleAPIService.GET_ADDRESS);
        intent.putExtra(GoogleAPIService.LONGITUDE, lng);
        intent.putExtra(GoogleAPIService.LATITUDE, lat);
        // Pass the result receiver as an extra to the service.
        intent.putExtra(GoogleAPIService.RECEIVER, mResultReceiver);
        startService(intent);
    }


    public void startFilterIntent(String activity, String radius) {
        int r = Integer.parseInt(radius);
        this.radius = r*1600;
        String the_url = downloadHelper.getUrlCoordinate(lat,longitude,this.radius,activity);
        Intent msgIntent = new Intent(this, GoogleAPIService.class);
        msgIntent.setAction(GoogleAPIService.GET_RESULT);
        msgIntent.putExtra(GoogleAPIService.URL, the_url);
        startService(msgIntent);
    }

    private boolean CheckGooglePlayServices() {
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


    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            Log.d("successful", "onComplete: ");
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
//                            Log.d("successful", "onComplete: " + mLastKnownLocation.getLongitude() + mLastKnownLocation.getLatitude());

                            try {
                                longitude = mLastKnownLocation.getLongitude();
                                lat = mLastKnownLocation.getLatitude();

                            }catch (Exception e){
                                Log.d("error", e.toString());
                            }
                        } else {
                            Log.d("Device_Location", "Current location is null. Using defaults.");
                            Log.e("Device Location", "Exception: %s", task.getException());
                            longitude = -80.43301769999999;
                            lat = 37.2432963;
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    public void initView(){
        manager = new DatabaseManager(this);
        manager.open();
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainTrail.this, MapView.class);
                Log.d("LAT ", "OnMap: " + lat);
                Log.d("LONG  ", "onMap: " + longitude);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", lat);
                //  intent.putExtra("place_id", temp.get(position).getPlace_id());
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
                intent.putExtra("name",mAdapter.getPlace(position).getName());
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
                startGetLatLngIntent(query);

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

            cb_hiking.setChecked(activityMap.get("hiking") == true);
            cb_camping.setChecked(activityMap.get("camping") == true);
            cb_biking.setChecked(activityMap.get("biking") == true);
            cb_trailing.setChecked(activityMap.get("climbing") == true);


            editText_radius.setText((radius/1600)+"");

            mDialog.setTitle(R.string.preference);
            mDialog.setView(dialogView);
            mDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d("dialog", "onClick: ok");
                    String r = editText_radius.getText().toString();
                    String a = "";
                    if (cb_hiking.isChecked()) {
                        activityMap.put("hiking", true);
                        a = a + "hiking+";
                    } else
                        activityMap.put("hiking",false);
                    if (cb_biking.isChecked()){
                        activityMap.put("biking", true);
                        a = a + "biking+";
                    } else
                        activityMap.put("biking",false);
                    if (cb_camping.isChecked())
                    {
                        activityMap.put("camping", true);
                        a = a + "campground+";
                    } else
                        activityMap.put("camping",false);
                    if (cb_trailing.isChecked())
                    {
                        activityMap.put("climbing", true);
                        a = a + "climbing+";
                    } else
                        activityMap.put("climbing",false);
                    if (a.length() > 0) {
                        if (a.substring(a.length() - 1).equals("+"))
                            a = a.substring(0, a.length() - 1);
                        Log.d("filter", "onClick: " + a);
                        activity = a;
                    }else{
                        activity = "hiking";
                    }

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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    public class ResponseReceiver extends BroadcastReceiver{
        public static final String ACTION_RESP =
                "Data_fetched";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("MyTrail","Data is fetched");
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
    private class AddressResultReceiver extends ResultReceiver {
        AddressResultReceiver(Handler handler) {
            super(handler);
        }

        /**
         *  Receives data sent from FetchAddressIntentService and updates the UI in MainActivity.
         */
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string or an error message sent from the intent service.
            Log.d("address","found address:"+resultData.getString(GoogleAPIService.RETURN_ADDRESS));

            // Show a toast message if an address was found.
            if (resultCode == GoogleAPIService.SUCCESS_RESULT) {
                longitude = resultData.getDouble(GoogleAPIService.LONGITUDE);
                lat = resultData.getDouble(GoogleAPIService.LATITUDE);
                Log.d("address",lat + ","+ longitude);
            }
            if (resultCode == GoogleAPIService.SUCCESS_ADDRESS) {
                String Adresss = resultData.getString(GoogleAPIService.RETURN_ADDRESS);

            }


        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mResultReceiver = null;
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
