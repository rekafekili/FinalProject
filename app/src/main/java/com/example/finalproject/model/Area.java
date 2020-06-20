package com.example.finalproject.model;

import com.squareup.moshi.Json;

public class Area {
    @Json(name = "code")
    private int code;

    @Json(name = "name")
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
