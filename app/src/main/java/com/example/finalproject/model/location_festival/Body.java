
package com.example.finalproject.model.location_festival;

import com.squareup.moshi.Json;

public class Body {

    @Json(name = "items")
    private Items items;
    @Json(name = "numOfRows")
    private Long numOfRows;
    @Json(name = "pageNo")
    private Long pageNo;
    @Json(name = "totalCount")
    private Long totalCount;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Long getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(Long numOfRows) {
        this.numOfRows = numOfRows;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

}
