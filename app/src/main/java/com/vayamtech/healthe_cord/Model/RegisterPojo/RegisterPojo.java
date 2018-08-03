package com.vayamtech.healthe_cord.Model.RegisterPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//*Created by Jagadish on 7/31/2018.*/
public class RegisterPojo {

    @SerializedName("responseStatus")
    @Expose
    private ResponseStatus responseStatus;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("DoB")
    @Expose
    private String dob;

    @SerializedName("Gender")
    @Expose
    private String gender;

    @SerializedName("Email Id")
    @Expose
    private String emailId;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("Address")
    @Expose
    private String address;

    @SerializedName("Contact No.")
    @Expose
    private String contactNo;

    @SerializedName("City Id")
    @Expose
    private String cityId;

    @SerializedName("Pin Code")
    @Expose
    private String pinCode;

    @SerializedName("Y")
    @Expose
    private String terms;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }


}
