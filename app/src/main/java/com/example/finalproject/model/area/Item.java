
package com.example.finalproject.model.area;

import com.squareup.moshi.Json;

public class Item {

    @Json(name = "code")
    private Integer code;
    @Json(name = "name")
    private String name;
    @Json(name = "rnum")
    private Integer rnum;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRnum() {
        return rnum;
    }

    public void setRnum(Integer rnum) {
        this.rnum = rnum;
    }

}
