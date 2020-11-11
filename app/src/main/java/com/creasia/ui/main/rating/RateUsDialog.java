

package com.creasia.ui.main.rating;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.creasia.R;
import com.creasia.di.component.ActivityComponent;
import com.creasia.ui.base.BaseDialog;
import com.creasia.utils.AppUtils;

import javax.inject.Inject;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class RateUsDialog extends BaseDialog implements RatingDialogMvpView {

    private static final String TAG = "RateUsDialog";

    @Inject
    RatingDialogMvpPresenter<RatingDialogMvpView> mPresenter;

    @BindView(R.id.rating_bar_feedback)
    RatingBar mRatingBar;

    @BindView(R.id.et_message)
    EditText mMessage;

    @BindView(R.id.view_rating_message)
    View mRatingMessageView;

    @BindView(R.id.view_play_store_rating)
    View mPlayStoreRatingView;

    @BindView(R.id.btn_submit)
    Button mSubmitButton;


    public static RateUsDialog newInstance() {
        RateUsDialog fragment = new RateUsDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_rate_us, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {

            component.inject(this);

            setUnBinder(ButterKnife.bind(this, view));

            mPresenter.onAttach(this);
        }

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }


    @Override
    public void openPlayStoreForRating() {
        AppUtils.openPlayStoreForApp(getContext());
    }

    @Override
    public void showPlayStoreRatingView() {
        mPlayStoreRatingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRatingMessageView() {
        mRatingMessageView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setUp(View view) {

        mRatingMessageView.setVisibility(View.GONE);
        mPlayStoreRatingView.setVisibility(View.GONE);

        LayerDrawable stars = (LayerDrawable) mRatingBar.getProgressDrawable();
        stars.getDrawable(2)
                .setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0)
                .setColorFilter(ContextCompat.getColor(getContext(), R.color.shadow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1)
                .setColorFilter(ContextCompat.getColor(getContext(), R.color.shadow), PorterDuff.Mode.SRC_ATOP);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onRatingSubmitted(mRatingBar.getRating(), mMessage.getText().toString());
            }
        });

    }

    @OnClick(R.id.btn_later)
    void onLaterClick() {
        mPresenter.onLaterClicked();
    }

    @OnClick(R.id.btn_rate_on_play_store)
    void onPlayStoreRateClick() {
        mPresenter.onPlayStoreRatingClicked();
    }

    @Override
    public void disableRatingStars() {
        mRatingBar.setIsIndicator(true);
    }

    @Override
    public void hideSubmitButton() {
        mSubmitButton.setVisibility(View.GONE);
    }

    @Override
    public void dismissDialog() {
        super.dismissDialog(TAG);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}