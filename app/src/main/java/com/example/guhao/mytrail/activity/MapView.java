package com.example.guhao.mytrail.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.guhao.mytrail.R;
import com.example.guhao.mytrail.data.Place;
import com.example.guhao.mytrail.database.DBOpenHelper;
import com.example.guhao.mytrail.database.DatabaseManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.List;

public class MapView extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private static final String TAG = "map_detail";

   // GoogleApiClient mGoogleApiClient = null;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    private PlaceDetectionClient mPlaceDetectionClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted;
    private GeoDataClient mGeoDataClient;
    double latitude;
    double longitude;
    private static final int DEFAULT_ZOOM = 10;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private DatabaseManager manager;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;
    private LatLng mDefaultLocation = new LatLng(37.18482189999999, -80.3763869);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_view);
        Intent intent = getIntent();
        if (getIntent() != null){
           latitude =  intent.getDoubleExtra("latitude", 37.18482189999999);
           longitude =  intent.getDoubleExtra("longitude",  -80.3763869);
            Log.d("LAT ", "onCreate: " + latitude);
            Log.d("LONG  ", "onCreate: " + longitude);
            //mLastKnownLocation = new Location(latitude, longitude);
            mDefaultLocation = new LatLng(latitude, longitude);
           // String name = intent.getStringExtra("name");

           // getSupportActionBar().setTitle(name);
           // Log.d("place_name", "initView: " + name);
        }

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }
    private void ShowNearbyPlace () {
        manager = new DatabaseManager(this);
        manager.open();
        List<Place> temp;
        temp = manager.getAllRecords(DBOpenHelper.RESULT_TABLE_ID);
        for(int i = 0; i < temp.size(); i++){
            MarkerOptions markerOptions = new MarkerOptions();
            double lat = Double.parseDouble(temp.get(i).getLatitude());
            double lng = Double.parseDouble(temp.get(i).getLongitude());
            String placename = temp.get(i).getName();
            String address = temp.get(i).getAddress();
            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placename + " : " + address);
            Log.d("Marker:", placename);
            if(mMap != null ){
                mMap.addMarker(markerOptions);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                //move map camera
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
            }
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//        //Initialize Google Play Services
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                buildGoogleApiClient();
//                mMap.setMyLocationEnabled(true);
//            }
//        }
//        else {
//            buildGoogleApiClient();
//            mMap.setMyLocationEnabled(true);
//        }
        // Prompt the user for permission.
        getLocationPermission();

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
        ShowNearbyPlace();

    }
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                mFusedLocationProviderClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    mLastKnownLocation = location;
                                 //   mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                     //       new LatLng(mLastKnownLocation.getLatitude(),
                                      //              mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));

                                }
                                else{
                                    Log.d(TAG, "Current location is null. Using defaults.");
                                   // Log.e(TAG, "Exception: %s", task.getException());
                                  //  mMap.moveCamera(CameraUpdateFactory
                                   //         .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                                  //  mMap.getUiSettings().setMyLocationButtonEnabled(false);
                                }
                            }
                        });
//                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
//                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Location> task) {
//                        if (task.isSuccessful()) {
//                            // Set the map's camera position to the current location of the device.
//                            mLastKnownLocation = task.getResult();
//                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                                    new LatLng(mLastKnownLocation.getLatitude(),
//                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
//                        } else {
//                            Log.d(TAG, "Current location is null. Using defaults.");
//                            Log.e(TAG, "Exception: %s", task.getException());
//                            mMap.moveCamera(CameraUpdateFactory
//                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
//                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
//                        }
//                    }
//                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
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
    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
        updateLocationUI();
    }
}

