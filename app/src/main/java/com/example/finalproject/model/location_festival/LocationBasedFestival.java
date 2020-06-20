
package com.example.finalproject.model.location_festival;

import com.squareup.moshi.Json;

public class LocationBasedFestival {

    @Json(name = "response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
