package com.example.finalproject.repository;

import com.example.finalproject.model.detail_info.DetailInfo;
import com.example.finalproject.model.festival.EventInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.finalproject.constant.URLConstant.SERVICE_KEY;

public interface TourismService {
    // 행사정보 조회
    @GET("searchFestival" + SERVICE_KEY)
    Call<EventInfo> fetchFestival(@Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp, @Query("_type") String type, @Query("areaCode") int areaCode, @Query("eventStartDate") String startDate);

    // 상세 정보 조회
    @GET("detailIntro" + SERVICE_KEY)
    Call<DetailInfo> fetchFestivalDetail(@Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp, @Query("_type") String type, @Query("contentId") long contentId, @Query("contentTypeId") int contentTypeId);
}
