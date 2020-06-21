package com.example.finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.viewmodel.EventViewModel;

public class EventDetailActivity extends AppCompatActivity {
    private EventViewModel eventViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        TextView textView = findViewById(R.id.detail_textview);
        long contentId = getIntent().getLongExtra("CONTENT_ID", -1);
        if(contentId != -1) {
            eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
            eventViewModel.fetchFestivalDetail(contentId);
            eventViewModel.detailLiveData.observe(this, detailItem -> {
                textView.setText(detailItem.getSponsor1());
            });
        } else {
            finish();
        }
    }
}