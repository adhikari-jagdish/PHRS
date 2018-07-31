package com.vayamtech.healthe_cord.Model.ResponsePojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//*Created by Jagadish on 7/31/2018.*/
public class ResponsePojo {

    @SerializedName("responseStatus")
    @Expose
    private ResponseStatus responseStatus;

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
