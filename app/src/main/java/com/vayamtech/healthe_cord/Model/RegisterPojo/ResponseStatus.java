package com.vayamtech.healthe_cord.Model.RegisterPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//*Created by Jagadish on 7/31/2018.*/
public class ResponseStatus {

    @SerializedName("statusCode")
    @Expose
    private Boolean statusCode;
    @SerializedName("messageCode")
    @Expose
    private String messageCode;

    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
