package com.creasia.data.network;

import com.creasia.data.db.model.Task;
import com.creasia.data.network.model.CustomerImageResponse;
import com.creasia.data.network.model.AddTaskRequest;
import com.creasia.data.network.model.AddTaskResponse;
import com.creasia.data.network.model.LoginResponse;
import com.creasia.data.network.model.LogoutResponse;
import com.creasia.data.network.model.OpenSourceResponse;
import com.creasia.data.network.model.ResponseArray;
import com.creasia.data.network.model.ResponseObject;
import com.creasia.data.network.model.TaskResponse;
import com.creasia.utils.AppConstants;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiHelper {

    ApiHeader getApiHeader();

    // Login
    @POST(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
    @Multipart
    Observable<ResponseObject<LoginResponse>> doLoginApiCall(@Part("ca_user_name") RequestBody userName, @Part("ca_user_password") RequestBody pass);

    // Logout
    @POST(ApiEndPoint.ENDPOINT_LOGOUT)
    @Headers({
            "Content-Type:"+ AppConstants.CONTENT_TYPE
    })
    Observable<LogoutResponse> doLogoutApiCall();

    // task
    @GET(ApiEndPoint.ENDPOINT_TASK)
    @Headers({
            "Content-Type:"+ AppConstants.CONTENT_TYPE
    })
    Observable<ResponseArray<TaskResponse>> getTaskApiCall(@Query("ca_user_id") String userId, @Query("ca_company_id") String companyId, @Query("page") String page);

    // add customer image
    @POST(ApiEndPoint.ENDPOINT_ADD_IMAGE)
    @Multipart
    Observable<ResponseObject> addImageApiCall(@Part("ca_longitude") RequestBody longitude, @Part("ca_latitude")
            RequestBody latitude, @Part MultipartBody.Part uploadFile,@Part("ca_kpi_customer_img1") RequestBody customerImg1, @Part("ca_kpi_customer_img2") RequestBody customerImg2,
                                                      @Part("ca_routeplan_id") RequestBody routePlanId, @Part("ca_company_id") RequestBody companyId,
                                                      @Part("ca_user_id") RequestBody userId, @Part("ca_project_id") RequestBody projectId,
                                                      @Part("ca_company_user_name") RequestBody companyUserName, @Part("ca_user_full_name") RequestBody userFullName,  @Part("ca_kpi_customer_id") RequestBody customerId,
                                                      @Part("store_type_select") RequestBody storeType,  @Part("house_number") RequestBody houseNumber,  @Part("street_name") RequestBody streetName,  @Part("ward_name") RequestBody wardName,
                                                      @Part("district_name") RequestBody districtName,  @Part("city_name") RequestBody cityName);

    // Add task
    @POST(ApiEndPoint.ENDPOINT_ADD_CUSTOMER)
    @Multipart
    Observable<AddTaskResponse> addCustomerApiCall(@Part("ca_company_id") RequestBody companyId, @Part("ca_user_id") RequestBody userId, @Part("ward_name") RequestBody wardName,
                                                 @Part("city_name") RequestBody cityName, @Part("district_name") RequestBody districtName, @Part("ca_kpi_customer_name") RequestBody customerName,
                                                 @Part("street_name") RequestBody streetName, @Part("ca_poster_check_in_lat") RequestBody posterCheckInLat, @Part("ca_poster_check_in_lng") RequestBody posterCheckIng,
                                                 @Part("ca_routeplan_id") RequestBody routePlantId,@Part("ca_project_id") RequestBody projectId, @Part("store_type_select") RequestBody storeType,
                                                   @Part("house_number") RequestBody houseNumber,  @Part("ca_kpi_customer_id") RequestBody customerId, @Part("ca_kpi_customer_status") RequestBody customerStatus);
    // create_new_kpi_customer
    @POST(ApiEndPoint.ENDPOINT_CREATE_NEW_KPI)
    @Multipart
    Observable<AddTaskResponse> createNewKpiCustomer(@Part("ca_user_id") RequestBody userId, @Part("ca_company_id") RequestBody companyId);

    Observable<OpenSourceResponse> getOpenSourceApiCall();

}
