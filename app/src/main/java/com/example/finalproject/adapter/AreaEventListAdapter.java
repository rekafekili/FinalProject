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
import com.example.finalproject.model.festival.FestivalItem;
import com.example.finalproject.model.location_festival.LocationFestivalItem;

import java.util.ArrayList;
import java.util.List;

public class AreaEventListAdapter extends RecyclerView.Adapter<AreaEventListAdapter.ViewHolder> {
    private Context context;
    private List<FestivalItem> areaFestivalList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public AreaEventListAdapter(Context context) {
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

        public void bindFestivalItem(FestivalItem festivalItem) {
            eventName.setText(festivalItem.getTitle());
            Glide.with(context).load(festivalItem.getFirstimage())
                    .error(R.drawable.noimage).centerCrop().into(eventPoster);
            eventPeriod.setText(makePeriodString(festivalItem.getEventstartdate().toString(), festivalItem.getEventenddate().toString()));
            eventDistance.setText(festivalItem.getAddr1());
            eventCount.setText(String.valueOf(festivalItem.getReadcount()));
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AreaEventListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaEventListAdapter.ViewHolder holder, int position) {
        holder.bindFestivalItem(areaFestivalList.get(position));
    }

    @Override
    public int getItemCount() {
        return areaFestivalList.size();
    }

    public void updateFestivalItems(List<FestivalItem> areas) {
        areaFestivalList = areas;
        notifyDataSetChanged();
    }

    private String makePeriodString(String startDate, String endDate) {
        return String.format(context.getResources().getString(R.string.event_period_format),
                startDate.substring(0, 4), startDate.substring(4, 6), startDate.substring(6),
                endDate.substring(0, 4), endDate.substring(4, 6), endDate.substring(6));
    }
}
