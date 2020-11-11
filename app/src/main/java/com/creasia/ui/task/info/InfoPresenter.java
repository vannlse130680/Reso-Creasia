package com.creasia.ui.task.info;

import com.creasia.data.DataManager;
import com.creasia.ui.base.BasePresenter;
import com.creasia.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class InfoPresenter <V extends InfoMvpView> extends BasePresenter<V>
        implements InfoMvpPresenter<V> {

    @Inject
    public InfoPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
