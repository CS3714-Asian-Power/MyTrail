package guhao.mytrail.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import guhao.mytrail.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import guhao.mytrail.api.DownloadHelper;
import guhao.mytrail.data.Place;

/**
 * Author: GuHao
 * Date: 12/5/17
 * Time: 1:09 PM
 * Desc:
 */

public class  MyAdapter extends RecyclerView.Adapter<MyAdapter.PlaceViewHolder>{
    private List<Place> places;
    private Context context;
    private TextView name;

    public MyAdapter(List<Place> places, Context context){
        this.places = places;
        this.context = context;
    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView location;
        TextView rating;
        ImageView thumbnail;
        RatingBar ratingBar;
        CardView cardView;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.my_card_view);
            name = (TextView)itemView.findViewById(R.id.place_name);
            rating = (TextView) itemView.findViewById(R.id.rating);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            location = (TextView)itemView.findViewById(R.id.place_location);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        }
    }

    public Place getPlace(int i){
        return places.get(i);
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_card_view, parent, false);
        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PlaceViewHolder holder, int position) {
        DownloadHelper helper = new DownloadHelper();
        if(!places.get(position).getThumbnail().equals("null") ){
            String thumbnail_URL = helper.getPhotoURL(1200, places.get(position).getThumbnail());
//            Target target = new Target() {
//                @Override
//                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                    BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);
//                    holder.thumbnail.setImageDrawable(drawable);
//                }
//
//                @Override
//                public void onBitmapFailed(Drawable errorDrawable) {
//
//                }
//
//                @Override
//                public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                }
//            };
//
//            holder.thumbnail.setTag(target);
            Log.d("recycler_url", "onBindViewHolder: " + thumbnail_URL);
            Picasso picasso = Picasso.with(context);
            picasso.load(thumbnail_URL).into(holder.thumbnail);
//            Glide.with(context).load(thumbnail_URL).into(holder.thumbnail);

        }
        holder.ratingBar.setRating((float) Double.parseDouble(places.get(position).getRating()));

        holder.name.setText(places.get(position).getName());
        this.name = holder.name;
        holder.rating.setText(places.get(position).getRating());
        holder.location.setText(places.get(position).getAddress());

       // holder.location.setText(places.get(position).getLatitude());

        holder.location.setText(places.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public TextView getName(){
        return this.name;
    }
}
