package com.example.finalproject.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.model.detail_info.DetailInfo;
import com.example.finalproject.model.detail_info.DetailItem;
import com.example.finalproject.model.festival.EventInfo;
import com.example.finalproject.model.festival.FestivalItem;
import com.example.finalproject.model.location_festival.LocationBasedFestival;
import com.example.finalproject.model.location_festival.LocationFestivalItem;
import com.example.finalproject.repository.TourismService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.example.finalproject.constant.URLConstant.BASE_URL;
import static com.example.finalproject.constant.URLConstant.CONTENT_TYPE_ID;
import static com.example.finalproject.constant.URLConstant.MOBILE_APP;
import static com.example.finalproject.constant.URLConstant.MOBILE_OS;
import static com.example.finalproject.constant.URLConstant.TYPE;

public class EventViewModel extends ViewModel {
    public MutableLiveData<List<FestivalItem>> festivalLiveData = new MutableLiveData<>();
    public MutableLiveData<List<LocationFestivalItem>> locationFestivalLiveData = new MutableLiveData<>();
    public MutableLiveData<DetailItem> detailLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isFetchingLiveData = new MutableLiveData<>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    private TourismService service = retrofit.create(TourismService.class);

    public void fetchFestivalByAreaCode(int areaCode) {
        isFetchingLiveData.postValue(true);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String now = format.format(date);

        service.fetchFestivalByAreaCode(MOBILE_OS, MOBILE_APP, TYPE, areaCode, now, 1).clone().enqueue(new Callback<EventInfo>() {
            @Override
            public void onResponse(Call<EventInfo> call, Response<EventInfo> response) {
                festivalLiveData.postValue(response.body().getResponse().getBody().getItems().getFestivalItem());
                isFetchingLiveData.postValue(false);
            }

            @Override
            public void onFailure(Call<EventInfo> call, Throwable t) {
                processFailure(t);
            }
        });
    }

    public void fetchFestivalByLocation(double mapX, double mapY) {
        isFetchingLiveData.postValue(true);

        service.fetchFestivalByLocation(MOBILE_OS, MOBILE_APP, TYPE, mapX, mapY, 2000, CONTENT_TYPE_ID, 1).clone().enqueue(new Callback<LocationBasedFestival>() {
            @Override
            public void onResponse(Call<LocationBasedFestival> call, Response<LocationBasedFestival> response) {
                locationFestivalLiveData.postValue(response.body().getResponse().getBody().getItems().getLocationFestivalItem());
                isFetchingLiveData.postValue(false);
            }

            @Override
            public void onFailure(Call<LocationBasedFestival> call, Throwable t) {
                processFailure(t);
            }
        });
    }

    public void fetchFestivalDetail(long contentId) {
        isFetchingLiveData.postValue(true);
        service.fetchFestivalDetail(MOBILE_OS, MOBILE_APP, TYPE, contentId, CONTENT_TYPE_ID).clone().enqueue(new Callback<DetailInfo>() {
            @Override
            public void onResponse(Call<DetailInfo> call, Response<DetailInfo> response) {
                detailLiveData.postValue(response.body().getResponse().getBody().getItems().getDetailItem());
                isFetchingLiveData.postValue(false);
            }

            @Override
            public void onFailure(Call<DetailInfo> call, Throwable t) {
                processFailure(t);
            }
        });
    }

    private void processFailure(Throwable throwable) {
        Log.d("jsontest", "onFailure: " + throwable.getMessage());
        isFetchingLiveData.postValue(false);
    }
}
