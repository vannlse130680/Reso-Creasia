package com.creasia.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddTaskRequest {

    @Expose
    @SerializedName("ca_company_id")
    private String companyId;

    @Expose
    @SerializedName("ca_user_id")
    private String userId;

    @Expose
    @SerializedName("ward_name")
    private String wardName;

    @Expose
    @SerializedName("city_name")
    private String cityName;

    @Expose
    @SerializedName("district_name")
    private String districtName;

    @Expose
    @SerializedName("ca_kpi_customer_name")
    private String customerName;

    @Expose
    @SerializedName("street_name")
    private String streetName;

    @Expose
    @SerializedName("ca_poster_check_in_lat")
    private String posterCheckInLat;

    @Expose
    @SerializedName("ca_poster_check_in_lng")
    private String posterCheckInLng;

    @Expose
    @SerializedName("ca_routeplan_id")
    private String routePlanId;

    @Expose
    @SerializedName("ca_project_id")
    private String projectId;

    @Expose
    @SerializedName("store_type_select")
    private String storeType;

    @Expose
    @SerializedName("house_number")
    private String houseNumber;

    @Expose
    @SerializedName("ca_kpi_customer_id")
    private String customerId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPosterCheckInLat() {
        return posterCheckInLat;
    }

    public void setPosterCheckInLat(String posterCheckInLat) {
        this.posterCheckInLat = posterCheckInLat;
    }

    public String getPosterCheckInLng() {
        return posterCheckInLng;
    }

    public void setPosterCheckInLng(String posterCheckInLng) {
        this.posterCheckInLng = posterCheckInLng;
    }

    public String getRoutePlanId() {
        return routePlanId;
    }

    public void setRoutePlanId(String routePlanId) {
        this.routePlanId = routePlanId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}
