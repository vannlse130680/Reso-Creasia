package com.creasia.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class CustomerImageRequest {
    @Expose
    @SerializedName("ca_longitude")
    private double longitude;

    @Expose
    @SerializedName("ca_latitude")
    private double latitude;

//    @Expose
//    @SerializedName("upload_file")
    private File uploadFile;

    @Expose
    @SerializedName("ca_company_user_name")
    private String companyUserName;

    @Expose
    @SerializedName("ca_user_full_name")
    private String userFullName;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public File getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getCompanyUserName() {
        return companyUserName;
    }

    public void setCompanyUserName(String companyUserName) {
        this.companyUserName = companyUserName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
}
