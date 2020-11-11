
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


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Observable<ResponseObject<LoginResponse>> doLoginApiCall(RequestBody userName, RequestBody pass) {
        return ApiUtils.getAPIService().doLoginApiCall(userName,pass);
    }


    @Override
    public Observable<LogoutResponse> doLogoutApiCall() {
        return ApiUtils.getAPIService().doLogoutApiCall();
    }

    @Override
    public Observable<ResponseArray<TaskResponse>> getTaskApiCall(String userId, String companyId, String page) {
        return ApiUtils.getAPIService().getTaskApiCall(userId,companyId,page);
    }

    @Override
    public Observable<ResponseObject> addImageApiCall(RequestBody longitude, RequestBody latitude, MultipartBody.Part uploadFile, RequestBody customerImg1, RequestBody customerImg2, RequestBody routePlanId, RequestBody companyId, RequestBody userId, RequestBody projectId, RequestBody companyUserName, RequestBody userFullName, RequestBody customerId, RequestBody storeType, RequestBody houseNumber, RequestBody streetName, RequestBody wardName, RequestBody districtName, RequestBody cityName) {
        return ApiUtils.getAPIService().addImageApiCall(longitude, latitude, uploadFile, customerImg1, customerImg2, routePlanId, companyId, userId, projectId, companyUserName, userFullName, customerId, storeType, houseNumber, streetName, wardName, districtName, cityName);
    }

    @Override
    public Observable<AddTaskResponse> addCustomerApiCall(RequestBody companyId, RequestBody userId, RequestBody wardName, RequestBody cityName, RequestBody districtName, RequestBody customerName, RequestBody streetName, RequestBody posterCheckInLat, RequestBody posterCheckIng, RequestBody routePlantId, RequestBody projectId, RequestBody storeType, RequestBody houseNumber, RequestBody customerId, RequestBody customerStatus) {
        return ApiUtils.getAPIService().addCustomerApiCall(companyId, userId, wardName, cityName, districtName, customerName, streetName, posterCheckInLat, posterCheckIng, routePlantId, projectId, storeType, houseNumber, customerId, customerStatus);
    }

    @Override
    public Observable<AddTaskResponse> createNewKpiCustomer(RequestBody userId, RequestBody companyId) {
        return ApiUtils.getAPIService().createNewKpiCustomer(userId, companyId);
    }



    @Override
    public Observable<OpenSourceResponse> getOpenSourceApiCall() {
        return ApiUtils.getAPIService().getOpenSourceApiCall();
    }


}

