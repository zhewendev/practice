package com.baiheng.colormatrixdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;

import java.util.Arrays;

public class ColorMatrixView extends View {

    private float[] mColorMatrix = new float[20];
    private Bitmap mBitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.maidou);
    private Paint mPaint = new Paint();



    public ColorMatrixView(Context context) {
        super(context);
//        mBitmap =setImageMatrix(mBitmap);
//        mBitmap = handleImageEffect(mBitmap,0f,0.7f,0.5f);
        mBitmap = changeBright(mBitmap, 0.5f);
    }

    public Bitmap handleImageEffect(Bitmap bitmap,float rotate,float saturation,float scale){
        // 创建副本，用于将处理过的图片展示出来而不影响原图，Android系统也不允许直接修改原图
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        // 修改色调,即色彩矩阵围绕某种颜色分量旋转
        ColorMatrix rotateMatrix = new ColorMatrix();
        // 0,1,2分别代表像素点颜色矩阵中的Red，Green,Blue分量
        rotateMatrix.setRotate(0,rotate);
        rotateMatrix.setRotate(1,rotate);
        rotateMatrix.setRotate(2,rotate);

        // 修改饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        // 修改亮度，即某种颜色分量的缩放
        ColorMatrix scaleMatrix = new ColorMatrix();
        // 分别代表三个颜色分量的亮度
        scaleMatrix.setScale(scale,scale,scale,1);

        //将三种效果结合
        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(rotateMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(scaleMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        return bmp;
    }

    private Bitmap setImageMatrix(Bitmap bitmap){
        reset();
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        return bmp;
    }

    private Bitmap changeBright(Bitmap bitmap, float brightness) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap dstBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);

        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.setScale(brightness, brightness, brightness, 1);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

        Canvas canvas = new Canvas(dstBitmap);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return dstBitmap;
    }

    public void reset() {
        final float[] a = mColorMatrix;
        Arrays.fill(a, 0);
        a[0] = a[6] = a[12] = a[18] = 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0,0,mPaint);
    }
}
