package com.example.finalproject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.model.Area;
import com.example.finalproject.repository.TourismService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.example.finalproject.constant.URLConstant.BASE_URL;
import static com.example.finalproject.constant.URLConstant.MOBILE_APP;
import static com.example.finalproject.constant.URLConstant.MOBILE_OS;
import static com.example.finalproject.constant.URLConstant.SERVICE_KEY;

public class EventViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Area>> areaLiveData = new MutableLiveData<>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    private TourismService service = retrofit.create(TourismService.class);

    public void fetchAreaCode() {
        service.fetchAreaCode(SERVICE_KEY, MOBILE_OS, MOBILE_APP).clone().enqueue(new Callback<Area>() {
            @Override
            public void onResponse(Call<Area> call, Response<Area> response) {
            }

            @Override
            public void onFailure(Call<Area> call, Throwable t) {
                areaLiveData.postValue(new ArrayList<Area>());
            }
        });
    }
}
