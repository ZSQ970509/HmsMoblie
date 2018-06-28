package com.hc.hmsmoblie.widget;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 *
 */

public class MatrixOnTouchListener implements View.OnTouchListener {

    /**
     * 记录是拖拉照片模式还是放大缩小照片模式
     */
    private int mode = 0;// 初始状态
    /**
     * 拖拉照片模式
     */
    private static final int MODE_DRAG = 1;
    /**
     * 放大缩小照片模式
     */
    private static final int MODE_ZOOM = 2;

    /**
     * 用于记录开始时候的坐标位置
     */
    private PointF startPoint = new PointF();
    /**
     * 用于记录拖拉图片移动的坐标位置
     */
    private Matrix matrix = new Matrix();
    /**
     * 用于记录图片要进行拖拉时候的坐标位置
     */
    private Matrix currentMatrix = new Matrix();

    /**
     * 两个手指的开始距离
     */
    private float startDis;
    /**
     * 两个手指的中间点
     */
    private PointF midPoint;
    private ImageView imageView;
    private long mClickFirstTime = 0;
    private long mClickDownTime = 0;
    private float mImageScale = 1;//与开始相比缩放的比例
    private float mImageScaleOne = 1;//单次缩放的比例
    private PointF mImgInViewPoint = new PointF(0, 0);//图片相对于控件左上角的位置
    private DoubleClick mDoubleClick;

    public MatrixOnTouchListener(ImageView imageView, DoubleClick doubleClick) {
        this.imageView = imageView;
        mDoubleClick = doubleClick;
    }

    public void setImgInViewPoint(float x, float y) {
        mImgInViewPoint.set(x, y);
        Log.e("asda", "开始时位置;" + mImgInViewPoint.x + "  " + mImgInViewPoint.y);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        /** 通过与运算保留最后八位 MotionEvent.ACTION_MASK = 255 */
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            // 手指压下屏幕
            case MotionEvent.ACTION_DOWN://单点触摸动作
                mode = MODE_DRAG;
                mClickDownTime = System.currentTimeMillis();
                // 记录ImageView当前的移动位置
                currentMatrix.set(imageView.getImageMatrix());
                startPoint.set(event.getX(), event.getY());
                break;
            // 手指在屏幕上移动，改事件会被不断触发
            case MotionEvent.ACTION_MOVE://触摸点移动动作
                // 拖拉图片
                if (mode == MODE_DRAG) {
                    float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
                    float dy = event.getY() - startPoint.y; // 得到x轴的移动距离
                    // 在没有移动之前的位置上进行移动
                    matrix.set(currentMatrix);
                    Log.e("asd", "移动：" + dx + " " + dy + " " + event.getX() + " " + event.getY() + " " + startPoint.x + " " + startPoint.y + " " + mImgInViewPoint.x + " " + mImgInViewPoint.y);
//                    mImgInViewPoint.x += dx;
//                    mImgInViewPoint.y = dy;
                    matrix.postTranslate(dx, dy);
                    imageView.setImageMatrix(matrix);
                }
                // 放大缩小图片
                else if (mode == MODE_ZOOM) {
                    float endDis = distance(event);// 结束距离
                    if (endDis > 10f && startDis > 0) { // 两个手指并拢在一起的时候像素大于10
                        float scale = endDis / startDis;// 得到缩放倍数
                        matrix.set(currentMatrix);
                        Log.e("asd", "比例" + " " + scale + " " + mImageScale);
                        mImageScaleOne = scale;
                        matrix.postScale(scale, scale, mImgInViewPoint.x, mImgInViewPoint.y);
                        imageView.setImageMatrix(matrix);
                    }
                }
                break;
            // 手指离开屏幕
            case MotionEvent.ACTION_UP://单点触摸离开动作

                long clickTimeUp = System.currentTimeMillis();
                long clickTimeInterval = clickTimeUp - mClickDownTime;
                if (clickTimeInterval < 100) {//手指按下到放开的时间间隔 小于500秒 才是算一次点击
                    if (clickTimeUp - mClickFirstTime < 1000) {
                        Log.e("asd", "双击了");
                        mDoubleClick.onClick(mImgInViewPoint, event.getX(), event.getY(), mImageScale);
                    } else {
                        mClickFirstTime = clickTimeUp;
                        Log.e("asd", "单击了");
                    }
                    break;
                }
                if (mode == MODE_DRAG) {
                    mImgInViewPoint.x += event.getX() - startPoint.x;
                    mImgInViewPoint.y += event.getY() - startPoint.y;
                }
                // 当触点离开屏幕，但是屏幕上还有触点(手指)
                mode = 0;
                break;
            case MotionEvent.ACTION_POINTER_UP://多点离开动作
                mode = 0;
                Log.e("asd", "放开时的比例：mImageScale:" + mImageScale + " One:" + mImageScaleOne + " Scale:" + mImageScaleOne * mImageScale);
                mImageScale *= mImageScaleOne;
                break;
            // 当屏幕上已经有触点(手指)，再有一个触点压下屏幕
            case MotionEvent.ACTION_POINTER_DOWN://多点触摸动作
                mode = MODE_ZOOM;
                    /* 计算两个手指间的距离 */
                startDis = distance(event);
                    /* 计算两个手指间的中间点 */
                if (startDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                    midPoint = mid(event);
                    //记录当前ImageView的缩放倍数
                    currentMatrix.set(imageView.getImageMatrix());
                }
                break;
        }

        return true;
    }

    /**
     * 计算两个手指间的距离
     */
    private float distance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        /** 使用勾股定理返回两点之间的距离 */
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 计算两个手指间的中间点
     */
    private PointF mid(MotionEvent event) {
        float midX = (event.getX(1) + event.getX(0)) / 2;
        float midY = (event.getY(1) + event.getY(0)) / 2;
        return new PointF(midX, midY);
    }

    public interface DoubleClick {
        void onClick(PointF imgInViewPoint, float clickX, float clickY, float imageScale);
    }
}