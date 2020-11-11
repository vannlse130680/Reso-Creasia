package com.creasia.ui.task.photo;

import com.androidnetworking.error.ANError;
import com.creasia.R;
import com.creasia.data.DataManager;
import com.creasia.data.network.model.CustomerImageRequest;
import com.creasia.data.network.model.CustomerImageResponse;
import com.creasia.ui.base.BasePresenter;
import com.creasia.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PhotoPresenter <V extends PhotoMvpView> extends BasePresenter<V>
        implements PhotoMvpPresenter<V> {

    @Inject
    public PhotoPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    private boolean hideShowLoadImage1 =false;
    private boolean hideShowLoadImage2 =false;
    @Override
    public void onSave(CustomerImageRequest request1, CustomerImageRequest request2) {

    }
}
