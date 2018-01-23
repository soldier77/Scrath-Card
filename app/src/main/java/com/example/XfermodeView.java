package com.example;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 图片刮刮卡
 * XML文件中设置Bitmap属性设置刮刮卡底部图片
 * 设置监听器XfViewListener，方法内调用setBitmap设置刮刮卡底部的图片
 * 原理：
 * （1）两张图片 1、没刮过或刮过的刮层 2、底下的图片
 * （2）先画出图片，再画刮层，就形成了一张刮刮卡
 * 每次onTouch都重复（1）（2）操作
 */

public class XfermodeView extends View {
    private Bitmap mBitmap,fBitmap;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path path;
    private int mWidth,mHeight,bitmapId;
    private boolean isSetBitmap = false;
    private XfViewListener xfViewListener;

    public XfermodeView(Context context,AttributeSet attrs){
        super(context,attrs);
        getAttrs(context,attrs);
    }
    public void setmBitmap(final Bitmap bitmap){
        mBitmap = Bitmap.createScaledBitmap(bitmap,mWidth,mHeight,false);
        if(mBitmap != null) init();
    }
    private void getAttrs(Context context,AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.XfermodeView);
        bitmapId = ta.getResourceId(R.styleable.XfermodeView_Bitmap,0);
        ta.recycle();

    }
    public void setXfViewListener(XfViewListener xfViewListener){
        this.xfViewListener = xfViewListener;
    }
    public void setmBitmap(int id){
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),id);
        if(isSetBitmap)
        mBitmap = Bitmap.createScaledBitmap(bitmap,mWidth,mHeight,false);
        if(mBitmap != null)
        init();
    }
    private void init(){
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(90);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        path = new Path();
        fBitmap = Bitmap.createBitmap(mBitmap.getWidth(),mBitmap.getHeight(),Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(fBitmap);
        mCanvas.drawColor(Color.GRAY);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                break;
        }
        mCanvas.drawPath(path,mPaint);
        invalidate();
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if(mBitmap != null){
            canvas.drawBitmap(mBitmap,0,0,null);
            canvas.drawBitmap(fBitmap,0,0,null);
        }else
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = measureWidth(widthMeasureSpec);
        mHeight = measureHight(heightMeasureSpec);
        if (!isSetBitmap){
            isSetBitmap = true;
            if (bitmapId != 0){
                setmBitmap(bitmapId);
            } else{
                xfViewListener.canSetBitmap();
            }
        }
        setMeasuredDimension(mWidth,mHeight);
    }
    /**
     * 解析MeasureSpec
     */
    private int measureWidth(int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            if(specMode == MeasureSpec.AT_MOST){
                result = 200;
                result = Math.min(result,specSize);
            }
        }
        return result;
    }
    private int measureHight(int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            if(specMode == MeasureSpec.AT_MOST){
                result = 300;
                result = Math.min(result,specSize);
            }
        }
        return result;
    }
    public int getmWidth(){
        return mWidth;
    }
    public int getmHeight(){
        return mHeight;
    }
}
