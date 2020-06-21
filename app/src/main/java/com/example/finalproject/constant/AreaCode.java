package com.example.finalproject.constant;

import androidx.annotation.DrawableRes;

import com.example.finalproject.R;

public enum AreaCode {
    SEOUL(1, "서울특별시", R.drawable.symbol_seoul),
    INCHEON(2, "인천광역시", R.drawable.symbol_incheon),
    DAEJEON(3, "대전광역시", R.drawable.symbol_daejeon),
    DAEGU(4, "대구광역시", R.drawable.symbol_daegu),
    GWANGJU(5, "광주광역시", R.drawable.symbol_gwangju),
    BUSAN(6, "부산광역시", R.drawable.symbol_busan),
    ULSAN(7, "울산광역시", R.drawable.symbol_ulsan),
    SEJONG(8, "세종특별자치시", R.drawable.symbol_sejong),
    GYEONGGI(31, "경기도", R.drawable.symbol_gyenggi),
    GANGWON(32, "강원도", R.drawable.symbol_gangwon),
    CHUNGBUK(33, "충청북도", R.drawable.symbol_chungbuk),
    CHUNGNAM(34, "충청남도", R.drawable.symbol_chungnam),
    GYEONGBUK(35, "경상북도", R.drawable.symbol_gyeongbuk),
    GYEONGNAM(36, "경상남도", R.drawable.symbol_gyeongnam),
    JEONBUK(37, "전라북도", R.drawable.symbol_jeonbuk),
    JEONNAM(38, "전라남도", R.drawable.symbol_jeonnam),
    JEJU(39, "제주도", R.drawable.symbol_jeju);

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

    public static String getNameByCode(long code) {
        int areaCode = (int) code;
        for(AreaCode tmp : AreaCode.values()) {
            if(areaCode == tmp.getAreaCode()) {
                return tmp.getAreaName();
            }
        }
        return null;
    }

    AreaCode(int areaCode, String areaName, @DrawableRes int drawableRes) {
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.drawableRes = drawableRes;
    }
}
