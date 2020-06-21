
package com.example.finalproject.model.area;

import java.util.List;
import com.squareup.moshi.Json;

public class Items {

    @Json(name = "item")
    private List<AreaItem> areaItem = null;

    public List<AreaItem> getAreaItem() {
        return areaItem;
    }

    public void setAreaItem(List<AreaItem> areaItem) {
        this.areaItem = areaItem;
    }

}
