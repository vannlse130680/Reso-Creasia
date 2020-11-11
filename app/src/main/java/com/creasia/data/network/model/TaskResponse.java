package com.creasia.data.network.model;

import com.creasia.data.db.model.Task;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskResponse {
    @SerializedName("ca_kpi_customer_id")
    @Expose
    private String caKpiCustomerId;
    @SerializedName("ca_kpi_id")
    @Expose
    private String caKpiId;
    @SerializedName("ca_routeplan_id")
    @Expose
    private String caRouteplanId;
    @SerializedName("ca_kpi_customer_name")
    @Expose
    private String caKpiCustomerName;
    @SerializedName("ca_kpi_customer_phone")
    @Expose
    private String caKpiCustomerPhone;
    @SerializedName("ca_kpi_customer_gender")
    @Expose
    private String caKpiCustomerGender;
    @SerializedName("ca_kpi_customer_email")
    @Expose
    private String caKpiCustomerEmail;
    @SerializedName("ca_kpi_customer_address")
    @Expose
    private String caKpiCustomerAddress;
    @SerializedName("ca_kpi_customer_day_of_birth")
    @Expose
    private String caKpiCustomerDayOfBirth;
    @SerializedName("ca_kpi_customer_money_exchange")
    @Expose
    private String caKpiCustomerMoneyExchange;
    @SerializedName("ca_kpi_customer_micro_num")
    @Expose
    private String caKpiCustomerMicroNum;
    @SerializedName("ca_kpi_customer_export_quality")
    @Expose
    private String caKpiCustomerExportQuality;
    @SerializedName("ca_kpi_customer_note")
    @Expose
    private String caKpiCustomerNote;
    @SerializedName("ca_kpi_customer_order_no")
    @Expose
    private String caKpiCustomerOrderNo;
    @SerializedName("ca_kpi_customer_img1")
    @Expose
    private String caKpiCustomerImg1;
    @SerializedName("ca_kpi_customer_img2")
    @Expose
    private String caKpiCustomerImg2;
    @SerializedName("ca_kpi_customer_img3")
    @Expose
    private String caKpiCustomerImg3;
    @SerializedName("ca_kpi_customer_is_updated")
    @Expose
    private String caKpiCustomerIsUpdated;
    @SerializedName("ca_kpi_customer_date")
    @Expose
    private String caKpiCustomerDate;
    @SerializedName("ca_kpi_customer_date_updated")
    @Expose
    private String caKpiCustomerDateUpdated;
    @SerializedName("ca_company_id")
    @Expose
    private String caCompanyId;
    @SerializedName("ca_kpi_customer_sale_amount")
    @Expose
    private String caKpiCustomerSaleAmount;
    @SerializedName("ca_gift_json")
    @Expose
    private String caGiftJson;
    @SerializedName("ca_fb_img_url")
    @Expose
    private String caFbImgUrl;
    @SerializedName("ca_fb_gift_id")
    @Expose
    private String caFbGiftId;
    @SerializedName("ca_project_id")
    @Expose
    private String caProjectId;
    @SerializedName("ca_kpi_customer_otp")
    @Expose
    private String caKpiCustomerOtp;
    @SerializedName("ca_kpi_customer_is_verified")
    @Expose
    private String caKpiCustomerIsVerified;
    @SerializedName("ca_kpi_customer_lw_turn")
    @Expose
    private String caKpiCustomerLwTurn;
    @SerializedName("ca_kpi_customer_status")
    @Expose
    private String caKpiCustomerStatus;

    private boolean loadMore = false;
    public boolean isLoadMore() {
        return loadMore;
    }

    public TaskResponse(boolean loadMore) {
        this.loadMore = loadMore;
    }

    public String getCaKpiCustomerId() {
        return caKpiCustomerId;
    }

    public void setCaKpiCustomerId(String caKpiCustomerId) {
        this.caKpiCustomerId = caKpiCustomerId;
    }

    public String getCaKpiId() {
        return caKpiId;
    }

    public void setCaKpiId(String caKpiId) {
        this.caKpiId = caKpiId;
    }

    public String getCaRouteplanId() {
        return caRouteplanId;
    }

    public void setCaRouteplanId(String caRouteplanId) {
        this.caRouteplanId = caRouteplanId;
    }

    public String getCaKpiCustomerName() {
        return caKpiCustomerName;
    }

    public void setCaKpiCustomerName(String caKpiCustomerName) {
        this.caKpiCustomerName = caKpiCustomerName;
    }

    public String getCaKpiCustomerPhone() {
        return caKpiCustomerPhone;
    }

    public void setCaKpiCustomerPhone(String caKpiCustomerPhone) {
        this.caKpiCustomerPhone = caKpiCustomerPhone;
    }

    public String getCaKpiCustomerGender() {
        return caKpiCustomerGender;
    }

    public void setCaKpiCustomerGender(String caKpiCustomerGender) {
        this.caKpiCustomerGender = caKpiCustomerGender;
    }

    public String getCaKpiCustomerEmail() {
        return caKpiCustomerEmail;
    }

    public void setCaKpiCustomerEmail(String caKpiCustomerEmail) {
        this.caKpiCustomerEmail = caKpiCustomerEmail;
    }

    public String getCaKpiCustomerAddress() {
        return caKpiCustomerAddress;
    }

    public void setCaKpiCustomerAddress(String caKpiCustomerAddress) {
        this.caKpiCustomerAddress = caKpiCustomerAddress;
    }

    public String getCaKpiCustomerDayOfBirth() {
        return caKpiCustomerDayOfBirth;
    }

    public void setCaKpiCustomerDayOfBirth(String caKpiCustomerDayOfBirth) {
        this.caKpiCustomerDayOfBirth = caKpiCustomerDayOfBirth;
    }

    public String getCaKpiCustomerMoneyExchange() {
        return caKpiCustomerMoneyExchange;
    }

    public void setCaKpiCustomerMoneyExchange(String caKpiCustomerMoneyExchange) {
        this.caKpiCustomerMoneyExchange = caKpiCustomerMoneyExchange;
    }

    public String getCaKpiCustomerMicroNum() {
        return caKpiCustomerMicroNum;
    }

    public void setCaKpiCustomerMicroNum(String caKpiCustomerMicroNum) {
        this.caKpiCustomerMicroNum = caKpiCustomerMicroNum;
    }

    public String getCaKpiCustomerExportQuality() {
        return caKpiCustomerExportQuality;
    }

    public void setCaKpiCustomerExportQuality(String caKpiCustomerExportQuality) {
        this.caKpiCustomerExportQuality = caKpiCustomerExportQuality;
    }

    public String getCaKpiCustomerNote() {
        return caKpiCustomerNote;
    }

    public void setCaKpiCustomerNote(String caKpiCustomerNote) {
        this.caKpiCustomerNote = caKpiCustomerNote;
    }

    public String getCaKpiCustomerOrderNo() {
        return caKpiCustomerOrderNo;
    }

    public void setCaKpiCustomerOrderNo(String caKpiCustomerOrderNo) {
        this.caKpiCustomerOrderNo = caKpiCustomerOrderNo;
    }

    public String getCaKpiCustomerImg1() {
        return caKpiCustomerImg1;
    }

    public void setCaKpiCustomerImg1(String caKpiCustomerImg1) {
        this.caKpiCustomerImg1 = caKpiCustomerImg1;
    }

    public String getCaKpiCustomerImg2() {
        return caKpiCustomerImg2;
    }

    public void setCaKpiCustomerImg2(String caKpiCustomerImg2) {
        this.caKpiCustomerImg2 = caKpiCustomerImg2;
    }

    public String getCaKpiCustomerImg3() {
        return caKpiCustomerImg3;
    }

    public void setCaKpiCustomerImg3(String caKpiCustomerImg3) {
        this.caKpiCustomerImg3 = caKpiCustomerImg3;
    }

    public String getCaKpiCustomerIsUpdated() {
        return caKpiCustomerIsUpdated;
    }

    public void setCaKpiCustomerIsUpdated(String caKpiCustomerIsUpdated) {
        this.caKpiCustomerIsUpdated = caKpiCustomerIsUpdated;
    }

    public String getCaKpiCustomerDate() {
        return caKpiCustomerDate;
    }

    public void setCaKpiCustomerDate(String caKpiCustomerDate) {
        this.caKpiCustomerDate = caKpiCustomerDate;
    }

    public String getCaKpiCustomerDateUpdated() {
        return caKpiCustomerDateUpdated;
    }

    public void setCaKpiCustomerDateUpdated(String caKpiCustomerDateUpdated) {
        this.caKpiCustomerDateUpdated = caKpiCustomerDateUpdated;
    }

    public String getCaCompanyId() {
        return caCompanyId;
    }

    public void setCaCompanyId(String caCompanyId) {
        this.caCompanyId = caCompanyId;
    }

    public String getCaKpiCustomerSaleAmount() {
        return caKpiCustomerSaleAmount;
    }

    public void setCaKpiCustomerSaleAmount(String caKpiCustomerSaleAmount) {
        this.caKpiCustomerSaleAmount = caKpiCustomerSaleAmount;
    }

    public String getCaGiftJson() {
        return caGiftJson;
    }

    public void setCaGiftJson(String caGiftJson) {
        this.caGiftJson = caGiftJson;
    }

    public String getCaFbImgUrl() {
        return caFbImgUrl;
    }

    public void setCaFbImgUrl(String caFbImgUrl) {
        this.caFbImgUrl = caFbImgUrl;
    }

    public String getCaFbGiftId() {
        return caFbGiftId;
    }

    public void setCaFbGiftId(String caFbGiftId) {
        this.caFbGiftId = caFbGiftId;
    }

    public String getCaProjectId() {
        return caProjectId;
    }

    public void setCaProjectId(String caProjectId) {
        this.caProjectId = caProjectId;
    }

    public String getCaKpiCustomerOtp() {
        return caKpiCustomerOtp;
    }

    public void setCaKpiCustomerOtp(String caKpiCustomerOtp) {
        this.caKpiCustomerOtp = caKpiCustomerOtp;
    }

    public String getCaKpiCustomerIsVerified() {
        return caKpiCustomerIsVerified;
    }

    public void setCaKpiCustomerIsVerified(String caKpiCustomerIsVerified) {
        this.caKpiCustomerIsVerified = caKpiCustomerIsVerified;
    }

    public String getCaKpiCustomerLwTurn() {
        return caKpiCustomerLwTurn;
    }

    public void setCaKpiCustomerLwTurn(String caKpiCustomerLwTurn) {
        this.caKpiCustomerLwTurn = caKpiCustomerLwTurn;
    }

    public String getCaKpiCustomerStatus() {
        return caKpiCustomerStatus;
    }

    public void setCaKpiCustomerStatus(String caKpiCustomerStatus) {
        this.caKpiCustomerStatus = caKpiCustomerStatus;
    }
}
