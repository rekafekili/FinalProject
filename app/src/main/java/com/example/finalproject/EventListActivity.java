package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finalproject.adapter.EventListAdapter;

public class EventListActivity extends AppCompatActivity {
    private RecyclerView eventListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        eventListRecyclerView = findViewById(R.id.event_list_recyclerview);

        EventListAdapter eventListAdapter = new EventListAdapter(this);
        eventListRecyclerView.setAdapter(eventListAdapter);
    }
}