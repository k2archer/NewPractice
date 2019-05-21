package pk.wei.com.newpractice.fragment.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pk.wei.com.newpractice.R;


public class FragmentViewOne extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentdemo_one, container, false);
        return view;
    }

    /*接口*/
    public interface CallBack{
        /*定义一个获取信息的方法*/
        public void getResult(String result);
    }

    /*接口回调*/
    public void getData(CallBack callBack){
        /*获取文本框的信息,当然你也可以传其他类型的参数,看需求咯*/
//        String msg = editText.getText().toString();
        Log.d("", "getResult: " + "-->>" );

        String msg = "callBack";
        callBack.getResult(msg);


    }
}
