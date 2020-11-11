

package com.creasia.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @Expose
    @SerializedName("ca_company_id")
    private String companyId;

    @Expose
    @SerializedName("ca_user_id")
    private String userId;

    @Expose
    @SerializedName("ca_project_id")
    private String projectId;

    @Expose
    @SerializedName("ca_user_full_name")
    private String userFullName;

    @Expose
    @SerializedName("ca_company_user_name")
    private String companyUserName;


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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getCompanyUserName() {
        return companyUserName;
    }

    public void setCompanyUserName(String companyUserName) {
        this.companyUserName = companyUserName;
    }
}
