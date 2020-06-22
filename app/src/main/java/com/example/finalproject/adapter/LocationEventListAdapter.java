package com.example.finalproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.example.finalproject.model.location_festival.LocationFestivalItem;

import java.util.ArrayList;
import java.util.List;

public class LocationEventListAdapter extends RecyclerView.Adapter<LocationEventListAdapter.ViewHolder> {
    private Context context;
    private List<LocationFestivalItem> locationFestivalItemList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public LocationEventListAdapter(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView eventPoster;
        TextView eventName;
        TextView eventPeriod;
        TextView eventCount;
        TextView eventDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventPoster = itemView.findViewById(R.id.item_event_poster_imageview);
            eventName = itemView.findViewById(R.id.item_event_name_textview);
            eventPeriod = itemView.findViewById(R.id.item_event_period_textview);
            eventCount = itemView.findViewById(R.id.item_event_viewcount_textview);
            eventDistance = itemView.findViewById(R.id.item_event_distance_textview);

            itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(itemView, getAdapterPosition());
                }
            });
        }

        @SuppressLint("SetTextI18n")
        public void bindLocationFestivalItem(LocationFestivalItem locationFestivalItem) {
            eventName.setText(locationFestivalItem.getTitle());
            Glide.with(context).load(locationFestivalItem.getFirstimage()).error(R.drawable.noimage).centerCrop().into(eventPoster);
            eventDistance.setText(String.format(context.getResources().getString(R.string.event_dist_format), locationFestivalItem.getDist()));
            eventPeriod.setText(locationFestivalItem.getAddr1() + locationFestivalItem.getAddr2());
            eventCount.setText(String.valueOf(locationFestivalItem.getReadcount()));
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public LocationEventListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationEventListAdapter.ViewHolder holder, int position) {
        holder.bindLocationFestivalItem(locationFestivalItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return locationFestivalItemList.size();
    }

    public void updateLocationFestivalItems(List<LocationFestivalItem> locationFestivalItems) {
        locationFestivalItemList = locationFestivalItems;
        notifyDataSetChanged();
    }

    private String makePeriodString(String startDate, String endDate) {
        return String.format(context.getResources().getString(R.string.event_period_format),
                startDate.substring(0, 4), startDate.substring(4, 6), startDate.substring(6),
                endDate.substring(0, 4), endDate.substring(4, 6), endDate.substring(6));
    }
}
