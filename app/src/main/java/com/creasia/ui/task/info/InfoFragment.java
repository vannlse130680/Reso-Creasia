package com.creasia.ui.task.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creasia.R;
import com.creasia.di.component.ActivityComponent;
import com.creasia.ui.base.BaseFragment;

import javax.inject.Inject;

import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoFragment extends BaseFragment implements InfoMvpView {
    public static final String TAG = "InfoFragment";

    @Inject
    InfoMvpPresenter<InfoMvpView> mPresenter;

    public static InfoFragment newInstance() {
        Bundle args = new Bundle();
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        return view;
    }

    @Override
    protected void setUp(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @OnClick(R.id.nav_back__info_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @OnClick(R.id.btn_save)
    void onBtnSaveClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
