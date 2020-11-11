package com.creasia.ui.main;

import com.androidnetworking.error.ANError;
import com.creasia.R;
import com.creasia.data.DataManager;
import com.creasia.data.db.model.Task;
import com.creasia.data.network.model.AddTaskResponse;
import com.creasia.data.network.model.LogoutResponse;
import com.creasia.data.network.model.ResponseArray;
import com.creasia.data.network.model.TaskResponse;
import com.creasia.service.SyncJobService;
import com.creasia.ui.base.BasePresenter;
import com.creasia.utils.rx.SchedulerProvider;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = "MainPresenter";
    private int page_index = 1;

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onDrawerOptionAboutClick() {
        getMvpView().closeNavigationDrawer();
        getMvpView().showAboutFragment();
    }

    @Override
    public void onDrawerOptionLogoutClick() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().doLogoutApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LogoutResponse>() {
                    @Override
                    public void accept(LogoutResponse response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getDataManager().setUserAsLoggedOut();
                        getMvpView().hideLoading();
                        getMvpView().openLoginActivity();
                    }
                }, new Consumer<Throwable>() {
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

    @Override
    public void onViewInitialized() {

    }

    @Override
    public void onAddNewTask() {
        getMvpView().showLoading();
        RequestBody userId =RequestBody.create(MediaType.parse("text/plain"), getDataManager().getCurrentUserId());
        RequestBody companyId =RequestBody.create(MediaType.parse("text/plain"), getDataManager().getCompanyId());
        getCompositeDisposable().add(getDataManager()
                .createNewKpiCustomer(userId,companyId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<AddTaskResponse>() {
                    @Override
                    public void accept(AddTaskResponse response) throws Exception {
                        if (response != null && response.getCode() != null && response.getCode().trim().equals("1")) {
                            getMvpView().openNewTask(response.getRoutePlanId(),response.getCustomerId());
                        }else {
                            getMvpView().onError(response.getMessage());
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().hideLoading();
                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void onNavMenuCreated() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().updateAppVersion();

        final String currentUserName = getDataManager().getCurrentUserName();
        if (currentUserName != null && !currentUserName.isEmpty()) {
            getMvpView().updateUserName(currentUserName);
        }

        final String currentUserId = getDataManager().getCurrentUserId();
        if (currentUserId != null && !currentUserId.isEmpty()) {
            getMvpView().updateUserId(currentUserId);
        }

        final String profilePicUrl = getDataManager().getCurrentUserProfilePicUrl();
        if (profilePicUrl != null && !profilePicUrl.isEmpty()) {
            getMvpView().updateUserProfilePic(profilePicUrl);
        }
    }

    @Override
    public void onViewPrepared() {
        getCompositeDisposable().add(getDataManager()
                .getTaskApiCall(getDataManager().getCurrentUserId(),getDataManager().getCompanyId(),""+page_index)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ResponseArray<TaskResponse>>() {
                    @Override
                    public void accept(ResponseArray<TaskResponse> tasks) throws Exception {
                        if (tasks.getResponseCode() != null && tasks.getResponseCode().trim().equals("0")) {
                            getMvpView().updateTask( tasks.getResponseData());
                        }else {
                            getMvpView().updateTask(null);
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().updateTask( null);
                        getMvpView().hideLoading();
                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void rsPageIndex() {
        page_index=1;
    }

    @Override
    public void inPageIndex() {
        page_index++;
    }

    @Override
    public void onAsyncData() {
//        getMvpView().showLoading();
        SyncJobService syncJobService = new SyncJobService();
        syncJobService.onStartJob(null);
    }

    @Override
    public void onDrawerRateUsClick() {
        getMvpView().closeNavigationDrawer();
        getMvpView().showRateUsDialog();
    }

}
