package com.creasia.ui.task;

import com.creasia.data.network.model.AddTaskRequest;
import com.creasia.data.network.model.CustomerImageRequest;
import com.creasia.data.db.model.Task;
import com.creasia.di.PerActivity;
import com.creasia.ui.base.MvpPresenter;

@PerActivity
public interface TaskMvpPresenter<V extends TaskMvpView> extends MvpPresenter<V> {
    public void onSave(CustomerImageRequest request1, CustomerImageRequest request2, AddTaskRequest task);
    public void onSaveOffline(CustomerImageRequest request1, CustomerImageRequest request2, AddTaskRequest task);
}
