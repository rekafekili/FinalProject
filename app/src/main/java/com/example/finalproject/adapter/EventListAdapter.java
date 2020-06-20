package com.example.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.model.Area;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Area> areaList = new ArrayList<>();

    public EventListAdapter(Context context) {
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView eventPoster;
        TextView eventName;
        TextView eventLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventPoster = itemView.findViewById(R.id.item_event_poster_imageview);
            eventName = itemView.findViewById(R.id.item_event_name_textview);
            eventLocation = itemView.findViewById(R.id.item_event_location_textview);
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
        holder.eventName.setText(areaList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void updateItems(ArrayList<Area> areas) {
        areaList = areas;
        notifyDataSetChanged();
    }
}
