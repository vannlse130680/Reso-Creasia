package com.creasia.ui.main.routeMonth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creasia.R;
import com.creasia.data.network.model.OpenSourceResponse;
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


public class RouteMonthFragment extends BaseFragment implements
        RouteMonthMvpView, RouteMonthAdapter.Callback {

    private static final String TAG = "OpenSourceFragment";

    @Inject
    RouteMonthMvpPresenter<RouteMonthMvpView> mPresenter;

    @Inject
    RouteMonthAdapter mRouteMonthAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.repo_recycler_view)
    RecyclerView mRecyclerView;

    public static RouteMonthFragment newInstance() {
        Bundle args = new Bundle();
        RouteMonthFragment fragment = new RouteMonthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route_month, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mRouteMonthAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRouteMonthAdapter);

        mPresenter.onViewPrepared();
    }

    @Override
    public void onRepoEmptyViewRetryClick() {

    }

    @Override
    public void updateRepo(List<OpenSourceResponse.Repo> repoList) {
        mRouteMonthAdapter.addItems(repoList);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
