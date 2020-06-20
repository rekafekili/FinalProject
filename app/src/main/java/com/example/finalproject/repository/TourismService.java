package com.example.finalproject.repository;

import com.example.finalproject.model.Area;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TourismService {
    // 지역코드 조회
    @GET("areaCode/")
    Call<Area> fetchAreaCode(@Query("ServiceKey") String serviceKey, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp);


}
