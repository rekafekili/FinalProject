package com.example.finalproject;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.model.area.Area;
import com.example.finalproject.model.area.Item;
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
    public MutableLiveData<List<Item>> areaLiveData = new MutableLiveData<>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    private TourismService service = retrofit.create(TourismService.class);

    public void fetchAreaCode() {
        service.fetchAreaCode(MOBILE_OS, MOBILE_APP, TYPE).clone().enqueue(new Callback<Area>() {
            @Override
            public void onResponse(Call<Area> call, Response<Area> response) {
                Log.d("jsontest", "onResponse: " + response.body().getResponse().getBody().getItems().getItem());
                areaLiveData.postValue(response.body().getResponse().getBody().getItems().getItem());
            }

            @Override
            public void onFailure(Call<Area> call, Throwable t) {

            }
        });
    }
}
