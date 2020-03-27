package com.baiheng.cameratest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

public class CameraTestView extends View {
    private Camera camera;
    private Matrix matrix;
    private Paint paint;
    private Resources mResources;
    private Bitmap mBitmap;
    private int girlBitWidth , girlBitHeight;
    private Rect girlSrcRect , girlDesRect;
    private int mTotalWidth, mTotalHeight;

    public CameraTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        camera = new Camera();
        matrix = new Matrix();
        setBackgroundColor(Color.parseColor("#3f51b5"));
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#ff4081"));
        mResources = getResources();
        initBitmap();
    }

    private void initBitmap() {
        mBitmap = ((BitmapDrawable)mResources.getDrawable(R.drawable.a1)).getBitmap();
        girlBitWidth = mBitmap.getWidth();
        girlBitHeight = mBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        matrix.reset();
        camera.save();
        camera.rotateZ(-50);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-getWidth()/2, -getHeight()/2);
        matrix.postTranslate(getWidth()/2, getHeight()/2);

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.a1,option);
        option.inSampleSize = calculateInSampleSize(option, getWidth()/2, getHeight()/2);
        option.inJustDecodeBounds = false;
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.a1,option), matrix, paint);
    }
    private int calculateInSampleSize(BitmapFactory.Options options,
                                      int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
////        matrix.reset();
////        camera.save();
////        camera.translate(10, 50, -180);
////        camera.getMatrix(matrix);
////        camera.restore();
////        canvas.concat(matrix);
//        canvas.drawCircle(60, 60, 60, paint);

//        canvas.save();
//
//        camera.save(); // 保存 Camera 的状态
//        camera.rotateX(30); // 旋转 Camera 的三维空间
//        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
//        camera.restore(); // 恢复 Camera 的状态
//
//        canvas.drawBitmap(mBitmap, 200, 200, paint);
//        canvas.restore();

//        canvas.save();
//
//        camera.save(); // 保存 Camera 的状态
//        camera.rotateX(30); // 旋转 Camera 的三维空间
//        canvas.translate(200, 200); // 旋转之后把投影移动回来
//        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
//        canvas.translate(-200, -200); // 旋转之前把绘制内容移动到轴心（原点）
//        camera.restore(); // 恢复 Camera 的状态
//
//        canvas.drawBitmap(mBitmap, 200, 200, paint);
//        canvas.restore();
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
////        Log.d("xxxxxx", "onSizeChanged , w = "+w+" , h = "+h+" , mBitWidth = "+mBitWidth+" , mBitHeight = "+mBitHeight);
//        super.onSizeChanged(w, h, oldw, oldh);
//        mTotalWidth = w;
//        mTotalHeight = h;
//
//        girlSrcRect = new Rect(0, 0, girlBitWidth, girlBitHeight);
//        girlDesRect = new Rect(0, 0, girlBitWidth, girlBitHeight);
//
//    }
}
