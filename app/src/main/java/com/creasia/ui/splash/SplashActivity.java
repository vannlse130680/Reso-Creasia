package com.creasia.ui.splash;

import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import com.creasia.BuildConfig;
import com.creasia.service.SyncJobService;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.github.buchandersenn.android_permission_manager.PermissionManager;
import com.creasia.R;
import com.creasia.ui.base.BaseActivity;
import com.creasia.ui.login.LoginActivity;
import com.creasia.ui.main.MainActivity;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashMvpView {
    private static String TAG ="SplashActivity";
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }
    private final PermissionManager permissionManager = PermissionManager.create(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        scheduleJob();
        mPresenter.onAttach(SplashActivity.this);
        mPresenter.checkLogin();
    }



    /**
     * Making the screen wait so that the  branding can be shown
     */
    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void startSyncService() {
//        SyncService.start(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
    public void scheduleJob() {
//        ComponentName componentName = new ComponentName(this, SyncJobService.class);
//        JobInfo info;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            info = new JobInfo.Builder(BuildConfig.ID_JOB, componentName)
//                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                    .setPersisted(true)
//                    .setMinimumLatency(BuildConfig.REFRESH_INTERVAL)
//                    .setOverrideDeadline(BuildConfig.OVER_DEADLINE)
//                    .setRequiresCharging(true)
//                    .build();
//        } else {
//            info = new JobInfo.Builder(BuildConfig.ID_JOB, componentName)
//                    .setPeriodic(BuildConfig.REFRESH_INTERVAL)
//                    .setPersisted(true)
//                    .setOverrideDeadline(BuildConfig.OVER_DEADLINE)
//                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                    .setRequiresCharging(true)
//                    .build();
//        }
//        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        int resultCode = scheduler.schedule(info);
//        if (resultCode == JobScheduler.RESULT_SUCCESS) {
//            Log.d(TAG, "Job scheduled");
//        } else {
//            Log.d(TAG, "Job scheduling failed");
//        }

        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                // the JobService that will be called
                .setService(SyncJobService.class)
                // uniquely identifies the job
                .setTag(BuildConfig.ID_JOB+"")
                // one-off job
                .setRecurring(true)
                // don't persist past a device reboot
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                // start between 0 and 60 seconds from now
                .setTrigger(Trigger.executionWindow(0, BuildConfig.OVER_DEADLINE))
                // don't overwrite an existing job with the same tag
                .setReplaceCurrent(false)
                // retry with exponential backoff
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                // constraints that need to be satisfied for the job to run
                .setConstraints(
                        // only run on an unmetered network
                        Constraint.ON_ANY_NETWORK
                )
                .build();
        Log.d(TAG, "Job scheduled");
        dispatcher.mustSchedule(myJob);
    }
}
