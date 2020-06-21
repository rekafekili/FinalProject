
package com.example.finalproject.model.detail_info;

import com.squareup.moshi.Json;

public class Items {

    @Json(name = "item")
    private DetailItem item;

    public DetailItem getDetailItem() {
        return item;
    }

    public void setDetailItem(DetailItem item) {
        this.item = item;
    }

}
