package com.vayamtech.healthe_cord.Model.RegisterPojo;

//*Created by Jagadish on 8/10/2018.*/
public class masterList {

    public int stateValue;
    public String stateName;

    public masterList(int stateValue, String stateName) {
        this.stateValue = stateValue;
        this.stateName = stateName;
    }

    public int getStateValue() {
        return stateValue;
    }

    public String getStateName() {
        return stateName;
    }
}
