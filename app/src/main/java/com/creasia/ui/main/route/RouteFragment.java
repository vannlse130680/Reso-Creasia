package com.creasia.ui.main.route;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creasia.R;
import com.creasia.data.network.model.BlogResponse;
import com.creasia.di.component.ActivityComponent;
import com.creasia.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RouteFragment extends BaseFragment implements
        RouteMvpView, RouteAdapter.Callback {

    private static final String TAG = "BlogFragment";

    @Inject
    RouteMvpPresenter<RouteMvpView> mPresenter;

    @Inject
    RouteAdapter mRouteAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.blog_recycler_view)
    RecyclerView mRecyclerView;

    public static RouteFragment newInstance() {
        Bundle args = new Bundle();
        RouteFragment fragment = new RouteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mRouteAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRouteAdapter);

        mPresenter.onViewPrepared();
    }

    @Override
    public void onBlogEmptyViewRetryClick() {

    }

    @Override
    public void updateBlog(List<BlogResponse.Blog> blogList) {
        mRouteAdapter.addItems(blogList);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
