package com.creasia.data;


import com.creasia.data.db.DbHelper;
import com.creasia.data.network.ApiHelper;
import com.creasia.data.prefs.PreferencesHelper;

import io.reactivex.Observable;

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void updateApiHeader(String userId, String accessToken);

    void setUserAsLoggedOut();

    void updateUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode,
            String userName,
            String companyId,
            String projectId,
            String userFullName,
            String companyUserName);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
