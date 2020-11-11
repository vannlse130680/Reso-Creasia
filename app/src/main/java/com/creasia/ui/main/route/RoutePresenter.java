package com.creasia.ui.main.route;

import com.androidnetworking.error.ANError;
import com.creasia.data.DataManager;
import com.creasia.data.network.model.BlogResponse;
import com.creasia.ui.base.BasePresenter;
import com.creasia.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class RoutePresenter<V extends RouteMvpView> extends BasePresenter<V>
        implements RouteMvpPresenter<V> {

    @Inject
    public RoutePresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
//        getCompositeDisposable().add(getDataManager()
//                .getBlogApiCall()
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(new Consumer<BlogResponse>() {
//                    @Override
//                    public void accept(@NonNull BlogResponse blogResponse)
//                            throws Exception {
//                        if (blogResponse != null && blogResponse.getData() != null) {
//                            getMvpView().updateBlog(blogResponse.getData());
//                        }
//                        getMvpView().hideLoading();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable)
//                            throws Exception {
//                        if (!isViewAttached()) {
//                            return;
//                        }
//
//                        getMvpView().hideLoading();
//
//                        // handle the error here
//                        if (throwable instanceof ANError) {
//                            ANError anError = (ANError) throwable;
//                            handleApiError(anError);
//                        }
//                    }
//                }));
    }
}
