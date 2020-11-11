

package com.creasia.data;


import android.content.Context;

import com.creasia.data.db.model.Task;
import com.creasia.data.network.model.CustomerImageResponse;
import com.creasia.data.network.model.AddTaskRequest;
import com.creasia.data.network.model.AddTaskResponse;
import com.creasia.data.db.DbHelper;
import com.creasia.data.db.model.User;
import com.creasia.data.network.ApiHeader;
import com.creasia.data.network.ApiHelper;
import com.creasia.data.network.model.LoginResponse;
import com.creasia.data.network.model.LogoutResponse;
import com.creasia.data.network.model.OpenSourceResponse;
import com.creasia.data.network.model.ResponseArray;
import com.creasia.data.network.model.ResponseObject;
import com.creasia.data.network.model.TaskResponse;
import com.creasia.data.prefs.PreferencesHelper;
import com.creasia.di.ApplicationContext;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public boolean getRememberMe() {
        return mPreferencesHelper.getRememberMe();
    }

    @Override
    public void setRememberMe(boolean checked) {
        mPreferencesHelper.setRememberMe(checked);
    }

    @Override
    public String getCompanyId() {
        return mPreferencesHelper.getCompanyId();
    }

    @Override
    public void setCompanyId(String companyId) {
        mPreferencesHelper.setCompanyId(companyId);
    }

    @Override
    public String getProjectId() {
        return mPreferencesHelper.getProjectId();
    }

    @Override
    public void setProjectId(String projectId) {
        mPreferencesHelper.setProjectId(projectId);
    }

    @Override
    public String getUserFullName() {
        return mPreferencesHelper.getUserFullName();
    }

    @Override
    public void setUserFullName(String fullName) {
        mPreferencesHelper.setUserFullName(fullName);
    }

    @Override
    public String getCompanyUserName() {
        return mPreferencesHelper.getCompanyUserName();
    }

    @Override
    public void setCompanyUserName(String companyUserName) {
        mPreferencesHelper.setCompanyUserName(companyUserName);
    }

    @Override
    public Observable<Long> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return mDbHelper.getAllUsers();
    }

    @Override
    public Observable<Long> insertTask(Task task) {
        return mDbHelper.insertTask(task);
    }

    @Override
    public Observable<Boolean> deleteTask(Task task) {
        return mDbHelper.deleteTask(task);
    }

    @Override
    public Observable<Boolean> updateTask(Task task) {
        return mDbHelper.updateTask(task);
    }

    @Override
    public Observable<List<Task>> getAllTask() {
        return mDbHelper.getAllTask();
    }

    @Override
    public Observable<ResponseObject<LoginResponse>> doLoginApiCall(RequestBody userName, RequestBody pass) {
        return mApiHelper.doLoginApiCall(userName, pass);
    }

    @Override
    public Observable<LogoutResponse> doLogoutApiCall() {
        return mApiHelper.doLogoutApiCall();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public void updateApiHeader(String userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode,
            String userName,
            String companyId,
            String projectId,
            String userFullName,
            String companyUserName) {

        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCompanyId(companyId);
        updateApiHeader(userId, accessToken);
        setProjectId(projectId);
        setUserFullName(userFullName);
        setCompanyUserName(companyUserName);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public Observable<ResponseArray<TaskResponse>> getTaskApiCall(String userId, String companyId, String page) {
        return mApiHelper.getTaskApiCall(userId,companyId,page);
    }

    @Override
    public Observable<ResponseObject> addImageApiCall(RequestBody longitude, RequestBody latitude, MultipartBody.Part uploadFile, RequestBody customerImg1, RequestBody customerImg2, RequestBody routePlanId, RequestBody companyId, RequestBody userId, RequestBody projectId, RequestBody companyUserName, RequestBody userFullName, RequestBody customerId, RequestBody storeType, RequestBody houseNumber, RequestBody streetName, RequestBody wardName, RequestBody districtName, RequestBody cityName) {
        return mApiHelper.addImageApiCall(longitude, latitude, uploadFile, customerImg1, customerImg2, routePlanId, companyId, userId, projectId, companyUserName, userFullName, customerId, storeType, houseNumber, streetName, wardName, districtName, cityName);
    }

    @Override
    public Observable<AddTaskResponse> addCustomerApiCall(RequestBody companyId, RequestBody userId, RequestBody wardName, RequestBody cityName, RequestBody districtName, RequestBody customerName, RequestBody streetName, RequestBody posterCheckInLat, RequestBody posterCheckIng, RequestBody routePlantId, RequestBody projectId, RequestBody storeType, RequestBody houseNumber, RequestBody customerId, RequestBody customerStatus) {
        return mApiHelper.addCustomerApiCall(companyId, userId, wardName, cityName, districtName, customerName, streetName, posterCheckInLat, posterCheckIng, routePlantId, projectId, storeType, houseNumber, customerId, customerStatus);
    }

    @Override
    public Observable<AddTaskResponse> createNewKpiCustomer(RequestBody userId, RequestBody companyId) {
        return mApiHelper.createNewKpiCustomer(userId, companyId);
    }


    @Override
    public Observable<OpenSourceResponse> getOpenSourceApiCall() {
        return mApiHelper.getOpenSourceApiCall();
    }

}
