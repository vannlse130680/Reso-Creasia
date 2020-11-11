

package com.creasia.utils;
public final class AppConstants {
    public static final String CONTENT_TYPE = "application/json";
    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "creasia_mvp.db";
    public static final String PREF_NAME = "creasia_pref";

    public static final long NULL_INDEX = -1L;

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    //network response
    public static final int RESPONSE_SUCCESS = 200;
    public static final int RESPONSE_FAIL = 404;
    public static final int RESPONSE_SERVER_ERROR = 500;
    public static final int LBL_USER_IS_NOT_VERIFY =999;
    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
