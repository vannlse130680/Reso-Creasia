package com.creasia.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddTaskResponse {
    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("ca_routeplan_id")
    private String routePlanId;

    @Expose
    @SerializedName("ca_kpi_customer")
    private String customerId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRoutePlanId() {
        return routePlanId;
    }

    public void setRoutePlanId(String routePlanId) {
        this.routePlanId = routePlanId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
