package com.creasia.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskHeader {
    @Expose
    @SerializedName("ca_project_id")
    private String projectId;

    @Expose
    @SerializedName("page")
    private String page;

    public TaskHeader(String projectId, String page) {
        this.projectId = projectId;
        this.page = page;
    }

    public TaskHeader() {
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
