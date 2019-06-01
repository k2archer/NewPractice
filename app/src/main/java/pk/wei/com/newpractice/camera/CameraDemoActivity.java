package pk.wei.com.newpractice.camera;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pk.wei.com.newpractice.R;

public class CameraDemoActivity extends AppCompatActivity {

    @BindView(R.id.bt_take_picture)
    Button btTakePicture;
    @BindView(R.id.camera_preview)
    FrameLayout cameraPreview;
    @BindView(R.id.button_capture)
    Button buttonCapture;

    private int REQUEST_CODE = 1001;
    private int PHOTO_REQUEST_CAMERA = 1002;
    private static final String TAG = "CameraDemoActivity";
    private Camera mCamera;
    private CameraPreview mPreview;
    private FrameLayout mCameraLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_demo);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.bt_take_picture)
    public void onTakePictureClicked(View view) {
        camera();

//        if (!checkCameraHardware(this)) {
//            Toast.makeText(CameraDemoActivity.this, "相机不支持", Toast.LENGTH_SHORT)
//                    .show();
//        } else {
//            mCamera = getCameraInstance();
//            mPreview = new CameraPreview(CameraDemoActivity.this, mCamera);
//            mCameraLayout = (FrameLayout) findViewById(R.id.camera_preview);
//            mCameraLayout.addView(mPreview);
//        }

    }

    public void camera() {

        String PHOTO_FILE_NAME = "test_photo.jpg";
        File tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
        Uri uri = Uri.fromFile(tempFile); // 从文件中创建 uri

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        /**
         * 使用 MediaStore.EXTRA_OUTPUT 保存照片
         * 或者使用 onActivityResult() 中传入 Intent
         * 二者只能选其一
         **/
//        i.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(i, PHOTO_REQUEST_CAMERA); // 携带请求码
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PHOTO_REQUEST_CAMERA) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            ImageView img = (ImageView) findViewById(R.id.tv_image);
            img.setImageBitmap(bitmap);
        }

    }

    // 拍照回调
    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (pictureDir == null) {
                Log.d(TAG,
                        "Error creating media file, check storage permissions!");
                return;
            }

            try {
//                String pictureName = new DateFormat().format("yyyyMMddHHmmss", new Date()).toString() + ".png";
                String pictureName = "test.png";
                FileOutputStream fos = new FileOutputStream(pictureDir
                        + File.separator + pictureName);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }
        }
    };

    // 判断相机是否支持
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    // 获取相机
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    @OnClick(R.id.button_capture)
    public void onCaptureClicked() {
        mCamera.takePicture(null, null, mPictureCallback);
    }
}
