package com.example;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private XfermodeView xfermodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xfermodeView = findViewById(R.id.myXfermodeView);
        System.out.println("onCreate 宽度"+xfermodeView.getmWidth()+"高度"+xfermodeView.getmHeight());
        xfermodeView.setXfViewListener(new XfViewListener() {
            @Override
            public void canSetBitmap() {
            }
        });
//        xfermodeView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                System.out.println("OnGlobalLayoutListener 宽度"+xfermodeView.getmWidth()+"高度"+xfermodeView.getmHeight());
//            }
//        });
    }
/*
    @Override
    protected void onStart() {
        System.out.println("onStart 宽度"+xfermodeView.getmWidth()+"高度"+xfermodeView.getmHeight());
        super.onStart();
    }

    @Override
    protected void onResume() {
        System.out.println("onResume 宽度"+xfermodeView.getmWidth()+"高度"+xfermodeView.getmHeight());
        super.onResume();
        System.out.println("onResume之后 宽度"+xfermodeView.getmWidth()+"高度"+xfermodeView.getmHeight());
    }

    @Override
    protected void onPause() {
        System.out.println("onPause 宽度"+xfermodeView.getmWidth()+"高度"+xfermodeView.getmHeight());
        super.onPause();
        System.out.println("onPause之后 宽度"+xfermodeView.getmWidth()+"高度"+xfermodeView.getmHeight());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        System.out.println("onWindowFocusChanged 宽度"+xfermodeView.getmWidth()+"高度"+xfermodeView.getmHeight());
        super.onWindowFocusChanged(hasFocus);
        System.out.println("onWindowFocusChanged之后 宽度"+xfermodeView.getmWidth()+"高度"+xfermodeView.getmHeight());
    }
    */
}
