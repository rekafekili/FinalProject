
package com.example.finalproject.model.festival;

import java.util.List;
import com.squareup.moshi.Json;

public class Items {

    @Json(name = "item")
    private List<FestivalItem> festivalItem = null;

    public List<FestivalItem> getFestivalItem() {
        return festivalItem;
    }

    public void setFestivalItem(List<FestivalItem> festivalItem) {
        this.festivalItem = festivalItem;
    }

}
