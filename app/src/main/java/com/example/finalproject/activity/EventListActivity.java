package com.example.finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
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

        initRecyclerView();

        eventViewModel.fetchFestival();
        eventViewModel.isFetchingLiveData.observe(this, isFetching -> {
            if(isFetching) {
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
        eventViewModel.festivalRegionLiveData.observe(this, region -> {
            TextView textView = findViewById(R.id.event_list_location_name_textview);
            textView.setText(region);
        });
    }

    private void initRecyclerView() {
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