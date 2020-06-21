package com.example.finalproject.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.model.detail_info.DetailInfo;
import com.example.finalproject.model.detail_info.DetailItem;
import com.example.finalproject.model.festival.EventInfo;
import com.example.finalproject.model.festival.FestivalItem;
import com.example.finalproject.repository.TourismService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.example.finalproject.constant.URLConstant.BASE_URL;
import static com.example.finalproject.constant.URLConstant.MOBILE_APP;
import static com.example.finalproject.constant.URLConstant.MOBILE_OS;
import static com.example.finalproject.constant.URLConstant.TYPE;

public class EventViewModel extends ViewModel {
//    public MutableLiveData<List<Item>> areaLiveData = new MutableLiveData<>();
    public MutableLiveData<List<FestivalItem>> festivalLiveData = new MutableLiveData<>();
    public MutableLiveData<DetailItem> detailLiveData = new MutableLiveData<>();
    public MutableLiveData<String> festivalRegionLiveData = new MutableLiveData<>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    private TourismService service = retrofit.create(TourismService.class);

//    public void fetchAreaCode() {
//        service.fetchAreaCode(MOBILE_OS, MOBILE_APP, TYPE).clone().enqueue(new Callback<Area>() {
//            @Override
//            public void onResponse(Call<Area> call, Response<Area> response) {
//                Log.d("jsontest", "onResponse: " + response.body().getResponse().getBody().getItems().getItem());
//                areaLiveData.postValue(response.body().getResponse().getBody().getItems().getItem());
//            }
//
//            @Override
//            public void onFailure(Call<Area> call, Throwable t) {
//
//            }
//        });
//    }
    public void fetchFestival() {
        service.fetchFestival(MOBILE_OS, MOBILE_APP, TYPE, 34).clone().enqueue(new Callback<EventInfo>() {
            @Override
            public void onResponse(Call<EventInfo> call, Response<EventInfo> response) {
                festivalLiveData.postValue(response.body().getResponse().getBody().getItems().getFestivalItem());
                festivalRegionLiveData.postValue("충청남도");
            }

            @Override
            public void onFailure(Call<EventInfo> call, Throwable t) {
                Log.d("jsontest", "onFailure: " + t.getMessage());
            }
        });
    }

    public void fetchFestivalDetail(long contentId) {
        service.fetchFestivalDetail(MOBILE_OS, MOBILE_APP, TYPE, contentId, 15).clone().enqueue(new Callback<DetailInfo>() {
            @Override
            public void onResponse(Call<DetailInfo> call, Response<DetailInfo> response) {
                detailLiveData.postValue(response.body().getResponse().getBody().getItems().getDetailItem());
            }

            @Override
            public void onFailure(Call<DetailInfo> call, Throwable t) {
                Log.d("jsontest", "onFailure: " + t.getMessage());
            }
        });
    }
}
