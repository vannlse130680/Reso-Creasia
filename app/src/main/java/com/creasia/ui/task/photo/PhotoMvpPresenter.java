package com.creasia.ui.task.photo;

import com.creasia.data.network.model.CustomerImageRequest;
import com.creasia.ui.base.MvpPresenter;

public interface PhotoMvpPresenter <V extends PhotoMvpView> extends MvpPresenter<V> {
    public void onSave(CustomerImageRequest request1, CustomerImageRequest request2);
}
