package com.creasia.ui.main.routeMonth;

import com.creasia.data.network.model.OpenSourceResponse;
import com.creasia.ui.base.MvpView;

import java.util.List;

public interface RouteMonthMvpView extends MvpView {

    void updateRepo(List<OpenSourceResponse.Repo> repoList);
}
