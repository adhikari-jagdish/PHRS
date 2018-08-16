package com.vayamtech.healthe_cord.Model.RegisterPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//*Created by Jagadish on 8/10/2018.*/
public class masterList {

    @SerializedName("value")
    @Expose
    public String stateValue;

    @SerializedName("name")
    @Expose
    public String stateName;

    public masterList(String stateValue, String stateName) {
        this.stateValue = stateValue;
        this.stateName = stateName;
    }

    public String getStateValue() {
        return stateValue;
    }

    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
