package pk.wei.com.newpractice.thirdParty.RxJava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import pk.wei.com.newpractice.R;

public class RxJavaDemoActivity extends AppCompatActivity {

    @BindView(R.id.bt_test)
    Button btTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo);
        ButterKnife.bind(this);

        observable.subscribe(observer);
    }


    Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            //执行一些其他操作
            //.............
            //执行完毕，触发回调，通知观察者
            emitter.onNext("我来发射数据");
        }
    });

    Observer<String> observer = new Observer<String>() {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        //观察者接收到通知,进行相关操作
        public void onNext(String aLong) {
            System.out.println("我接收到数据了");
            Toast.makeText(RxJavaDemoActivity.this, "我接收到数据了 " + aLong, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    @OnClick(R.id.bt_test)
    public void onViewClicked() {

        ObservableOnSubscribe<String> observableOnSubscribe = new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("one");
                emitter.onComplete();
            }
        };


        Observable<String> observable = Observable.create(observableOnSubscribe);
        observable.subscribe(observer);

        Disposable disposable = Observable.just("").subscribe((s)->{});
        disposable.dispose();
    }
}
