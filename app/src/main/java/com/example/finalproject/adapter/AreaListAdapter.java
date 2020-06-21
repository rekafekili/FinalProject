package com.example.finalproject.adapter;

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
import com.example.finalproject.constant.AreaCode;

public class AreaListAdapter extends RecyclerView.Adapter<AreaListAdapter.ViewHolder> {
    private Context context;
    private AreaCode[] areaCodes;
    private OnItemClickListener onItemClickListener;

    public AreaListAdapter(Context context, AreaCode[] areaCodeArray) {
        this.context = context;
        this.areaCodes = areaCodeArray;
    }

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView areaImage;
        private TextView areaName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            areaImage = itemView.findViewById(R.id.item_area_image_imageview);
            areaName = itemView.findViewById(R.id.item_area_name_textview);

            itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClicked(view, getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_area, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AreaCode area = areaCodes[position];
        Glide.with(context).load(area.getDrawableRes()).fitCenter().into(holder.areaImage);
        holder.areaName.setText(area.getAreaName());
    }

    @Override
    public int getItemCount() {
        return AreaCode.values().length;
    }
}
