package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class TimerExampleActivity extends AppCompatActivity {

    private static final String TAG = TimerExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork();
            }
        });
    }

    /*
     * simple example using timer to do something after 2 second
     */
    private void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    /**
     * 操作符 timer ：定时任务（作用延时时间去执行任务）
     * @return
     * 使用场景：
     * 被观察者 Observable 在此确定 Observable中的类型，所有继承long，在返回来的观察者aobserver中也定义类型
     */
    private Observable<? extends Long> getObservable(){
        return Observable.timer(2,TimeUnit.SECONDS);
    }
    //观察者
    private Observer<Long> getObserver(){
        return new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("onSubscribe",d.isDisposed()+"");
            }

            @Override
            public void onNext(Long aLong) {
                Log.i("onNext",aLong.toString());
                textView.append(aLong.toString());
                textView.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("onError",e.toString());
            }

            @Override
            public void onComplete() {
                Log.i("onComplete","");
            }
        };
    }
//    private Observable<? extends Long> getObservable() {
//        return Observable.timer(2, TimeUnit.SECONDS);
//    }

//    private Observer<Long> getObserver() {
//        return new Observer<Long>() {
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, " onSubscribe : " + d.isDisposed());
//            }
//
//            @Override
//            public void onNext(Long value) {
//                textView.append(" onNext : value : " + value);
//                textView.append(AppConstant.LINE_SEPARATOR);
//                Log.d(TAG, " onNext : value : " + value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                textView.append(" onError : " + e.getMessage());
//                textView.append(AppConstant.LINE_SEPARATOR);
//                Log.d(TAG, " onError : " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                textView.append(" onComplete");
//                textView.append(AppConstant.LINE_SEPARATOR);
//                Log.d(TAG, " onComplete");
//            }
//        };
//    }


}