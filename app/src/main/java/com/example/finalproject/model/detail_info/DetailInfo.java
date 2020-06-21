
package com.example.finalproject.model.detail_info;

import com.squareup.moshi.Json;

public class DetailInfo {

    @Json(name = "response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
