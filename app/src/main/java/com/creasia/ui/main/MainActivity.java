
package com.creasia.ui.main;

import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.creasia.data.db.model.Task;
import com.creasia.data.network.model.TaskResponse;
import com.creasia.service.SyncJobService;
import com.creasia.ui.task.TaskActivity;
import com.google.android.material.navigation.NavigationView;
import com.creasia.BuildConfig;
import com.creasia.R;
import com.creasia.ui.about.AboutFragment;
import com.creasia.ui.base.BaseActivity;
import com.creasia.ui.custom.RoundedImageView;
import com.creasia.ui.login.LoginActivity;
import com.creasia.ui.main.rating.RateUsDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.nlopez.smartlocation.SmartLocation;

public class MainActivity extends BaseActivity implements MainMvpView, TaskAdapter.Callback {

    private static String TAG ="MainActivity";
    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_view)
    DrawerLayout mDrawer;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.tv_app_version)
    TextView mAppVersionTextView;

    @BindView(R.id.task_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeTaskList)
    SwipeRefreshLayout mSwipeTaskList;

    @Inject
    TaskAdapter mTaskAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    private TextView mNameTextView;

    private TextView mUserIdTextView;

    private RoundedImageView mProfileImageView;

    private ActionBarDrawerToggle mDrawerToggle;

    private ArrayList<TaskResponse> mArrTask;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
        mTaskAdapter.setCallback(this);
        setUp();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(AboutFragment.TAG);
        }
    }

    @Override
    public void updateAppVersion() {
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        mAppVersionTextView.setText(version);
    }

    @Override
    public void updateUserName(String currentUserName) {
        mNameTextView.setText(currentUserName);
    }

    @Override
    public void updateUserId(String currentUserId) {
        mUserIdTextView.setText("Id: "+currentUserId);
    }

    @Override
    public void updateUserProfilePic(String currentUserProfilePicUrl) {
        //load profile pic url into ANImageView
    }
    @OnClick(R.id.fab_add_task)
    void onAddTaskClick(View v) {
        if(isNetworkConnected()) {
            mPresenter.onAddNewTask();
        }else{
            startActivity(TaskActivity.getStartIntent(this,null,null, false));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }
    }

    @Override
    public void showAboutFragment() {
        lockDrawer();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
    }

    @Override
    public void lockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void updateTask(List<TaskResponse> taskList) {
       // mTaskAdapter.addItems(taskList);

        if (mArrTask != null && mArrTask.size() > 0){
            if (mArrTask.get(mArrTask.size() - 1).isLoadMore()){
                mArrTask.remove(mArrTask.size() -1 );
            }
        }

        if (taskList != null && taskList.size() > 0){
            mArrTask.addAll(taskList);
        }else {
            mTaskAdapter.setMoreDataAvailable(false);
        }
        mSwipeTaskList.setRefreshing(false);
        mRecyclerView.setVisibility(View.VISIBLE);
        mTaskAdapter.notifyDataChanged();
    }

    @Override
    public void openNewTask(String routePlantId, String customerId) {
        startActivity(TaskActivity.getStartIntent(this,routePlantId,customerId, true));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void setUp() {
        //create task load more
        mArrTask = new ArrayList<>();
        mArrTask.add(new TaskResponse(true));
        mTaskAdapter = new TaskAdapter(mArrTask);
        mTaskAdapter.setCallback(this);
        mTaskAdapter.setLoadMoreListener(new TaskAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        //load more add row load more
                        if (mArrTask.size() > 0) {
                            if (!mArrTask.get(mArrTask.size() - 1).isLoadMore()) {
                                mArrTask.add(new TaskResponse(true));
                                mTaskAdapter.notifyItemInserted(mArrTask.size() - 1);
                                // increase page index (next page)
                                mPresenter.inPageIndex();
                                // load data
                                mPresenter.onViewPrepared();

                            }
                        }
                    }
                });
            }
        });

        setSupportActionBar(mToolbar);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mTaskAdapter);

        mPresenter.onViewPrepared();
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        mPresenter.onNavMenuCreated();
        mPresenter.onViewInitialized();
        // Refresh
        mSwipeTaskList.setColorSchemeColors(getResources().getColor(R.color.colorPrimary)); //set color
        mSwipeTaskList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.setVisibility(View.INVISIBLE);
                        mLayoutManager.removeAllViews();
                        mPresenter.rsPageIndex(); // set page index into 1 (first page)
                        mArrTask.clear(); // clear all data
                        mPresenter.onViewPrepared(); // load data
                        mTaskAdapter.setMoreDataAvailable(true);
                        mSwipeTaskList.setRefreshing(true);
                    }
                });
            }
        });
        //check permission
        // Check if the location services are enabled
        SmartLocation.with(this).location().state().locationServicesEnabled();

        // Check if any provider (network or gps) is enabled
        SmartLocation.with(this).location().state().isAnyProviderAvailable();

        // Check if GPS is available
        SmartLocation.with(this).location().state().isGpsAvailable();

        // Check if Network is available
        SmartLocation.with(this).location().state().isNetworkAvailable();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.CAMERA}, 5);
        }
    }
    void setupNavMenu() {
        View headerLayout = mNavigationView.getHeaderView(0);
        mProfileImageView = (RoundedImageView) headerLayout.findViewById(R.id.iv_profile_pic);
        mNameTextView = (TextView) headerLayout.findViewById(R.id.tv_name);
        mUserIdTextView = (TextView) headerLayout.findViewById(R.id.tv_user_id);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mDrawer.closeDrawer(GravityCompat.START);
                        switch (item.getItemId()) {
                            case R.id.nav_item_logout:
                                mPresenter.onDrawerOptionLogoutClick();
                                return true;
//                            case R.id.nav_item_async_data:
//                                mPresenter.onAsyncData();
//                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }


    @Override
    public void openLoginActivity() {
        cancelJob();
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void showRateUsDialog() {
        RateUsDialog.newInstance().show(getSupportFragmentManager());
    }

    @Override
    public void closeNavigationDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onAEmptyViewRetryClick() {
        mSwipeTaskList.setRefreshing(true);
        mTaskAdapter.setMoreDataAvailable(true);
        // check if there is any data available
        if (mArrTask.size() > 0) {
            // add the progress circle
            if (!mArrTask.get(mArrTask.size() - 1).isLoadMore()) {
                mArrTask.add(new TaskResponse(true));
                mTaskAdapter.notifyItemChanged(mArrTask.size() - 1);
            }
        }
        mPresenter.rsPageIndex();
        mPresenter.onViewPrepared();
    }

    public void cancelJob() {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(BuildConfig.ID_JOB);
        Log.d(TAG, "Job cancelled");
    }
}
