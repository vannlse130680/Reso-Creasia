

package com.creasia.data.prefs;

import com.creasia.data.DataManager;



public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getCurrentUserId();

    void setCurrentUserId(String userId);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    String getAccessToken();

    void setAccessToken(String accessToken);

    boolean getRememberMe();

    void setRememberMe(boolean checked);

    String getCompanyId();

    void setCompanyId(String companyId);

    String getProjectId();

    void setProjectId(String projectId);

    String getUserFullName();

    void setUserFullName(String fullName);

    String getCompanyUserName();

    void setCompanyUserName(String companyUserName);

}
