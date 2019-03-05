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
import io.reactivex.disposables.Disposable;

/**
 * Created by amitshekhar on 27/08/16.
 * 操作符
 *
 * concat 把两个数组以字符串的方式联系起来
 *
 *
 */
public class ConcatExampleActivity extends AppCompatActivity {

    private static final String TAG = ConcatExampleActivity.class.getSimpleName();
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
     * Using concat operator to combine Observable : concat maintain
     * the order of Observable.
     * It will emit all the 7 values in order
     * here - first "A1", "A2", "A3", "A4" and then "B1", "B2", "B3"
     * first all from the first Observable and then
     * all from the second Observable all in order
     */
    private void doSomeWork() {
        final String[] aStrings = {"A1", "A2", "A3", "A4"};
        final String[] bStrings = {"B1", "B2", "B3"};

        final Observable<String> aObservable = Observable.fromArray(aStrings);
        final Observable<String> bObservable = Observable.fromArray(bStrings);

        final String[] cStrings={"C1","c2","c3","c4"};
        final String[] dStrings={"d1","d2","d3","d4"};

        final Observable<String> cObservable= Observable.fromArray(cStrings);
        final Observable<String> dObservable= Observable.fromArray(dStrings);

        Observable.concat(cObservable,dObservable).subscribe(getObserver1());

        /**
         * 将数组转换成string，然后去显示
         * concat 将两个string 字符串合并到一起
         */
//        Observable.concat(aObservable, bObservable)
//                .subscribe(getObserver());
    }

    private Observer<String> getObserver1(){
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("onSubscribe  ",d.isDisposed()+"");
            }

            @Override
            public void onNext(String s) {
                textView.append(s);
                textView.append(AppConstant.LINE_SEPARATOR);

            }

            @Override
            public void onError(Throwable e) {
                textView.append(e.toString());
                textView.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onComplete() {
                Log.i("onComplete","");
            }
        };
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                textView.append(" onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }


}