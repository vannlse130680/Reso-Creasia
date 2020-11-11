package com.creasia.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "task")
public class Task {
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "ca_company_id")
    private String ca_company_id;

    @SerializedName("ca_user_id")
    private String ca_user_id;

    @Property(nameInDb = "ward_name")
    private String ward_name;

    @Property(nameInDb = "city_name")
    private String city_name;

    @Property(nameInDb = "district_name")
    private String district_name;

    @Property(nameInDb = "ca_kpi_customer_name")
    private String ca_kpi_customer_name;

    @Property(nameInDb = "street_name")
    private String street_name;

    @Property(nameInDb = "ca_poster_check_in_lat")
    private String ca_poster_check_in_lat;

    @Property(nameInDb = "ca_poster_check_in_lng")
    private String ca_poster_check_in_lng;

    @Property(nameInDb = "ca_routeplan_id")
    private String ca_routeplan_id;

    @Property(nameInDb = "ca_project_id")
    private String ca_project_id;

    @Property(nameInDb = "store_type_select")
    private String store_type_select;

    @Property(nameInDb = "house_number")
    private String house_number;

    @Property(nameInDb = "ca_kpi_customer_id")
    private String ca_kpi_customer_id;

    @Property(nameInDb = "upload_file1")
    private String upload_file1;

    @Property(nameInDb = "upload_file2")
    private String upload_file2;

    @Property(nameInDb = "ca_kpi_customer_status")
    private String ca_kpi_customer_status;

    @Property(nameInDb = "ca_company_user_name")
    private String ca_company_user_name;

    @Property(nameInDb = "ca_user_full_name")
    private String ca_user_full_name;



    @Generated(hash = 1877970786)
    public Task(Long id, String ca_company_id, String ca_user_id, String ward_name,
            String city_name, String district_name, String ca_kpi_customer_name,
            String street_name, String ca_poster_check_in_lat,
            String ca_poster_check_in_lng, String ca_routeplan_id,
            String ca_project_id, String store_type_select, String house_number,
            String ca_kpi_customer_id, String upload_file1, String upload_file2,
            String ca_kpi_customer_status, String ca_company_user_name,
            String ca_user_full_name) {
        this.id = id;
        this.ca_company_id = ca_company_id;
        this.ca_user_id = ca_user_id;
        this.ward_name = ward_name;
        this.city_name = city_name;
        this.district_name = district_name;
        this.ca_kpi_customer_name = ca_kpi_customer_name;
        this.street_name = street_name;
        this.ca_poster_check_in_lat = ca_poster_check_in_lat;
        this.ca_poster_check_in_lng = ca_poster_check_in_lng;
        this.ca_routeplan_id = ca_routeplan_id;
        this.ca_project_id = ca_project_id;
        this.store_type_select = store_type_select;
        this.house_number = house_number;
        this.ca_kpi_customer_id = ca_kpi_customer_id;
        this.upload_file1 = upload_file1;
        this.upload_file2 = upload_file2;
        this.ca_kpi_customer_status = ca_kpi_customer_status;
        this.ca_company_user_name = ca_company_user_name;
        this.ca_user_full_name = ca_user_full_name;
    }

    @Generated(hash = 733837707)
    public Task() {
    }

    

    public String getCa_company_id() {
        return ca_company_id;
    }

    public void setCa_company_id(String ca_company_id) {
        this.ca_company_id = ca_company_id;
    }

    public String getCa_user_id() {
        return ca_user_id;
    }

    public void setCa_user_id(String ca_user_id) {
        this.ca_user_id = ca_user_id;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getCa_kpi_customer_name() {
        return ca_kpi_customer_name;
    }

    public void setCa_kpi_customer_name(String ca_kpi_customer_name) {
        this.ca_kpi_customer_name = ca_kpi_customer_name;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getCa_poster_check_in_lat() {
        return ca_poster_check_in_lat;
    }

    public void setCa_poster_check_in_lat(String ca_poster_check_in_lat) {
        this.ca_poster_check_in_lat = ca_poster_check_in_lat;
    }

    public String getCa_poster_check_in_lng() {
        return ca_poster_check_in_lng;
    }

    public void setCa_poster_check_in_lng(String ca_poster_check_in_lng) {
        this.ca_poster_check_in_lng = ca_poster_check_in_lng;
    }

    public String getCa_routeplan_id() {
        return ca_routeplan_id;
    }

    public void setCa_routeplan_id(String ca_routeplan_id) {
        this.ca_routeplan_id = ca_routeplan_id;
    }

    public String getCa_project_id() {
        return ca_project_id;
    }

    public void setCa_project_id(String ca_project_id) {
        this.ca_project_id = ca_project_id;
    }

    public String getStore_type_select() {
        return store_type_select;
    }

    public void setStore_type_select(String store_type_select) {
        this.store_type_select = store_type_select;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getCa_kpi_customer_id() {
        return ca_kpi_customer_id;
    }

    public void setCa_kpi_customer_id(String ca_kpi_customer_id) {
        this.ca_kpi_customer_id = ca_kpi_customer_id;
    }

    public String getUpload_file1() {
        return upload_file1;
    }

    public void setUpload_file1(String upload_file1) {
        this.upload_file1 = upload_file1;
    }

    public String getUpload_file2() {
        return upload_file2;
    }

    public void setUpload_file2(String upload_file2) {
        this.upload_file2 = upload_file2;
    }

    public String getCa_kpi_customer_status() {
        return ca_kpi_customer_status;
    }

    public void setCa_kpi_customer_status(String ca_kpi_customer_status) {
        this.ca_kpi_customer_status = ca_kpi_customer_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCa_company_user_name() {
        return ca_company_user_name;
    }

    public void setCa_company_user_name(String ca_company_user_name) {
        this.ca_company_user_name = ca_company_user_name;
    }

    public String getCa_user_full_name() {
        return ca_user_full_name;
    }

    public void setCa_user_full_name(String ca_user_full_name) {
        this.ca_user_full_name = ca_user_full_name;
    }
}
