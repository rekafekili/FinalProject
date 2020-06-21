package com.example.finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.example.finalproject.databinding.ActivityEventDetailBinding;
import com.example.finalproject.viewmodel.EventViewModel;

public class EventDetailActivity extends AppCompatActivity {
    private EventViewModel eventViewModel;
    private ActivityEventDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        long contentId = getIntent().getLongExtra("CONTENT_ID", -1);
        if (contentId == -1) return;

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        String title = getIntent().getStringExtra("CONTENT_TITLE");
        binding.detailTitleTextview.setText(title);

        String url = getIntent().getStringExtra("CONTENT_IMAGE");
        if (url != null) {
            Glide.with(this).load(url).fitCenter().into(binding.detailPosterImageview);
        }

        eventDataBinding(contentId);
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
}