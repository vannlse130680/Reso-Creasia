

package com.creasia.ui.main.rating;

import com.creasia.ui.base.MvpPresenter;

/**
 * Created by janisharali on 22/03/17.
 */

public interface RatingDialogMvpPresenter<V extends RatingDialogMvpView> extends MvpPresenter<V> {

    void onRatingSubmitted(float rating, String message);

    void onCancelClicked();

    void onLaterClicked();

    void onPlayStoreRatingClicked();
}
