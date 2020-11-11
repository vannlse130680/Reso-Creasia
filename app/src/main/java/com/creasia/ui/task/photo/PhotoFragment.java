package com.creasia.ui.task.photo;

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
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.creasia.R;
import com.creasia.data.network.model.CustomerImageRequest;
import com.creasia.di.component.ActivityComponent;
import com.creasia.ui.base.BaseFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoFragment extends BaseFragment implements PhotoMvpView {
    public static final String TAG = "PhotoFragment";
    public static final String PHOTO_PIC_1 = "_photo_pic_1.jpg";
    public static final String PHOTO_PIC_2 = "_photo_pic_2.jpg";
    @Inject
    PhotoMvpPresenter<PhotoMvpView> mPresenter;

    @BindView(R.id.img_1)
    ImageView imgView1;

    @BindView(R.id.img_2)
    ImageView imgView2;
    private boolean checkPickImage = false;
    private File fileImage1;
    private File fileImage2;

    public static PhotoFragment newInstance() {
        Bundle args = new Bundle();
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_photo, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        return view;
    }

    @Override
    protected void setUp(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @OnClick(R.id.nav_back_Photo_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @OnClick(R.id.btn_save_photo)
    void onBtnSaveClick() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.CAMERA},5);
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
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
        mPresenter.onSave(imageRequest1,imageRequest2);
        //getBaseActivity().onFragmentDetached(TAG);

    }

    @OnClick(R.id.ln_image_1)
    void onButtonPhoto1Click() {
        checkPickImage=false;
        selectImage(getContext());
    }

    @OnClick(R.id.ln_image_2)
    void onButtonPhoto2Click() {
        checkPickImage=true;
        selectImage(getContext());
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        try {
            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();
            if (resultCode != getActivity().RESULT_CANCELED) {
                switch (requestCode) {
                    case 0:
                        if (resultCode == getActivity().RESULT_OK && data != null) {
                            Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                            if(checkPickImage) {
                                imgView2.setImageBitmap(selectedImage);
                                fileImage2=bitmapToFile(selectedImage,ts+PHOTO_PIC_2);

                            }else {
                                imgView1.setImageBitmap(selectedImage);
                                fileImage1=bitmapToFile(selectedImage,ts+PHOTO_PIC_1);
                            }
                        }

                        break;
                    case 1:
                        if (resultCode == getActivity().RESULT_OK && data != null) {
                            Uri selectedImage = data.getData();
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            if (selectedImage != null) {
                                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                                        filePathColumn, null, null, null);
                                if (cursor != null) {
                                    cursor.moveToFirst();

                                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                    String picturePath = cursor.getString(columnIndex);
                                    Bitmap selectedImageGl = BitmapFactory.decodeFile(picturePath);
                                    if(checkPickImage) {
                                        imgView2.setImageBitmap(selectedImageGl);
                                        fileImage2=bitmapToFile(selectedImageGl,ts+PHOTO_PIC_2);
                                    }else {
                                        imgView1.setImageBitmap(selectedImageGl);
                                        fileImage1=bitmapToFile(selectedImageGl,ts+PHOTO_PIC_1);
                                    }
                                    cursor.close();
                                }
                            }

                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File bitmapToFile(Bitmap bitmap, String fileName) throws IOException {
        //create a file to write bitmap data
        File f = new File(getActivity().getCacheDir(), fileName);
        f.createNewFile();

        //Convert bitmap to byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , bos);
        byte[] bitmapped = bos.toByteArray();

        //write the bytes in file
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bitmapped);
        fos.flush();
        fos.close();
        return  f;
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
