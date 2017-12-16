package com.example.guhao.mytrail.api;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.example.guhao.mytrail.activity.DetailActivity;
import com.example.guhao.mytrail.activity.MainTrail;
import com.example.guhao.mytrail.data.DetailPlace;
import com.example.guhao.mytrail.database.DatabaseManager;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by DanielPham on 12/9/17.
 */

public class GoogleAPIService extends IntentService {
    public static final String API_KEY = "&key=AIzaSyB8kchYPw50-ExmT0yW3yYVb1LFECU0fZE";
    public static final String SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
    public static final String DETAIL_URL = "https://maps.googleapis.com/maps/api/place/details/json?placeid=";
    public static final String PHOTO_URL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
    public static final String GET_RESULT = "get result";
    public static final String GET_DETAIL = "get detail";
    public static final String ACTIVITY = "activity";
    public static final String RADIUS = "radius";
    public static final String LONGITUDE = "long";
    public static final String LATITUDE = "lat";
    public static final String RECEIVER = "receiver";
    public static final String URL = "url";
    public static final int SUCCESS_RESULT = 2;
    public static final String RETURN_ADDRESS = "return address";
    public static final String TAG = "Google API Service";

    public static final int FAILURE_RESULT = 3;
    public static final int FAILURE_ADDRESS = 4;
    public static final int SUCCESS_ADDRESS = 5;
    public static final String GET_LATLONG = "get lat lng";

    public static final String GET_ADDRESS = "get address";

    //act redius location


    private DetailPlace detailPlace;
    private DownloadHelper downloadHelper;
    private DatabaseManager manager;
    private ResultReceiver mReceiver;

    public GoogleAPIService() {
        super("GoogleAPIService");
    }
    //Gson gson = new Gson();
    //DetailPlace places = gson.fromJson("",DetailPlace.class);

    @Override
    protected void onHandleIntent( Intent intent) {
//            double longitude = -80.43301769999999, lat = 37.2432963;
            String response;

//            Log.d("Intent_Service", "onHandleIntent");
            if (intent != null) {
                final String intent_action = intent.getAction();

                if (GET_RESULT.equals(intent_action)) {
                    String search_url = intent.getStringExtra(URL);
                    Log.d("search_url", "onHandleIntent: " + search_url);
                    DownloadHelper downloadHelper = new DownloadHelper();
                    //String result = download(search_url);
                    //Log.d("Service", result.toString());
                    try{
                        response = downloadHelper.getResponses(search_url);
                        Log.d("Service", response.toString());
                        populateResultDB(response);
                    } catch (Exception e){
                       Log.d("Service Intent", e.toString());
                    }

//                    Intent broadcastIntent = new Intent();
//                    broadcastIntent.setAction(MainTrail.ResponseReceiver.ACTION_RESP);
//                    broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
//                    sendBroadcast(broadcastIntent);

                } else if (GET_DETAIL.equals((intent_action))) {
//                    if(mReceiver == null){
//                        Log.d("Get Detail Service: ", "No receiver received. There is nowhere to send the results.");
//                        return;
//                    }
                    Log.d("Intent_Service", "onHandleIntent" + intent_action);

                    String search_url = intent.getStringExtra(URL);
                    Log.d("search_url", "onHandleIntent: " + search_url);
                    DownloadHelper downloadHelper = new DownloadHelper();
                    try{
                        response = downloadHelper.getResponses(search_url);
                        Log.d("Service", response.toString());
                        DeliverPlaceDetail(SUCCESS_RESULT, response);
                    } catch (Exception e){
                        DeliverPlaceDetail(FAILURE_RESULT, e.toString());
                        Log.d("Service Intent", e.toString());
                    }

                    //request things for detailed view
                } else if (GET_LATLONG.equals(intent_action)){
                    mReceiver = intent.getParcelableExtra(RECEIVER);
                    if (mReceiver == null) {
                        Log.d("Error", "No receiver received. There is nowhere to send the results.");
                        return;
                    }
                    Log.d("Intent_Service", "onHandleIntent" + intent_action);

                    String search_url = intent.getStringExtra(URL);
                    Log.d("search_url", "onHandleIntent: " + search_url);
                    DownloadHelper downloadHelper = new DownloadHelper();
                    try{
                        response = downloadHelper.getResponses(search_url);
                        Log.d("Service", response.toString());
                        ParseLatLng(response);

                       // DeliverPlaceDetail(SUCCESS_RESULT, response);
                    } catch (Exception e){
                        DeliverPlaceDetail(FAILURE_RESULT, e.toString());
                        Log.d("Service Intent", e.toString());
                    }
                }else if (GET_ADDRESS.equals(intent_action)){
                    String errorMessage = "";

                    mReceiver = intent.getParcelableExtra(RECEIVER);

                    // Check if receiver was properly registered.
                    if (mReceiver == null) {
                        Log.d(TAG, "No receiver received. There is nowhere to send the results.");
                        return;
                    }

                    // Get the location passed to this service through an extra.
                    Location location = new Location("");
                    location.setLongitude(intent.getDoubleExtra(LONGITUDE,-80.43301769999999 ));
                    location.setLatitude(intent.getDoubleExtra(LATITUDE,37.2432963 ));

                    // Make sure that the location data was really sent over through an extra. If it wasn't,
                    // send an error error message and return.
                    if (location == null) {
                        errorMessage = "No location data provided";
                        Log.d(TAG, errorMessage);
                        deliverResultToReceiver(FAILURE_ADDRESS, errorMessage);
                        return;
                    }

                    // Errors could still arise from using the Geocoder (for example, if there is no
                    // connectivity, or if the Geocoder is given illegal location data). Or, the Geocoder may
                    // simply not have an address for a location. In all these cases, we communicate with the
                    // receiver using a resultCode indicating failure. If an address is found, we use a
                    // resultCode indicating success.

                    // The Geocoder used in this sample. The Geocoder's responses are localized for the given
                    // Locale, which represents a specific geographical or linguistic region. Locales are used
                    // to alter the presentation of information such as numbers or dates to suit the conventions
                    // in the region they describe.
                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());

                    // Address found using the Geocoder.
                    List<Address> addresses = null;

                    try {
                        // Using getFromLocation() returns an array of Addresses for the area immediately
                        // surrounding the given latitude and longitude. The results are a best guess and are
                        // not guaranteed to be accurate.
                        addresses = geocoder.getFromLocation(
                                location.getLatitude(),
                                location.getLongitude(),
                                // In this sample, we get just a single address.
                                1);
                    } catch (IOException ioException) {
                        // Catch network or other I/O problems.
                        errorMessage = "Service Not Available";
                        Log.e(TAG, errorMessage, ioException);
                    } catch (IllegalArgumentException illegalArgumentException) {
                        // Catch invalid latitude or longitude values.
                        errorMessage = "invalid lat long used";
                        Log.e(TAG, errorMessage + ". " +
                                "Latitude = " + location.getLatitude() +
                                ", Longitude = " + location.getLongitude(), illegalArgumentException);
                    }

                    // Handle case where no address was found.
                    if (addresses == null || addresses.size()  == 0) {
                        if (errorMessage.isEmpty()) {
                            errorMessage = "No address found";
                            Log.e(TAG, errorMessage);
                        }
                        deliverResultToReceiver(FAILURE_RESULT, errorMessage);
                    } else {
                        Address address = addresses.get(0);
                        ArrayList<String> addressFragments = new ArrayList<>();

                        // Fetch the address lines using {@code getAddressLine},
                        // join them, and send them to the thread. The {@link android.location.address}
                        // class provides other options for fetching address details that you may prefer
                        // to use. Here are some examples:
                        // getLocality() ("Mountain View", for example)
                        // getAdminArea() ("CA", for example)
                        // getPostalCode() ("94043", for example)
                        // getCountryCode() ("US", for example)
                        // getCountryName() ("United States", for example)
                        for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                            addressFragments.add(address.getAddressLine(i));
                            //address.get
                        }
                        Log.i(TAG, "Address found!");
                        // deliverResultToReceiver(Constants.SUCCESS_RESULT,
                        //        TextUtils.join(System.getProperty("line.separator"), addressFragments));

                        deliverResultToReceiver(SUCCESS_ADDRESS, address.getLocality());
                        //        TextUtils.join(System.getProperty("line.separator"), addressFragments));

                    }
                }


            }


    }

    private void populateResultDB(String jsonData) {
        Log.d("jsondata","can we see it:?"+jsonData);
        manager = new DatabaseManager(this.getApplicationContext());
        manager.open();
        manager.deleteAllResult();

        JSONObject jsonResponse = null;
        try {

            jsonResponse = new JSONObject(jsonData);

            JSONArray places = jsonResponse.getJSONArray("results");

            int places_list_size = places.length();



            for (int i = 0; i < places_list_size; i++) {

                JSONObject jsonPlace = places.getJSONObject(i);

                double rating = 0;
                String photo_reference = "null";
                String address = "null";
                String name = jsonPlace.getString("name");
                String place_id = jsonPlace.getString("place_id");
                double longitude = 0;
                double latitude = 0;

                try{
                    address = jsonPlace.getString("formatted_address");
                    rating = jsonPlace.getDouble("rating");

                    JSONArray photoArray = jsonPlace.getJSONArray("photos");
                    JSONObject photo= photoArray.getJSONObject(0);
                    photo_reference = photo.getString("photo_reference");

                    JSONObject Geometry = jsonPlace.getJSONObject("geometry");
                    JSONObject location = Geometry.getJSONObject("location");
                    longitude = location.getDouble("lng");
                    latitude = location.getDouble("lat");

                }catch (Exception e){
                    Log.d("Error ", e.toString());
                }

                if (name != null && !name.equals("null")) {
                    //manager.insertMovieInfo(title,dateString,(float) rating );
                    manager.insertPlaceInfo(name,place_id, (float) rating,photo_reference, address, (float) longitude, (float) latitude );

                }
            }
            manager.close();

            //broadcasting that it worked

            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(MainTrail.ResponseReceiver.ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            sendBroadcast(broadcastIntent);
        }
        catch (JSONException e) {

            e.printStackTrace();
        }
    }
    private void ParseLatLng(String jsonData){
        Log.d("jsondata","can we see it:?"+jsonData);

        JSONObject jsonResponse = null;
        try {

            jsonResponse = new JSONObject(jsonData);

            JSONArray places = jsonResponse.getJSONArray("results");

            int places_list_size = places.length();



            for (int i = 0; i < places_list_size; i++) {

                JSONObject jsonPlace = places.getJSONObject(i);
                String address = "null";
                String place_id = jsonPlace.getString("place_id");
                 double longitude = 0;
                double latitude = 0;

                try{
                    address = jsonPlace.getString("formatted_address");
                    JSONObject Geometry = jsonPlace.getJSONObject("geometry");
                    JSONObject location = Geometry.getJSONObject("location");
                    longitude = location.getDouble("lng");
                    latitude = location.getDouble("lat");
                    Log.d("New location", latitude +"'"+longitude);
                    DeliverLngLat(SUCCESS_RESULT, longitude, latitude);

                }catch (Exception e){
                    Log.d("Error ", e.toString());
                }

            }


        }
        catch (JSONException e) {

            e.printStackTrace();
        }

    }

    private void DeliverPlaceDetail( int resultCode, String json){
//        Bundle bundle = new Bundle();
//        bundle.putString("place_detail", json);
//        mReceiver.send(resultCode, bundle);

        Intent broadcastIntent = new Intent();
        broadcastIntent.putExtra("response", json);
        broadcastIntent.setAction(DetailActivity.ResponseReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        sendBroadcast(broadcastIntent);
    }
    private void deliverResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(RETURN_ADDRESS, message);
        mReceiver.send(resultCode, bundle);
    }

    private void DeliverLngLat(int resultCode, double lng, double lat ){
        Bundle bundle = new Bundle();
        bundle.putDouble(LATITUDE, lat);
        bundle.putDouble(LONGITUDE, lng);
        mReceiver.send(resultCode, bundle);
    }

}
