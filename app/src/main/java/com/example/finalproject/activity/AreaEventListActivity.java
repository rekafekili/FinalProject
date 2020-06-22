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
import com.example.finalproject.model.festival.FestivalItem;
import com.example.finalproject.viewmodel.EventViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.finalproject.constant.URLConstant.COUNT_ARRANGE;
import static com.example.finalproject.constant.URLConstant.CREATED_ARRANGE;
import static com.example.finalproject.constant.URLConstant.MODIFIED_ARRANGE;
import static com.example.finalproject.constant.URLConstant.TITLE_ARRANGE;

public class AreaEventListActivity extends AppCompatActivity implements AreaEventListAdapter.OnItemClickListener, TabLayout.OnTabSelectedListener {
    private EventViewModel eventViewModel;
    private AreaEventListAdapter areaEventListAdapter;
    private List<FestivalItem> festivalItemList;
    private String selectedAreaName;
    private TextView toolbarText;

    // Tab Index
    private final int TAB_TITLE_ARRANGE = 0;
    private final int TAB_COUNT_ARRANGE = 1;
    private final int TAB_MODIFIED_ARRANGE = 2;
    private final int TAB_CREATED_ARRANGE = 3;
    private int selectedAreaCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_event_list);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        viewModelBinding();
        initView();

        selectedAreaName = getIntent().getStringExtra("AREA_NAME");
        toolbarText.setText(selectedAreaName);
        selectedAreaCode = getIntent().getIntExtra("AREA_CODE", -1);

        if (selectedAreaCode == -1 || selectedAreaName == null || selectedAreaName.isEmpty()) {
            finish();
        }
        eventViewModel.fetchFestivalByAreaCode(selectedAreaCode, TITLE_ARRANGE);
    }

    private void initView() {
        // Toolbar 설정
        Toolbar toolbar = findViewById(R.id.event_list_toolbar);
        toolbarText = toolbar.findViewById(R.id.toolbar_title_textview);

        // TabLayout TabSelectListener
        TabLayout tabLayout = findViewById(R.id.event_arrange_tablayout);
        tabLayout.addOnTabSelectedListener(this);

        // RecyclerView 초기화
        RecyclerView eventListRecyclerView = findViewById(R.id.event_list_recyclerview);
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        areaEventListAdapter = new AreaEventListAdapter(this);
        areaEventListAdapter.setOnItemClickListener(this);
        eventListRecyclerView.setAdapter(areaEventListAdapter);
    }

    private void viewModelBinding() {
        eventViewModel.isFetchingLiveData.observe(this, isFetching -> {
            if (isFetching) {
                findViewById(R.id.event_list_progressbar).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.event_list_progressbar).setVisibility(View.GONE);
            }
        });

        eventViewModel.festivalLiveData.observe(this, items -> {
            if (items.isEmpty()) {
                Toast.makeText(this, "행사 목록을 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                festivalItemList = new ArrayList<>();
                festivalItemList.addAll(items);
                areaEventListAdapter.updateFestivalItems(items);
            }
        });
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this, EventDetailActivity.class);

        FestivalItem festivalItem = festivalItemList.get(position);
        intent.putExtra("CONTENT_ID", festivalItem.getContentid());
        intent.putExtra("CONTENT_TITLE", festivalItem.getTitle());
        intent.putExtra("CONTENT_IMAGE", festivalItem.getFirstimage());
        intent.putExtra("LATITUDE", festivalItem.getMapy());
        intent.putExtra("LONGITUDE", festivalItem.getMapx());

        startActivity(intent);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch(tab.getPosition()) {
            case TAB_TITLE_ARRANGE :
                eventViewModel.fetchFestivalByAreaCode(selectedAreaCode, TITLE_ARRANGE);
                break;
            case TAB_COUNT_ARRANGE :
                eventViewModel.fetchFestivalByAreaCode(selectedAreaCode, COUNT_ARRANGE);
                break;
            case TAB_MODIFIED_ARRANGE :
                eventViewModel.fetchFestivalByAreaCode(selectedAreaCode, MODIFIED_ARRANGE);
                break;
            case TAB_CREATED_ARRANGE :
                eventViewModel.fetchFestivalByAreaCode(selectedAreaCode, CREATED_ARRANGE);
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