package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finalproject.adapter.EventListAdapter;

public class EventListActivity extends AppCompatActivity {
    private RecyclerView eventListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        initRecyclerView();

    }

    private void initRecyclerView() {
        eventListRecyclerView = findViewById(R.id.event_list_recyclerview);
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        EventListAdapter eventListAdapter = new EventListAdapter(this);
        eventListRecyclerView.setAdapter(eventListAdapter);
    }
}