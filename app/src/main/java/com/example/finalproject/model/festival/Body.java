
package com.example.finalproject.model.festival;

import com.squareup.moshi.Json;

public class Body {

    @Json(name = "items")
    private Items items;
    @Json(name = "numOfRows")
    private int numOfRows;
    @Json(name = "pageNo")
    private int pageNo;
    @Json(name = "totalCount")
    private int totalCount;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}
