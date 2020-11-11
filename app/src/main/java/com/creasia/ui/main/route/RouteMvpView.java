package com.creasia.ui.main.route;

import com.creasia.data.network.model.BlogResponse;
import com.creasia.ui.base.MvpView;

import java.util.List;

public interface RouteMvpView extends MvpView {

    void updateBlog(List<BlogResponse.Blog> blogList);
}
