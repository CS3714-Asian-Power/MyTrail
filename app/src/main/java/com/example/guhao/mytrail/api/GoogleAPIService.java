package com.example.guhao.mytrail.api;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.guhao.mytrail.activity.MainTrail;
import com.example.guhao.mytrail.data.AllPlaces;
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
    public static final String URL = "url";
    //act redius location

    private AllPlaces allPlaces;
    private DetailPlace detailPlace;
    private DownloadHelper downloadHelper;
    private DatabaseManager manager;

    public GoogleAPIService() {
        super("GoogleAPIService");
    }
    //Gson gson = new Gson();
    //DetailPlace places = gson.fromJson("",DetailPlace.class);

    @Override
    protected void onHandleIntent( Intent intent) {
            double longitude = -80.43301769999999, lat = 37.2432963;
            String response;

            Log.d("Intent Service", "onHandleIntent");
            if (intent != null) {
                final String intent_action = intent.getAction();
                if (GET_RESULT.equals(intent_action)) {
                    String search_url = intent.getStringExtra(URL);
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
                String name = jsonPlace.getString("name");
                String place_id = jsonPlace.getString("place_id");
                double longitude = 0;
                double latitude = 0;

                try{
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


//                Log.d("item ", ""+i);
//                Log.d("name", name);
//                Log.d("place_id", place_id);
//                Log.d("rating", " " + rating);
//                Log.d("photo", photo_reference);
//                Log.d("latitude", ""+latitude);
//                Log.d("Longitude", ""+longitude);
                //int like = 0;

                if (name != null && !name.equals("null")) {
                    //manager.insertMovieInfo(title,dateString,(float) rating );
                    manager.insertPlaceInfo(name,place_id, (float) rating,photo_reference, (float) longitude, (float) latitude );

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
        }}
    private String download(String url){
        //manager = new DatabaseManager(this.getApplicationContext());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = null;
        String jsonData = null;

        try {
            response = call.execute();

            if (response.isSuccessful()) {
                jsonData = response.body().string();

            } else {
                jsonData = null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData; //This is returned to onPostExecute()

    }

}
