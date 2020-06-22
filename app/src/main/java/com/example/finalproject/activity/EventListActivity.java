package com.example.finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.adapter.EventListAdapter;
import com.example.finalproject.constant.AreaCode;
import com.example.finalproject.model.festival.FestivalItem;
import com.example.finalproject.model.location_festival.LocationFestivalItem;
import com.example.finalproject.viewmodel.EventViewModel;

import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends AppCompatActivity implements EventListAdapter.OnItemClickListener {
    private final String TAG = "TEST";

    private EventViewModel eventViewModel;
    private EventListAdapter eventListAdapter;
    private List<FestivalItem> festivalItemList;
    private List<LocationFestivalItem> locationFestivalItemList;
    private String selectedAreaName;
    private String service;
    private TextView toolbarText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        eventViewModel.isFetchingLiveData.observe(this, isFetching -> {
            if (isFetching) {
                findViewById(R.id.event_list_progressbar).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.event_list_progressbar).setVisibility(View.GONE);
            }
        });

        service = getIntent().getStringExtra("SERVICE");
        Log.d(TAG, "onCreate: " + service);
        if(service == null) finish();
        initView();

        switch (service) {
            case "AREA" :
                Log.d(TAG, "onCreate: " + service);
                selectedAreaName = getIntent().getStringExtra("AREA_NAME");
                toolbarText.setText(selectedAreaName);
                int selectedAreaCode = getIntent().getIntExtra("AREA_CODE", -1);

                if (selectedAreaCode == -1 || selectedAreaName == null || selectedAreaName.isEmpty()) {
                    finish();
                }
                eventViewModel.fetchFestivalByAreaCode(selectedAreaCode);

                eventViewModel.festivalLiveData.observe(this, items -> {
                    festivalItemList = new ArrayList<>();
                    festivalItemList.addAll(items);
                    eventListAdapter.updateFestivalItems(items);;
                });
                break;
            case "LOCATION" :
                Log.d(TAG, "onCreate: " + service);

                double mapX = getIntent().getDoubleExtra("MAP_X", 0.0);
                double mapY = getIntent().getDoubleExtra("MAP_Y", 0.0);
                Log.d(TAG, "onCreate: " + mapX + mapY);
                if(mapX == 0.0 || mapY == 0.0) finish();

                eventViewModel.fetchFestivalByLocation(mapX, mapY);
                eventViewModel.locationFestivalLiveData.observe(this, locationFestivalItems -> {
                    locationFestivalItemList = new ArrayList<>();
                    locationFestivalItemList.addAll(locationFestivalItems);

                    LocationFestivalItem item = locationFestivalItems.get(0);
                    selectedAreaName = AreaCode.getNameByCode(item.getAreacode());
                    toolbarText.setText(selectedAreaName);

                    eventListAdapter.updateLocationFestivalItems(locationFestivalItems);
                });
                break;
            default:
                finish();
        }

    }

    private void initView() {
        // Toolbar 설정
        Toolbar toolbar = findViewById(R.id.event_list_toolbar);
        toolbarText = toolbar.findViewById(R.id.toolbar_title_textview);

        // RecyclerView 초기화
        RecyclerView eventListRecyclerView = findViewById(R.id.event_list_recyclerview);
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        eventListAdapter = new EventListAdapter(this, service);
        eventListAdapter.setOnItemClickListener(this);
        eventListRecyclerView.setAdapter(eventListAdapter);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this, EventDetailActivity.class);

        if(service.equals("AREA")) {
            FestivalItem festivalItem = festivalItemList.get(position);
            intent.putExtra("CONTENT_ID", festivalItem.getContentid());
            intent.putExtra("CONTENT_TITLE", festivalItem.getTitle());
            intent.putExtra("CONTENT_IMAGE", festivalItem.getFirstimage());
            intent.putExtra("LATITUDE", festivalItem.getMapy());
            intent.putExtra("LONGITUDE", festivalItem.getMapx());
            Log.d(TAG, "onItemClick: " + festivalItem.getMapy() + " : " + festivalItem.getMapx());
        } else {
            LocationFestivalItem locationFestivalItem = locationFestivalItemList.get(position);
            intent.putExtra("CONTENT_ID", locationFestivalItem.getContentid());
            intent.putExtra("CONTENT_TITLE", locationFestivalItem.getTitle());
            intent.putExtra("CONTENT_IMAGE", locationFestivalItem.getFirstimage());
            intent.putExtra("LATITUDE", locationFestivalItem.getMapy());
            intent.putExtra("LONGITUDE", locationFestivalItem.getMapx());
        }

        startActivity(intent);
    }
}