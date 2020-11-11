package com.creasia.ui.task;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.creasia.R;
import com.creasia.data.db.model.Task;
import com.creasia.data.network.model.AddTaskRequest;
import com.creasia.data.network.model.CustomerImageRequest;
import com.creasia.ui.base.BaseActivity;
import com.creasia.ui.main.MainActivity;
import com.creasia.ui.task.info.InfoFragment;
import com.creasia.ui.task.photo.PhotoFragment;
import com.github.buchandersenn.android_permission_manager.PermissionManager;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationAccuracy;
import io.nlopez.smartlocation.location.config.LocationParams;


public class TaskActivity extends BaseActivity implements TaskMvpView {
    public static final String PHOTO_PIC_1 = "_photo_pic_1.jpg";
    public static final String PHOTO_PIC_2 = "_photo_pic_2.jpg";
    private static String ROUTE_PLAN_ID="ROUTE_PLAN_ID";
    private static String CUSTOMER_ID="CUSTOMER_ID";
    public static final String IS_NETWORK = "IS_NETWORK";
    private Location location = null;
    private boolean isLoadLocation= false;
    private boolean isShowLoading= false;
    private String currentPhotoPath="";
    SmartLocation smartLocation;
    @Inject
    TaskMvpPresenter<TaskMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.edt_name_store)
    EditText mNameStore;

    @BindView(R.id.spn_store_type)
    Spinner mStoreType;

    @BindView(R.id.edt_house_number)
    EditText mHouseNumber;

    @BindView(R.id.edt_street_name)
    EditText mStreetName;

    @BindView(R.id.edt_ward)
    EditText mWard;

    @BindView(R.id.edt_district)
    EditText mDistrict;

    @BindView(R.id.edt_city)
    EditText mCity;

    @BindView(R.id.img_customer_1)
    ImageView imgViewCustomer1;

    @BindView(R.id.img_customer_2)
    ImageView imgViewCustomer2;
    private boolean checkPickImage = false;
    private File fileImage1;
    private File fileImage2;

    @BindView(R.id.activity_route_detail)
    View mLayout;

    private final PermissionManager permissionManager = PermissionManager.create(this);

    public static Intent getStartIntent(Context context, String routePlantId, String customerId, boolean isNetWork) {
        Intent intent = new Intent(context, TaskActivity.class);
        intent.putExtra(ROUTE_PLAN_ID,routePlantId);
        intent.putExtra(CUSTOMER_ID,customerId);
        intent.putExtra(IS_NETWORK,isNetWork);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(TaskActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        showLoading();
        isShowLoading=true;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        startLocationListener();
    }

    @Override
    public void showInfoFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view_route, InfoFragment.newInstance(), InfoFragment.TAG)
                .commit();
    }

    //binding onclick start
    @OnClick(R.id.ln_image_customer_1)
    void onButtonPhoto1Click() {
        checkPickImage = false;
        selectImage(this);
    }

    @OnClick(R.id.ln_image_customer_2)
    void onButtonPhoto2Click() {
        checkPickImage = true;
        selectImage(this);
    }

    @OnClick(R.id.btn_save_customer)
    void onBtnSaveClick() {
        if(location==null){
            this.onError(R.string.empty_location_error);
            return;
        }
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        CustomerImageRequest imageRequest1 = new CustomerImageRequest();
        imageRequest1.setUploadFile(fileImage1);
        imageRequest1.setLongitude(longitude);
        imageRequest1.setLatitude(latitude);
        CustomerImageRequest imageRequest2 = new CustomerImageRequest();
        imageRequest2.setUploadFile(fileImage2);
        imageRequest2.setLongitude(longitude);
        imageRequest2.setLatitude(latitude);
        //task request object
        AddTaskRequest addTaskRequest = new AddTaskRequest();
        addTaskRequest.setWardName(mWard.getText().toString());
        addTaskRequest.setCityName(mCity.getText().toString());
        addTaskRequest.setDistrictName(mDistrict.getText().toString());
        addTaskRequest.setCustomerName(mNameStore.getText().toString());
        addTaskRequest.setStreetName(mStreetName.getText().toString());
        addTaskRequest.setPosterCheckInLat( String.valueOf(latitude));
        addTaskRequest.setPosterCheckInLng( String.valueOf(longitude));
        addTaskRequest.setRoutePlanId(getIntent().getStringExtra(ROUTE_PLAN_ID));
        addTaskRequest.setStoreType((mStoreType.getSelectedItemPosition()+1)+"");
        addTaskRequest.setHouseNumber(mHouseNumber.getText().toString());
        addTaskRequest.setCustomerId(getIntent().getStringExtra(CUSTOMER_ID));
        //check internet
        locationStop();
        boolean isInterNet= getIntent().getBooleanExtra(IS_NETWORK,false);
        if(isInterNet) {
            mPresenter.onSave(imageRequest1, imageRequest2, addTaskRequest);
        }else {
            mPresenter.onSaveOffline(imageRequest1, imageRequest2, addTaskRequest);
        }
        //getBaseActivity().onFragmentDetached(TAG);

    }
    //binding onclick end

    @Override
    public void showPhotoFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view_route, PhotoFragment.newInstance(), PhotoFragment.TAG)
                .commit();
    }

    @Override
    public void openMain() {
        startActivity(MainActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(InfoFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(InfoFragment.TAG);
        }
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
        }
    }

    private void selectImage(Context context) {
        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile("creasia");
            } catch (IOException ex) {
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getBaseContext(),
                        "com.creasia.provider",
                        photoFile);
                currentPhotoPath= photoFile.getAbsolutePath();
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePicture, 0);
            }
        }
        /*final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePicture.resolveActivity(getPackageManager()) != null) {
                        // Create the File where the photo should go
                        File photoFile = null;
                        try {
                            photoFile = createImageFile("creasia");
                        } catch (IOException ex) {
                        }
                        // Continue only if the File was successfully created
                        if (photoFile != null) {
                            Uri photoURI = FileProvider.getUriForFile(getBaseContext(),
                                    "com.creasia.provider",
                                    photoFile);
                            currentPhotoPath= photoFile.getAbsolutePath();
                            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(takePicture, 0);
                        }
                    }

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode != this.RESULT_CANCELED) {
                switch (requestCode) {
                    case 0:
                        if (resultCode == this.RESULT_OK ) {
                            if (checkPickImage) {
                                imgViewCustomer2.setImageBitmap(BitmapFactory.decodeFile(currentPhotoPath));
                                fileImage2 =new File(currentPhotoPath);
                                currentPhotoPath="";
                            } else {
                                imgViewCustomer1.setImageBitmap(BitmapFactory.decodeFile(currentPhotoPath));
                                fileImage1 = new File(currentPhotoPath);
                                currentPhotoPath="";
                            }
                        }

                        break;
                    case 1:
                        if (resultCode == this.RESULT_OK && data != null) {
                            Uri selectedImage = data.getData();
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            if (selectedImage != null) {
                                Cursor cursor = this.getContentResolver().query(selectedImage,
                                        filePathColumn, null, null, null);
                                if (cursor != null) {
                                    cursor.moveToFirst();

                                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                    String picturePath = cursor.getString(columnIndex);
                                    Bitmap selectedImageGl = BitmapFactory.decodeFile(picturePath);
                                    if (checkPickImage) {
                                        imgViewCustomer2.setImageBitmap(selectedImageGl);
                                        fileImage2 = new File(picturePath);
                                    } else {
                                        imgViewCustomer1.setImageBitmap(selectedImageGl);
                                        fileImage1 = new File(picturePath);
                                    }
                                    cursor.close();
                                }
                            }

                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startLocationListener() {
        long mLocTrackingInterval = 100 ; // 5 sec
        float trackingDistance = 0;
        LocationAccuracy trackingAccuracy = LocationAccuracy.HIGH;
        LocationParams.Builder builder = new LocationParams.Builder()
                .setAccuracy(trackingAccuracy)
                .setDistance(trackingDistance)
                .setInterval(mLocTrackingInterval);

        smartLocation.with(this)
                .location()
                .continuous()
                .config(builder.build())
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        processLocation(location);
                    }
                });
    }

    private void processLocation(Location lc){
        if(lc==null){
            if(!isShowLoading){
                showLoading();
                isShowLoading=true;
            }
            showMessage(R.string.location_loading);
            isLoadLocation=false;

        }else if (!isLoadLocation && lc != null) {
            hideLoading();
            isShowLoading=false;
            isLoadLocation=true;
            showMessage(R.string.location_success);
            this.location = lc;
        }
    }
    private File createImageFile(String imageFileName) throws IOException {
        // Create an image file name
        File storageDir = this.getCacheDir();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }
    private void locationStop(){
        smartLocation.with(this).location().stop();
    }
    @Override
    protected void onDestroy() {
        locationStop();
        mPresenter.onDetach();
        super.onDestroy();
    }
}
