package com.example.finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject.R;

public class CategorySelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        findViewById(R.id.select_region_textview).setOnClickListener(view -> {
            Intent intent = new Intent(this, EventListActivity.class);
            startActivity(intent);
        });
    }
}