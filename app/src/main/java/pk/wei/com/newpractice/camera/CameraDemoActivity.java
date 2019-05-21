package pk.wei.com.newpractice.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import pk.wei.com.newpractice.R;

public class CameraDemoActivity extends AppCompatActivity {

    private int REQUEST_CODE = 1001;
    private static final String TAG = "CameraDemoActivity";
    private Camera mCamera;
    private CameraPreview mPreview;
    private FrameLayout mCameraLayout;
    private Button mTakePictureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_demo);

        if (!checkCameraHardware(this)) {
            Toast.makeText(CameraDemoActivity.this, "相机不支持", Toast.LENGTH_SHORT)
                    .show();
        } else {
            mCamera = getCameraInstance();
            mPreview = new CameraPreview(CameraDemoActivity.this, mCamera);
            mCameraLayout = (FrameLayout) findViewById(R.id.camera_preview);
            mCameraLayout.addView(mPreview);
        }

        mTakePictureBtn = (Button) findViewById(R.id.button_capture);
        mTakePictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCamera.takePicture(null, null, mPictureCallback);
            }
        });

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

}
