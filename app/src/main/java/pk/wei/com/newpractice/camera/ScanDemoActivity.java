package pk.wei.com.newpractice.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class ScanDemoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera_demo);
//        Button button = findViewById(R.id.button_capture);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent resultIntent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
//                bundle.putString(CodeUtils.RESULT_STRING, "wwwwwwwwww");
//                resultIntent.putExtras(bundle);
//                ScanDemoActivity.this.setResult(RESULT_OK, resultIntent);
//                ScanDemoActivity.this.finish();
//            }
//        });

        super.onCreate(savedInstanceState);
        setContentView(com.uuzuche.lib_zxing.R.layout.camera);
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(com.uuzuche.lib_zxing.R.id.fl_zxing_container, captureFragment).commit();

//        captureFragment.setCameraInitCallBack(new CaptureFragment.CameraInitCallBack() {
//            @Override
//            public void callBack(Exception e) {
//                if (e == null) {
//
//                } else {
//                    Log.e("TAG", "callBack: ", e);
//                }
//            }
//        });

    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            ScanDemoActivity.this.setResult(RESULT_OK, resultIntent);
            ScanDemoActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            ScanDemoActivity.this.setResult(RESULT_OK, resultIntent);
            ScanDemoActivity.this.finish();
        }
    };
}