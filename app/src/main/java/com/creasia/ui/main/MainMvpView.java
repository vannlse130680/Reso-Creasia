

package com.creasia.ui.main;

import com.creasia.data.db.model.Task;
import com.creasia.data.network.model.TaskResponse;
import com.creasia.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void openLoginActivity();

    void showAboutFragment();

    void updateUserName(String currentUserName);

    void updateUserId(String currentUserId);

    void updateUserProfilePic(String currentUserProfilePicUrl);

    void updateAppVersion();

    void showRateUsDialog();

    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();

    void updateTask(List<TaskResponse> taskList);

    void openNewTask(String routePlantId, String customerId);
}
