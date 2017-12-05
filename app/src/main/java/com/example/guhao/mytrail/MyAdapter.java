package com.example.guhao.mytrail;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Author: GuHao
 * Date: 12/5/17
 * Time: 1:09 PM
 * Desc:
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PlaceViewHolder>{
    private List<Place> places;
    private Context context;

    public MyAdapter(List<Place> places, Context context){
        this.places = places;
        this.context = context;
    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView location;
        CardView cardView;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.my_card_view);
            name = (TextView)itemView.findViewById(R.id.place_name);
            location = (TextView)itemView.findViewById(R.id.place_location);
        }
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_card_view, parent, false);
        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        holder.name.setText(places.get(position).getName());
        holder.location.setText(places.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }
}
