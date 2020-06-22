package com.example.finalproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.example.finalproject.databinding.ActivityEventDetailBinding;
import com.example.finalproject.viewmodel.EventViewModel;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

public class EventDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private EventViewModel eventViewModel;
    private ActivityEventDetailBinding binding;
    private MapView eventPlaceMap;
    private Marker marker;
    private LatLng markerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        eventPlaceMap = findViewById(R.id.detail_naver_mapview);
        eventPlaceMap.onCreate(savedInstanceState);
        marker = new Marker();

        long contentId = getIntent().getLongExtra("CONTENT_ID", -1);
        if (contentId == -1) return;

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        String title = getIntent().getStringExtra("CONTENT_TITLE");
        binding.detailTitleTextview.setText(title);

        String url = getIntent().getStringExtra("CONTENT_IMAGE");
        if(url != null) {
            Glide.with(this).load(url).fitCenter().into(binding.detailPosterImageview);
        } else {
            binding.detailPosterImageview.setVisibility(View.GONE);
            binding.detailNoPosterTextview.setVisibility(View.VISIBLE);
        }

        double latitude = getIntent().getDoubleExtra("LATITUDE", 0.0);
        double longitude = getIntent().getDoubleExtra("LONGITUDE", 0.0);

        Log.d("TEST", "onCreate: " + latitude + " : " + longitude);
        markerPosition = new LatLng(latitude, longitude);
        marker.setPosition(markerPosition);
        eventPlaceMap.getMapAsync(this);

        eventDataBinding(contentId);
    }

    // 네이버 맵 지도객체를 받는 콜백 메소드
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        naverMap.setCameraPosition(new CameraPosition(markerPosition, 14.5));
        marker.setMap(naverMap);
    }

    private void eventDataBinding(long contentId) {
        eventViewModel.fetchFestivalDetail(contentId);

        eventViewModel.isFetchingLiveData.observe(this, isFetching -> {
            if (isFetching) {
                findViewById(R.id.detail_progressbar).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.detail_progressbar).setVisibility(View.GONE);
            }
        });

        eventViewModel.detailLiveData.observe(this, detailItem -> {
            binding.detailEventPeriodTextview.setText(makePeriodString(detailItem.getEventstartdate().toString(), detailItem.getEventenddate().toString()));
            binding.detailEventPlaceTextview.setText(detailItem.getEventplace());
            binding.detailEventSpendTimeTextview.setText(detailItem.getSpendtimefestival());
            binding.detailSponsor1Textview.setText(detailItem.getSponsor1());
            binding.detailSponsor1TelTextview.setText(detailItem.getSponsor1tel());
            binding.detailUsetimefestivalTextview.setText(Html.fromHtml(detailItem.getUsetimefestival()));
        });
    }

    private String makePeriodString(String startDate, String endDate) {
        return String.format(getResources().getString(R.string.event_period_format),
                startDate.substring(0, 4), startDate.substring(4, 6), startDate.substring(6),
                endDate.substring(0, 4), endDate.substring(4, 6), endDate.substring(6));
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventPlaceMap.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventPlaceMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventPlaceMap.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        eventPlaceMap.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventPlaceMap.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventPlaceMap.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        eventPlaceMap.onLowMemory();
    }
}