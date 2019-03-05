package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by amitshekhar on 27/08/16.
 * Rxjava2中的单例模式
 */
public class SingleObserverExampleActivity extends AppCompatActivity {

    private static final String TAG = SingleObserverExampleActivity.class.getSimpleName();
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
     * 单例 single.just();
     *
     */
    private void doSomeWork() {
        Single.just("AmitHello")
                .subscribe(getStringsObserver());
    }

    private SingleObserver<String> getStringsObserver(){
        return new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                 Log.i("onSubscribe",d.isDisposed()+"");
            }

            @Override
            public void onSuccess(String s) {
                Log.i("onSuccess",s.toString());
                textView.append(s.toString()+"单例");
                textView.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("onError",e.toString());
            }
        };
    }

    // SingleObserver 单例的观察者
    private SingleObserver<String> getSingleObserver() {
        return new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(String value) {
                textView.append(" onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }
        };
    }

}