

package com.creasia.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.creasia.data.DataManager;
import com.creasia.di.ApplicationContext;
import com.creasia.di.PreferenceInfo;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL
            = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_REMEMBER_ME = "PREF_KEY_REMEMBER_ME";
    private static final String PREF_KEY_COMPANY_ID = "PREF_KEY_COMPANY_ID";
    private static final String PREF_KEY_USER_FULL_NAME = "PREF_KEY_USER_FULL_NAME";
    private static final String PREF_KEY_COMPANY_USER_NAME = "PREF_KEY_COMPANY_USER_NAME";
    private static final String PREF_KEY_PROJECT_ID = "PREF_KEY_PROJECT_ID";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getCurrentUserId() {
        String userId = mPrefs.getString(PREF_KEY_CURRENT_USER_ID, "");
        return userId;
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID, userId).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserEmail() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public boolean getRememberMe() {
        return mPrefs.getBoolean(PREF_KEY_REMEMBER_ME, false);
    }

    @Override
    public void setRememberMe(boolean checked) {
        mPrefs.edit().putBoolean(PREF_KEY_REMEMBER_ME, checked).apply();
    }

    @Override
    public String getCompanyId() {
        return mPrefs.getString(PREF_KEY_COMPANY_ID, null);
    }

    @Override
    public void setCompanyId(String companyId) {
        mPrefs.edit().putString(PREF_KEY_COMPANY_ID, companyId).apply();
    }

    @Override
    public String getProjectId() {
        return mPrefs.getString(PREF_KEY_PROJECT_ID, null);
    }

    @Override
    public void setProjectId(String projectId) {
        mPrefs.edit().putString(PREF_KEY_PROJECT_ID, projectId).apply();
    }

    @Override
    public String getUserFullName() {
        return mPrefs.getString(PREF_KEY_USER_FULL_NAME, null);
    }

    @Override
    public void setUserFullName(String fullName) {
        mPrefs.edit().putString(PREF_KEY_USER_FULL_NAME, fullName).apply();
    }

    @Override
    public String getCompanyUserName() {
        return mPrefs.getString(PREF_KEY_COMPANY_USER_NAME, null);
    }

    @Override
    public void setCompanyUserName(String companyUserName) {
        mPrefs.edit().putString(PREF_KEY_COMPANY_USER_NAME, companyUserName).apply();
    }


}
