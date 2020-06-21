package com.example.finalproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.example.finalproject.model.festival.Item;

import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    private Context context;
    private List<Item> festivalList = new ArrayList<>();

    public EventListAdapter(Context context) {
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView eventPoster;
        TextView eventName;
        TextView eventLocation;
        TextView eventCount;
        TextView eventDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventPoster = itemView.findViewById(R.id.item_event_poster_imageview);
            eventName = itemView.findViewById(R.id.item_event_name_textview);
            eventLocation = itemView.findViewById(R.id.item_event_period_textview);
            eventCount = itemView.findViewById(R.id.item_event_viewcount_textview);
            eventDistance = itemView.findViewById(R.id.item_event_distance_textview);

        }
    }

    @NonNull
    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.ViewHolder holder, int position) {
        Item item = festivalList.get(position);
        holder.eventName.setText(item.getTitle());
        Log.d("jsontest", "onBindViewHolder: " + item.getFirstimage());
        Glide.with(context).load(item.getFirstimage())
                .error(R.drawable.noimage).centerCrop().into(holder.eventPoster);
        holder.eventLocation.setText(item.getAddr1());

    }

    @Override
    public int getItemCount() {
        return festivalList.size();
    }

    public void updateItems(List<Item> areas) {
        festivalList = areas;
        notifyDataSetChanged();
    }
}
