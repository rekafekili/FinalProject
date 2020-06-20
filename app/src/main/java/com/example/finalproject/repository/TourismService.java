package com.example.finalproject.repository;

import com.example.finalproject.model.area.Area;
import com.example.finalproject.model.area.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.finalproject.constant.URLConstant.SERVICE_KEY;

public interface TourismService {
    // 지역코드 조회
    @GET("areaCode" + SERVICE_KEY)
    Call<Area> fetchAreaCode(@Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp, @Query("_type") String type);


}
