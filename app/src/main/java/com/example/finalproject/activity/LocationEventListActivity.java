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
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.adapter.AreaEventListAdapter;
import com.example.finalproject.adapter.LocationEventListAdapter;
import com.example.finalproject.constant.AreaCode;
import com.example.finalproject.model.location_festival.LocationFestivalItem;
import com.example.finalproject.viewmodel.EventViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.finalproject.constant.URLConstant.COUNT_ARRANGE;
import static com.example.finalproject.constant.URLConstant.CREATED_ARRANGE;
import static com.example.finalproject.constant.URLConstant.DISTANCE_ARRANGE;
import static com.example.finalproject.constant.URLConstant.MODIFIED_ARRANGE;
import static com.example.finalproject.constant.URLConstant.TITLE_ARRANGE;

public class LocationEventListActivity extends AppCompatActivity implements LocationEventListAdapter.OnItemClickListener, TabLayout.OnTabSelectedListener {
    private EventViewModel eventViewModel;
    private List<LocationFestivalItem> locationFestivalItemList;
    private LocationEventListAdapter locationEventListAdapter;
    private TextView toolbarText;

    private String selectedAreaName;
    private double mapX;
    private double mapY;

    // Tab Index
    private final int TAB_TITLE_ARRANGE = 0;
    private final int TAB_COUNT_ARRANGE = 1;
    private final int TAB_MODIFIED_ARRANGE = 2;
    private final int TAB_CREATED_ARRANGE = 3;
    private final int TAB_DISTANCE_ARRANGE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_event_list);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        mapX = getIntent().getDoubleExtra("MAP_X", 0.0);
        mapY = getIntent().getDoubleExtra("MAP_Y", 0.0);
        if(mapX == 0.0 || mapY == 0.0) {
            Toast.makeText(this, "현재 위치를 받지 못하였습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        initView();
        viewModelBinding();

        eventViewModel.fetchFestivalByLocation(mapX, mapY, TITLE_ARRANGE);
    }

    private void viewModelBinding() {
        eventViewModel.isFetchingLiveData.observe(this, isFetching -> {
            if (isFetching) {
                findViewById(R.id.event_list_progressbar).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.event_list_progressbar).setVisibility(View.GONE);
            }
        });

        eventViewModel.locationFestivalLiveData.observe(this, locationFestivalItems -> {
            if(locationFestivalItems.isEmpty()) {
                Toast.makeText(this, "행사 목록을 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                locationFestivalItemList = new ArrayList<>();
                locationFestivalItemList.addAll(locationFestivalItems);

                LocationFestivalItem item = locationFestivalItems.get(0);
                selectedAreaName = AreaCode.getNameByCode(item.getAreacode());
                toolbarText.setText(selectedAreaName);

                locationEventListAdapter.updateLocationFestivalItems(locationFestivalItems);
            }
        });
    }

    private void initView() {
        // Toolbar 설정
        Toolbar toolbar = findViewById(R.id.event_list_toolbar);
        toolbarText = toolbar.findViewById(R.id.toolbar_title_textview);

        // TabLayout TabSelectedListener
        TabLayout tabLayout = findViewById(R.id.event_arrange_tablayout);
        tabLayout.addOnTabSelectedListener(this);

        // RecyclerView 초기화
        RecyclerView eventListRecyclerView = findViewById(R.id.event_list_recyclerview);
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        locationEventListAdapter = new LocationEventListAdapter(this);
        locationEventListAdapter.setOnItemClickListener(this);
        eventListRecyclerView.setAdapter(locationEventListAdapter);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this, EventDetailActivity.class);

        LocationFestivalItem locationFestivalItem = locationFestivalItemList.get(position);
        intent.putExtra("CONTENT_ID", locationFestivalItem.getContentid());
        intent.putExtra("CONTENT_TITLE", locationFestivalItem.getTitle());
        intent.putExtra("CONTENT_IMAGE", locationFestivalItem.getFirstimage());
        intent.putExtra("LATITUDE", locationFestivalItem.getMapy());
        intent.putExtra("LONGITUDE", locationFestivalItem.getMapx());

        startActivity(intent);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case TAB_TITLE_ARRANGE :
                eventViewModel.fetchFestivalByLocation(mapX, mapY, TITLE_ARRANGE);
                break;
            case TAB_COUNT_ARRANGE :
                eventViewModel.fetchFestivalByLocation(mapX, mapY, COUNT_ARRANGE);
                break;
            case TAB_MODIFIED_ARRANGE :
                eventViewModel.fetchFestivalByLocation(mapX, mapY, MODIFIED_ARRANGE);
                break;
            case TAB_CREATED_ARRANGE :
                eventViewModel.fetchFestivalByLocation(mapX, mapY, CREATED_ARRANGE);
                break;
            case TAB_DISTANCE_ARRANGE :
                eventViewModel.fetchFestivalByLocation(mapX, mapY, DISTANCE_ARRANGE);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}