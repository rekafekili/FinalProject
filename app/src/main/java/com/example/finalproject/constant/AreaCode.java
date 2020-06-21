package com.example.finalproject.constant;

import androidx.annotation.DrawableRes;

import com.example.finalproject.R;

public enum AreaCode {
    SEOUL(1, "서울특별시", R.drawable.noimage),
    INCHEON(2, "인천광역시", R.drawable.noimage),
    DAEJEON(3, "대전광역시", R.drawable.noimage),
    DAEGU(4, "대구광역시", R.drawable.noimage),
    GWANGJU(5, "광주광역시", R.drawable.noimage),
    BUSAN(6, "부산광역시", R.drawable.noimage),
    ULSAN(7, "울산광역시", R.drawable.noimage),
    SEJONG(8, "세종특별자치시", R.drawable.noimage),
    GYEONGGI(31, "경기도", R.drawable.noimage),
    GANGWON(32, "강원도", R.drawable.noimage),
    CHUNGBUK(33, "충청북도", R.drawable.noimage),
    CHUNGNAM(34, "충청남도", R.drawable.noimage),
    GYEONGBUK(35, "경상북도", R.drawable.noimage),
    GYEONGNAM(36, "경상남도", R.drawable.noimage),
    JEONBUK(37, "전라북도", R.drawable.noimage),
    JEONNAM(38, "전라남도", R.drawable.noimage),
    JEJU(39, "제주도", R.drawable.noimage);

    final private int areaCode;
    final private String areaName;
    @DrawableRes final private int drawableRes;

    public int getAreaCode() {
        return areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    AreaCode(int areaCode, String areaName, @DrawableRes int drawableRes) {
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.drawableRes = drawableRes;
    }
}
