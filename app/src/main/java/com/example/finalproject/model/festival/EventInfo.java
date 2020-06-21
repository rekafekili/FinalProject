
package com.example.finalproject.model.festival;

import com.squareup.moshi.Json;

public class EventInfo {

    @Json(name = "response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
