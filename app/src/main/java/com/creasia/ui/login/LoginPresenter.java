package com.creasia.ui.login;

import com.androidnetworking.error.ANError;
import com.creasia.R;
import com.creasia.data.DataManager;
import com.creasia.data.network.model.LoginRequest;
import com.creasia.data.network.model.LoginResponse;
import com.creasia.data.network.model.ResponseObject;
import com.creasia.ui.base.BasePresenter;
import com.creasia.utils.CommonUtils;
import com.creasia.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onServerLoginClick(String userName, String password) {
        //validate email and password
        if (userName == null || userName.isEmpty()) {
            getMvpView().onError(R.string.empty_user_name);
            return;
        }

        if (password == null || password.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }
        getMvpView().showLoading();
        getMvpView().hideKeyboard();
        getCompositeDisposable().add(getDataManager()
                .doLoginApiCall(RequestBody.create(MediaType.parse("text/plain"), userName), RequestBody.create(MediaType.parse("text/plain"), password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ResponseObject<LoginResponse>>() {
                    @Override
                    public void accept(ResponseObject<LoginResponse> loginResponse) throws Exception {
                        if (loginResponse.getResponseCode().equals("0")) {
                            getDataManager().updateUserInfo(
                                    loginResponse.getSessionKey(),
                                    loginResponse.getResponseData().getUserId(),
                                    DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                    userName,
                                    loginResponse.getResponseData().getCompanyId(),
                                    loginResponse.getResponseData().getProjectId(),
                                    loginResponse.getResponseData().getUserFullName(),
                                    loginResponse.getResponseData().getCompanyUserName());

                            if (!isViewAttached()) {
                                return;
                            }

                            getMvpView().hideLoading();
                            getMvpView().openMainActivity();

                        }else{
                            getMvpView().hideLoading();
                            getMvpView().onError(loginResponse.getResponseMessage());
                        }
                    }
                },new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().hideLoading();
                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
}
