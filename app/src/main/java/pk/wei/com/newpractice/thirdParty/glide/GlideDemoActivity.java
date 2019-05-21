package pk.wei.com.newpractice.thirdParty.glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pk.wei.com.newpractice.R;

public class GlideDemoActivity extends AppCompatActivity {

    @BindView(R.id.iv_demo)
    ImageView imageView;
    @BindView(R.id.bt_load_image)
    Button btLoadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);

        ButterKnife.bind(this);

    }

    private void LoadImage() {
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        Glide.with(this).load(url).into(imageView);

        /*
         // 加载本地图片
         File file = new File(getExternalCacheDir() + "/image.jpg");
         Glide.with(this).load(file).into(imageView);

         // 加载应用资源
         int resource = R.drawable.image;
         Glide.with(this).load(resource).into(imageView);

         // 加载二进制流
         byte[] image = getImageBytes();
         Glide.with(this).load(image).into(imageView);

         // 加载Uri对象
         Uri imageUri = getImageUri();
         Glide.with(this).load(imageUri).into(imageView);
         **/
    }

    private void glideDemo() {
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        /** placeholder() 加载占位图
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.loading)
                .into(imageView);
        **/

        // diskCacheStrategy() Glide 的缓存功能
        /** DiskCacheStrategy.NONE 禁用掉Glide的缓存功能 **/
//         Glide.with(this)
//         .load(url)
//         .placeholder(R.drawable.loading)
//         .diskCacheStrategy(DiskCacheStrategy.NONE)
//         .into(imageView);
    }

    @OnClick({R.id.bt_load_image})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.bt_load_image:
                LoadImage();
                break;
        }
    }

}
