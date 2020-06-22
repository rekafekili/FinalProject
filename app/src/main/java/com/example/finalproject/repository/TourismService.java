package com.example.finalproject.repository;

import com.example.finalproject.model.detail_info.DetailInfo;
import com.example.finalproject.model.festival.EventInfo;
import com.example.finalproject.model.location_festival.LocationBasedFestival;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.finalproject.constant.URLConstant.SERVICE_KEY;

public interface TourismService {
    // 행사정보 조회
    @GET("searchFestival" + SERVICE_KEY)
    Call<EventInfo> fetchFestivalByAreaCode(@Query("MobileOS") String mobileOS,
                                            @Query("MobileApp") String mobileApp,
                                            @Query("_type") String type,
                                            @Query("areaCode") int areaCode,
                                            @Query("eventStartDate") String startDate,
                                            @Query("arrange") char arrange,
                                            @Query("pageNo") int pageNo);

    // 상세 정보 조회
    @GET("detailIntro" + SERVICE_KEY)
    Call<DetailInfo> fetchFestivalDetail(@Query("MobileOS") String mobileOS,
                                         @Query("MobileApp") String mobileApp,
                                         @Query("_type") String type,
                                         @Query("contentId") long contentId,
                                         @Query("contentTypeId") int contentTypeId);

    // 위치 기반 행사정보 조회
    @GET("locationBasedList" + SERVICE_KEY)
    Call<LocationBasedFestival> fetchFestivalByLocation(@Query("MobileOS") String mobileOS,
                                                        @Query("MobileApp") String mobileApp,
                                                        @Query("_type") String type,
                                                        @Query("mapX") double mapX,
                                                        @Query("mapY") double mapY,
                                                        @Query("radius") int radius,
                                                        @Query("contentTypeId") int contentTypeId,
                                                        @Query("arrange") char arrange,
                                                        @Query("pageNo") int pageNo);
}
