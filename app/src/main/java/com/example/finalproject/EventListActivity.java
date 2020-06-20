package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finalproject.adapter.EventListAdapter;

public class EventListActivity extends AppCompatActivity {
    private EventViewModel eventViewModel;
    private EventListAdapter eventListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        initRecyclerView();

        eventViewModel.fetchAreaCode();
        eventViewModel.areaLiveData.observe(this, areaList -> eventListAdapter.updateItems(areaList));
    }

    private void initRecyclerView() {
        RecyclerView eventListRecyclerView = findViewById(R.id.event_list_recyclerview);
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        eventListAdapter = new EventListAdapter(this);
        eventListRecyclerView.setAdapter(eventListAdapter);
    }
}