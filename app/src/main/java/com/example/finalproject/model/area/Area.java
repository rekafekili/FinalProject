
package com.example.finalproject.model.area;

import com.squareup.moshi.Json;

public class Area {

    @Json(name = "response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
