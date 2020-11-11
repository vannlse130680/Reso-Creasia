package com.creasia.data.network.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class ResponseArray<T> {
    @SerializedName("data")
    @Expose
    @Nullable
    private ArrayList<T> responseData = null;

    @SerializedName("message")
    @Expose
    private String responseMessage;

    @SerializedName("code")
    @Expose
    private String responseCode;

    @Nullable
    public ArrayList<T> getResponseData() {
        return responseData;
    }

    public void setResponseData(@Nullable ArrayList<T> responseData) {
        this.responseData = responseData;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
