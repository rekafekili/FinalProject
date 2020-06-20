
package com.example.finalproject.model.area;

import com.squareup.moshi.Json;

public class Body {

    @Json(name = "items")
    private Items items;
    @Json(name = "numOfRows")
    private Integer numOfRows;
    @Json(name = "pageNo")
    private Integer pageNo;
    @Json(name = "totalCount")
    private Integer totalCount;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Integer getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(Integer numOfRows) {
        this.numOfRows = numOfRows;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
