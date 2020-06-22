package com.example.finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.adapter.AreaListAdapter;
import com.example.finalproject.constant.AreaCode;

public class AreaSelectActivity extends AppCompatActivity implements AreaListAdapter.OnItemClickListener{
    private AreaCode[] areaCodeList = AreaCode.values();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_select);

        Toolbar toolbar = findViewById(R.id.area_select_toolbar);
        TextView textView = toolbar.findViewById(R.id.toolbar_title_textview);
        textView.setText("지역 선택");

        RecyclerView recyclerView = findViewById(R.id.area_select_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        AreaListAdapter areaListAdapter = new AreaListAdapter(this, areaCodeList);
        areaListAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(areaListAdapter);
    }

    @Override
    public void onItemClicked(View view, int position) {
        AreaCode selectedArea = areaCodeList[position];
        Intent intent = new Intent(this, AreaEventListActivity.class);
        intent.putExtra("AREA_NAME", selectedArea.getAreaName());
        intent.putExtra("AREA_CODE", selectedArea.getAreaCode());
        startActivity(intent);
    }
}