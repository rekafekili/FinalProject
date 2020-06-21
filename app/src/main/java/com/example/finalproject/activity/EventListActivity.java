package com.example.finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.adapter.EventListAdapter;
import com.example.finalproject.model.festival.FestivalItem;
import com.example.finalproject.viewmodel.EventViewModel;

import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends AppCompatActivity implements EventListAdapter.OnItemClickListener {
    private EventViewModel eventViewModel;
    private EventListAdapter eventListAdapter;
    private List<FestivalItem> festivalItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        String selectedAreaName = getIntent().getStringExtra("AREA_NAME");
        int selectedAreaCode = getIntent().getIntExtra("AREA_CODE", -1);
        if (selectedAreaCode == -1 || selectedAreaName == null || selectedAreaName.isEmpty())
            return;

        initView(selectedAreaName);

        eventViewModel.fetchFestivalAreaCode(selectedAreaCode);
        eventViewModel.isFetchingLiveData.observe(this, isFetching -> {
            if (isFetching) {
                findViewById(R.id.event_list_progressbar).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.event_list_progressbar).setVisibility(View.GONE);
            }
        });
        eventViewModel.festivalLiveData.observe(this, items -> {
            festivalItemList = new ArrayList<>();
            festivalItemList.addAll(items);
            eventListAdapter.updateItems(items);
        });
    }

    private void initView(String selectedAreaName) {
        // Toolbar 설정
        Toolbar toolbar = findViewById(R.id.event_list_toolbar);
        TextView toolbarText = toolbar.findViewById(R.id.toolbar_title_textview);
        toolbarText.setText(selectedAreaName);

        // RecyclerView 초기화
        RecyclerView eventListRecyclerView = findViewById(R.id.event_list_recyclerview);
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        eventListAdapter = new EventListAdapter(this);
        eventListAdapter.setOnItemClickListener(this);
        eventListRecyclerView.setAdapter(eventListAdapter);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this, EventDetailActivity.class);

        FestivalItem festivalItem = festivalItemList.get(position);
        intent.putExtra("CONTENT_ID", festivalItem.getContentid());
        intent.putExtra("CONTENT_TITLE", festivalItem.getTitle());
        intent.putExtra("CONTENT_IMAGE", festivalItem.getFirstimage());
        startActivity(intent);
    }
}