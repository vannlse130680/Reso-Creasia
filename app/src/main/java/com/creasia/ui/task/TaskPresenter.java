package com.creasia.ui.task;

import com.androidnetworking.error.ANError;
import com.creasia.R;
import com.creasia.data.DataManager;
import com.creasia.data.network.model.AddTaskRequest;
import com.creasia.data.network.model.AddTaskResponse;
import com.creasia.data.network.model.CustomerImageRequest;
import com.creasia.data.network.model.CustomerImageResponse;
import com.creasia.data.db.model.Task;
import com.creasia.data.network.model.ResponseObject;
import com.creasia.ui.base.BasePresenter;
import com.creasia.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class TaskPresenter<V extends TaskMvpView> extends BasePresenter<V>
        implements TaskMvpPresenter<V> {
    private static final String TAG = "TaskPresenter";
    private static final String API_IMAGE_1 = "API_IMAGE_1";
    private static final String API_IMAGE_2 = "API_IMAGE_2";
    private static final String API_INFO = "API_INFO";
    private ArrayList<APIResult> apiResults = new ArrayList<>();
    @Inject
    public TaskPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onSave(CustomerImageRequest request1, CustomerImageRequest request2, AddTaskRequest task) {
        apiResults.clear();
        String resultPatchImage1="";
        String resultPatchImage2="";
        if (request1 == null || request2 == null) {
            getMvpView().onError(R.string.empty_object);
            return;
        }
        if(request1.getUploadFile() == null ){
            getMvpView().onError(R.string.invalid_image_1);
            return;
        }
        if(request2.getUploadFile() == null ){
            getMvpView().onError(R.string.invalid_image_2);
            return;
        }
        if ( task.getCustomerName() == null ||  task.getCustomerName().isEmpty()) {
            getMvpView().onError(R.string.err_store_name);
            return;
        }
        if ( task.getHouseNumber() == null ||  task.getHouseNumber().isEmpty()) {
            getMvpView().onError(R.string.err_address_number);
            return;
        }
        if ( task.getStreetName() == null ||  task.getStreetName().isEmpty()) {
            getMvpView().onError(R.string.err_street_name);
            return;
        }
        if ( task.getWardName() == null ||  task.getWardName().isEmpty()) {
            getMvpView().onError(R.string.err_town);
            return;
        }
        if ( task.getCityName() == null ||  task.getCityName().isEmpty()) {
            getMvpView().onError(R.string.err_city);
            return;
        }
        getMvpView().showLoading();

        RequestBody longitude =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getPosterCheckInLng()));
        RequestBody latitude =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getPosterCheckInLat()));
        RequestBody companyUserName =RequestBody.create(MediaType.parse("text/plain"), getDataManager().getCompanyUserName());
        RequestBody userFullName =RequestBody.create(MediaType.parse("text/plain"), getDataManager().getUserFullName());
        RequestBody projectId =RequestBody.create(MediaType.parse("text/plain"), getDataManager().getProjectId());
        RequestBody userId =RequestBody.create(MediaType.parse("text/plain"), getDataManager().getCurrentUserId());
        RequestBody companyId =RequestBody.create(MediaType.parse("text/plain"), getDataManager().getCompanyId());
        RequestBody routePlanId =RequestBody.create(MediaType.parse("text/plain"), task.getRoutePlanId());
        RequestBody customerId =RequestBody.create(MediaType.parse("text/plain"), task.getCustomerId());
        //add info store
        RequestBody wardName =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getWardName()));
        RequestBody cityName =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getCityName()));
        RequestBody districtName =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getDistrictName()));
        RequestBody streetName =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getStreetName()));
        RequestBody customerName =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getCustomerName()));
        RequestBody storeType =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getStoreType()));
        RequestBody houseNumber =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getHouseNumber()));
        RequestBody customerStatus =RequestBody.create(MediaType.parse("text/plain"), "1");
        //upload image 1
        //part body
        RequestBody uploadFile1 =RequestBody.create(MediaType.parse("multipart/form-data"),request1.getUploadFile());
        MultipartBody.Part uploadFileData1 = MultipartBody.Part.createFormData("upload_file", request1.getUploadFile().getName(), uploadFile1);
        RequestBody file1CustomerImage1 =RequestBody.create(MediaType.parse("text/plain"),"1");
        RequestBody file1CustomerImage2 =RequestBody.create(MediaType.parse("text/plain"), "0");

        getCompositeDisposable().add(getDataManager()
                .addImageApiCall(longitude,latitude,uploadFileData1,file1CustomerImage1,file1CustomerImage2,routePlanId,companyId,userId,projectId,companyUserName,userFullName, customerId,storeType,houseNumber,streetName,wardName,districtName,cityName)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ResponseObject>() {
                    @Override
                    public void accept(ResponseObject response) throws Exception {
                        APIResult apiResult= new APIResult(API_IMAGE_1,response.getResponseCode());
                        checkUpLoad(apiResult);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }
                        APIResult apiResult= new APIResult(API_IMAGE_1,"-1");
                        checkUpLoad(apiResult);
                    }
                }));

        //upload image 2
        //part body
        RequestBody uploadFile2 =RequestBody.create(MediaType.parse("multipart/form-data"),request2.getUploadFile());
        MultipartBody.Part uploadFileData2 = MultipartBody.Part.createFormData("upload_file", request2.getUploadFile().getName(), uploadFile2);
        RequestBody file2CustomerImage1 =RequestBody.create(MediaType.parse("text/plain"),"0");
        RequestBody file2CustomerImage2 =RequestBody.create(MediaType.parse("text/plain"), "1");
        getCompositeDisposable().add(getDataManager()
                .addImageApiCall(longitude,latitude,uploadFileData2,file2CustomerImage1,file2CustomerImage2,routePlanId,companyId,userId,projectId,companyUserName,userFullName,customerId,storeType,houseNumber,streetName,wardName,districtName,cityName)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ResponseObject>() {
                    @Override
                    public void accept(ResponseObject response) throws Exception {
                        APIResult apiResult= new APIResult(API_IMAGE_2,response.getResponseCode());
                        checkUpLoad(apiResult);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        APIResult apiResult= new APIResult(API_IMAGE_2,"-1");
                        checkUpLoad(apiResult);
                    }
                }));


        getCompositeDisposable().add(getDataManager()
                .addCustomerApiCall(companyId, userId, wardName,cityName,districtName,customerName,streetName,latitude,longitude,routePlanId,projectId,storeType,houseNumber,customerId,customerStatus)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<AddTaskResponse>() {
                    @Override
                    public void accept(AddTaskResponse response) throws Exception {
                        APIResult apiResult= new APIResult(API_INFO,response.getCode());
                        checkUpLoad(apiResult);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        APIResult apiResult= new APIResult(API_INFO,"-1");
                        checkUpLoad(apiResult);
                    }
                }));


    }

    @Override
    public void onSaveOffline(CustomerImageRequest request1, CustomerImageRequest request2, AddTaskRequest task) {
        if (request1 == null || request2 == null) {
            getMvpView().onError(R.string.empty_object);
            return;
        }
        if(request1.getUploadFile() == null ){
            getMvpView().onError(R.string.invalid_image_1);
            return;
        }
        if(request2.getUploadFile() == null ){
            getMvpView().onError(R.string.invalid_image_2);
            return;
        }
        if ( task.getCustomerName() == null ||  task.getCustomerName().isEmpty()) {
            getMvpView().onError(R.string.err_store_name);
            return;
        }
        if ( task.getHouseNumber() == null ||  task.getHouseNumber().isEmpty()) {
            getMvpView().onError(R.string.err_address_number);
            return;
        }
        if ( task.getStreetName() == null ||  task.getStreetName().isEmpty()) {
            getMvpView().onError(R.string.err_street_name);
            return;
        }
        if ( task.getWardName() == null ||  task.getWardName().isEmpty()) {
            getMvpView().onError(R.string.err_town);
            return;
        }
        if ( task.getCityName() == null ||  task.getCityName().isEmpty()) {
            getMvpView().onError(R.string.err_city);
            return;
        }
        Task taskData= new Task();
        taskData.setCa_company_id(getDataManager().getCompanyId());
        taskData.setCa_user_id(getDataManager().getCurrentUserId());
        taskData.setWard_name(task.getWardName());
        taskData.setCity_name(task.getCityName());
        taskData.setDistrict_name(task.getDistrictName());
        taskData.setCa_kpi_customer_name(task.getCustomerName());
        taskData.setStreet_name(task.getStreetName());
        taskData.setCa_poster_check_in_lat(task.getPosterCheckInLat());
        taskData.setCa_poster_check_in_lng(task.getPosterCheckInLng());
        taskData.setCa_project_id(getDataManager().getProjectId());
        taskData.setStore_type_select(task.getStoreType());
        taskData.setHouse_number(task.getHouseNumber());
        taskData.setUpload_file1(request1.getUploadFile().getPath());
        taskData.setUpload_file2(request2.getUploadFile().getPath());
        taskData.setCa_kpi_customer_status("1");
        taskData.setCa_company_user_name( getDataManager().getCompanyUserName());
        taskData.setCa_user_full_name( getDataManager().getUserFullName());
        getCompositeDisposable().add(getDataManager()
                .insertTask(taskData)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        getMvpView().showMessage(R.string.save_data_success);
                        getMvpView().openMain();
                    }
                } , new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }
                        throwable.printStackTrace();
                        getMvpView().onError(R.string.some_error);
                    }
                }));

    }

    private void checkUpLoad(APIResult apiResult){
        apiResults.add(apiResult);
        if(apiResults!=null && apiResults.size()==3){
            for(APIResult result: apiResults ){
                if((!result.getApiCode().trim().equals("0")&& result.getApiName().equals(API_INFO))||
                        (!result.getApiCode().trim().equals("1")&& (result.getApiName().equals(API_IMAGE_2)||result.getApiName().equals(API_IMAGE_1)))){
                    getMvpView().hideLoading();
                    getMvpView().onError(R.string.error_connect_internet);
                    return;
                }
            }
            getMvpView().showMessage(R.string.save_data_success);
            getMvpView().openMain();
            getMvpView().hideLoading();
        }
    }
    public static class APIResult{
        String apiName;
        String apiCode;

        public APIResult(String apiName, String apiCode) {
            this.apiName = apiName;
            this.apiCode = apiCode;
        }

        public String getApiName() {
            return apiName;
        }

        public void setApiName(String apiName) {
            this.apiName = apiName;
        }

        public String getApiCode() {
            return apiCode;
        }

        public void setApiCode(String apiCode) {
            this.apiCode = apiCode;
        }
    }
}
