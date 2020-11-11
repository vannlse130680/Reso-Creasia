package com.creasia.data.network;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.creasia.BuildConfig;

import okhttp3.OkHttpClient;
import timber.log.Timber;

public class ApiUtils {
    private ApiUtils() {}
    public static ApiHelper getAPIService() {

        return RetrofitClient.getClient(BuildConfig.BASE_URL).create(ApiHelper.class);
    }
}
