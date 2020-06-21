
package com.example.finalproject.model.location_festival;

import java.util.List;
import com.squareup.moshi.Json;

public class Items {
    @Json(name = "item")
    private List<LocationFestivalItem> locationFestivalItem = null;

    public List<LocationFestivalItem> getLocationFestivalItem() {
        return locationFestivalItem;
    }

    public void setLocationFestivalItem(List<LocationFestivalItem> locationFestivalItem) {
        this.locationFestivalItem = locationFestivalItem;
    }
}
