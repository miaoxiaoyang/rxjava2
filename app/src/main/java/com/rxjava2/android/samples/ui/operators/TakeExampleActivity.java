package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 *
 */
public class TakeExampleActivity extends AppCompatActivity {

    private static final String TAG = TakeExampleActivity.class.getSimpleName();
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

    /* Using take operator, it only emits
    * required number of values. here only 3 out of 5
    * 操作符 take的运用
    * take(3);当数据运行到 3 时候，停止继续（注意参数是long，长度就是数据集合的长短，可以是显示几个数据，不是数据本身）
    * 应用场景：当数据流开始的时候，想在哪里停止时，就使用 take()操作符
    */
    private void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .take(2)//注意这时是 数据为 2 长度的时候，截止后面数据跟进
                .subscribe(getObserver());
    }
    //发射者(被观察者)
    private Observable<Integer> getObservable(){
        return Observable.just(7,88,35,57,3573,354);
    }
    //观察者（接口回调出来的四个方法）
    private Observer<Integer> getObserver(){
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("onSubscribe",d.toString());
            }

            @Override
            public void onNext(Integer value) {
                Log.i("onNext",value.toString());
                textView.append("onNext " +value.toString());
                textView.append(AppConstant.LINE_SEPARATOR);//换行
            }

            @Override
            public void onError(Throwable e) {
                Log.i("onError",e.toString());
            }

            @Override
            public void onComplete() {
                Log.i("onComple","");
            }
        };
    }

//    private Observable<Integer> getObservable() {
//        return Observable.just(8, 9, 3, 4, 5,6,7);
//    }

//    private Observer<Integer> getObserver() {
//        return new Observer<Integer>() {//返回的四个接口回调
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, " onSubscribe : " + d.isDisposed());
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                textView.append(" onNext : value : " + value);
//                textView.append(AppConstant.LINE_SEPARATOR);
//                Log.d(TAG, " onNext value : " + value);
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