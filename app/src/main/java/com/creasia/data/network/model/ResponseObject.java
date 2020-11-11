package com.creasia.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class ResponseObject <T>{
    @SerializedName("data")
    @Expose
    @Nullable
    private T responseData = null;

    @SerializedName("message")
    @Expose
    private String responseMessage;

    @SerializedName("ca_login_session_key")
    @Expose
    private String sessionKey;

    @SerializedName("code")
    @Expose
    private String responseCode;

    @Nullable
    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(@Nullable T responseData) {
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

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
