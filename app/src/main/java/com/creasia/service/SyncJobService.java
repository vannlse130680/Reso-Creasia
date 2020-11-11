package com.creasia.service;

import android.util.Log;
import com.creasia.MvpApp;
import com.creasia.data.db.model.Task;
import com.creasia.data.network.model.AddTaskResponse;
import com.creasia.data.network.model.ResponseObject;
import com.creasia.ui.task.TaskPresenter;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class SyncJobService  extends JobService {


    private static final String TAG = "SyncJobService";
    private static final String API_IMAGE_1 = "API_IMAGE_1";
    private static final String API_IMAGE_2 = "API_IMAGE_2";
    private static final String API_INFO = "API_INFO";
    private boolean jobCancelled = false;
    private ArrayList<TaskPresenter.APIResult> apiResults = new ArrayList<>();

    @Override
    public boolean onStartJob(JobParameters job) {
        Log.d(TAG, "Job started");
        doBackgroundWork(job);

        return false;
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ((MvpApp) getApplication()).getComponent().getDataManager().getAllTask()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.computation())
                        .subscribe(new Observer<List<Task>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, " getAllTask onSubscribe");
                            }

                            @Override
                            public void onNext(List<Task> tasks) {
                                Log.d(TAG, "getAllTask success");
                                for (Task task: tasks){
                                    if(task.getCa_routeplan_id()!=null && !task.getCa_routeplan_id().isEmpty()
                                    && task.getCa_kpi_customer_id()!=null && !task.getCa_kpi_customer_id().isEmpty()){
                                        onSave(task);
                                    }else {
                                        creteNewTask(task);
                                    }
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "getAllTask Error");
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "getAllTask Complete");
                            }
                        });

                Log.d(TAG, "Job finished");
                jobFinished(params, false);
            }
        }).start();
    }
    private void checkUpLoad(TaskPresenter.APIResult apiResult, Task task){
        apiResults.add(apiResult);
        if(apiResults!=null && apiResults.size()==3){
            for(TaskPresenter.APIResult result: apiResults ){
                if((!result.getApiCode().trim().equals("0")&& result.getApiName().equals(API_INFO))||
                        (!result.getApiCode().trim().equals("1")&& (result.getApiName().equals(API_IMAGE_2)||result.getApiName().equals(API_IMAGE_1)))){
                    return;
                }
            }
            deleteTask(task);
        }
    }
    public void onSave(Task task) {
        apiResults.clear();
        RequestBody longitude =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getCa_poster_check_in_lng()));
        RequestBody latitude =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getCa_poster_check_in_lat()));
        RequestBody companyUserName =RequestBody.create(MediaType.parse("text/plain"), task.getCa_company_user_name());
        RequestBody userFullName =RequestBody.create(MediaType.parse("text/plain"), task.getCa_user_full_name());
        RequestBody projectId =RequestBody.create(MediaType.parse("text/plain"), task.getCa_project_id());
        RequestBody userId =RequestBody.create(MediaType.parse("text/plain"), task.getCa_user_id());
        RequestBody companyId =RequestBody.create(MediaType.parse("text/plain"), task.getCa_company_id());
        RequestBody routePlanId =RequestBody.create(MediaType.parse("text/plain"), task.getCa_routeplan_id());
        RequestBody customerId =RequestBody.create(MediaType.parse("text/plain"), task.getCa_kpi_customer_id());
        //add info store
        RequestBody wardName =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getWard_name()));
        RequestBody cityName =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getCity_name()));
        RequestBody districtName =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getDistrict_name()));
        RequestBody streetName =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getStreet_name()));
        RequestBody customerName =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getCa_kpi_customer_name()));
        RequestBody storeType =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getStore_type_select()));
        RequestBody houseNumber =RequestBody.create(MediaType.parse("text/plain"), String.valueOf(task.getHouse_number()));
        RequestBody customerStatus =RequestBody.create(MediaType.parse("text/plain"), "1");
        //upload image 1
        //part body
        File imgFile1 = new  File(task.getUpload_file1());
        if(!imgFile1.exists())
        {
            return;
        }
        RequestBody uploadFile1 =RequestBody.create(MediaType.parse("multipart/form-data"),imgFile1);
        MultipartBody.Part uploadFileData1 = MultipartBody.Part.createFormData("upload_file", imgFile1.getName(), uploadFile1);
        RequestBody file1CustomerImage1 =RequestBody.create(MediaType.parse("text/plain"),"1");
        RequestBody file1CustomerImage2 =RequestBody.create(MediaType.parse("text/plain"), "0");
        //upload image 2
        //part body
        File imgFile2 = new  File(task.getUpload_file2());
        if(!imgFile2.exists())
        {
            return;
        }
        RequestBody uploadFile2 =RequestBody.create(MediaType.parse("multipart/form-data"),imgFile2);
        MultipartBody.Part uploadFileData2 = MultipartBody.Part.createFormData("upload_file", imgFile2.getName(), uploadFile2);
        RequestBody file2CustomerImage1 =RequestBody.create(MediaType.parse("text/plain"),"0");
        RequestBody file2CustomerImage2 =RequestBody.create(MediaType.parse("text/plain"), "1");

        ((MvpApp) getApplication()).getComponent().getDataManager().addImageApiCall(longitude,latitude,uploadFileData1,file1CustomerImage1,file1CustomerImage2,routePlanId,companyId,userId,projectId,companyUserName,userFullName, customerId,storeType,houseNumber,streetName,wardName,districtName,cityName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(new Observer<ResponseObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseObject responseObject) {
                        TaskPresenter.APIResult apiResult= new TaskPresenter.APIResult(API_IMAGE_1,responseObject.getResponseCode());
                        checkUpLoad(apiResult,task);
                    }

                    @Override
                    public void onError(Throwable e) {
                        TaskPresenter.APIResult apiResult= new TaskPresenter.APIResult(API_IMAGE_1,"-1");
                        checkUpLoad(apiResult,task);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        ((MvpApp) getApplication()).getComponent().getDataManager().addImageApiCall(longitude,latitude,uploadFileData2,file2CustomerImage1,file2CustomerImage2,routePlanId,companyId,userId,projectId,companyUserName,userFullName,customerId,storeType,houseNumber,streetName,wardName,districtName,cityName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(new Observer<ResponseObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseObject responseObject) {
                        TaskPresenter.APIResult apiResult= new TaskPresenter.APIResult(API_IMAGE_2,responseObject.getResponseCode());
                        checkUpLoad(apiResult,task);
                    }

                    @Override
                    public void onError(Throwable e) {
                        TaskPresenter.APIResult apiResult= new TaskPresenter.APIResult(API_IMAGE_2,"-1");
                        checkUpLoad(apiResult,task);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        ((MvpApp) getApplication()).getComponent().getDataManager().addCustomerApiCall(companyId, userId, wardName,cityName,districtName,customerName,streetName,latitude,longitude,routePlanId,projectId,storeType,houseNumber,customerId,customerStatus)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(new Observer<AddTaskResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddTaskResponse response) {
                        TaskPresenter.APIResult apiResult= new TaskPresenter.APIResult(API_INFO,response.getCode());
                        checkUpLoad(apiResult,task);
                    }

                    @Override
                    public void onError(Throwable e) {
                        TaskPresenter.APIResult apiResult= new TaskPresenter.APIResult(API_INFO,"-1");
                        checkUpLoad(apiResult,task);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
    private void creteNewTask(Task task){
        RequestBody userId =RequestBody.create(MediaType.parse("text/plain"), task.getCa_user_id());
        RequestBody companyId =RequestBody.create(MediaType.parse("text/plain"), task.getCa_company_id());
        ((MvpApp) getApplication()).getComponent().getDataManager()
                .createNewKpiCustomer(userId,companyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(new Observer<AddTaskResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddTaskResponse response) {
                        if (response != null && response.getCode() != null && response.getCode().trim().equals("1")) {
                            task.setCa_routeplan_id(response.getRoutePlanId());
                            task.setCa_kpi_customer_id(response.getCustomerId());
                            updateTask(task);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void updateTask(Task task){
        ((MvpApp) getApplication()).getComponent().getDataManager().updateTask(task)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .       subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        Log.d(TAG, "Update task success");
                        onSave(task);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Update task fails");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void deleteTask(Task task){
        ((MvpApp) getApplication()).getComponent().getDataManager().deleteTask(task)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        Log.d(TAG, "Delete task success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Delete task fails");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return false;
    }

}
