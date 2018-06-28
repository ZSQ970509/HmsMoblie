package com.hc.hmsmoblie.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

/**
 *
 */

public class MatrixImageViewOld extends AppCompatImageView {
    private Context mContext;
    private int mImageViewWidth;//控件的宽度
    private int mImageViewHeight;//控件的高度
    private int mOriginalImageWidth;//原图的宽度
    private int mOriginalImageHeight;//原图的高度
    private double mOriginalToCurrentScale = 1;//原图与当前显示图的缩放比例
    private double mCurrentWidth;//当前图的宽度
    private double mCurrentHeight;//当前图的高度
    private PointF mCurrentInView = new PointF(0, 0);//当前图的左上角 相对于 控件的左上角 的位置（图在控件下x为正，上x为负，右y为正，左y为负）
    private OnTouchListener mOnTouchListener;
    private MotionEventModeEnum mMotionEventTypeEnum = MotionEventModeEnum.DEFAULT;//触摸的模式
    private ImageView imageView;

    private enum MotionEventModeEnum {
        //拖动图片，放大缩小图片
        DEFAULT, MOVE, SCALE
    }

    public MatrixImageViewOld(Context context) {
        super(context);
        init(context);
    }

    public MatrixImageViewOld(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MatrixImageViewOld(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        imageView = this;
        mContext = context;
        mOnTouchListener = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return motionEventmy(event);
            }
        };
        setOnTouchListener(mOnTouchListener);
//        setOnTouchListener((v,event)->motionEvent(event));
    }

    private PointF mMotionMoveStartPoint = new PointF(0, 0);//拖动的开始点
    private float mMotionScaleStartDistance = 0f;//放大/缩小开始时两指间的距离
    private PointF mMotionScaleMidPoint = new PointF(0, 0);//放大/缩小的中间点
    private Matrix mCurrentMatrix = new Matrix();

    private float mImageScale = 1;
    Matrix mMatrix = new Matrix();

    /**
     * 控件触摸动作
     *
     * @param event
     * @return
     */
    private boolean motionEventmy(MotionEvent event) {
        // 通过与运算保留最后八位 MotionEvent.ACTION_MASK = 255
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN://单点触摸动作
                mMotionEventTypeEnum = MotionEventModeEnum.MOVE;//拖动图片模式
                mCurrentMatrix.set(imageView.getImageMatrix());
                mMotionMoveStartPoint.set(event.getX(), event.getY());//拖动的开始位置(相对于控件左上角的位置)
                break;
            case MotionEvent.ACTION_MOVE://触摸点移动动作（包含多点和单点）
                if (mMotionEventTypeEnum == MotionEventModeEnum.MOVE) {
                    float moveX = event.getX() - mMotionMoveStartPoint.x;
                    float moveY = event.getY() - mMotionMoveStartPoint.y;
                    mMatrix.set(mCurrentMatrix);
                    mMatrix.setTranslate(moveX, moveY);
                } else if (mMotionEventTypeEnum == MotionEventModeEnum.SCALE) {
//                    float endDistance = pointDistance(event.getX(0), event.getY(0), event.getX(1), event.getY(1));
                    float endDistance = distance(event);
                    if (endDistance > 10f) {
                        mImageScale *= distance(event) / mMotionScaleStartDistance;
                        mMatrix.set(mCurrentMatrix);
                        mMatrix.postScale(mImageScale, mImageScale, mMotionScaleMidPoint.x, mMotionScaleMidPoint.y);
//                        scaleImage(mMotionScaleMidPoint.x, mMotionScaleMidPoint.y, scale, mMotionMatrixStart);
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN://多点触摸动作
                mMotionEventTypeEnum = MotionEventModeEnum.SCALE;//放大/缩小图片模式
//                mMotionScaleStartDistance = pointDistance(event.getX(0), event.getY(0), event.getX(1), event.getY(1));
                mMotionScaleStartDistance = distance(event);
                if (mMotionScaleStartDistance > 10f) {
                    mMotionScaleMidPoint = pointMid(event.getX(0), event.getY(0), event.getX(1), event.getY(1));
                    mCurrentMatrix.set(imageView.getImageMatrix());
                }
                break;
            case MotionEvent.ACTION_UP://单点触摸离开动作
                mMotionEventTypeEnum = MotionEventModeEnum.DEFAULT;//拖动图片模式
                break;
            case MotionEvent.ACTION_POINTER_UP://多点离开动作
                mMotionEventTypeEnum = MotionEventModeEnum.DEFAULT;//拖动图片模式
                break;
        }
        imageView.setImageMatrix(mMatrix);
        return true;
    }

    /**
     * 加载网络图片
     *
     * @param imgUrl
     */
    public void loadNetImage(String imgUrl) {
        Glide.with(mContext)
                .load(imgUrl)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                        //获取图片原始宽高
                        mOriginalImageWidth = resource.getWidth();//原始图的宽度
                        mOriginalImageHeight = resource.getHeight();//原始图的高度

                        //获取图片显示（即拉伸后的图）后的宽高
                        if (mOriginalImageWidth / mOriginalImageHeight - mImageViewWidth / mImageViewHeight > 0) {//图片的宽大于容器的宽
                            double tmpHeight = mImageViewWidth * mOriginalImageHeight / mOriginalImageWidth;
                            if (tmpHeight > mImageViewHeight) {
                                mCurrentWidth = mImageViewWidth * mImageViewHeight / tmpHeight;
                                mCurrentHeight = mImageViewHeight;
                            } else {
                                mCurrentWidth = mImageViewWidth;
                                mCurrentHeight = tmpHeight;
                            }
                        } else {
                            double tmpWidth = mImageViewHeight * mOriginalImageWidth / mOriginalImageHeight;
                            if (tmpWidth > mImageViewWidth) {
                                mCurrentWidth = mImageViewWidth;
                                mCurrentHeight = mImageViewHeight * mImageViewWidth / tmpWidth;
                            } else {
                                mCurrentWidth = tmpWidth;
                                mCurrentHeight = mImageViewHeight;
                            }
                        }
                        Log.i("aa", "当前：" + mCurrentHeight + " " + mCurrentWidth + " " + " 原始：" + mOriginalImageHeight + " " + mOriginalImageWidth + " 控件：" + mImageViewHeight + " " + mImageViewWidth);
                        //压缩图片
                        Matrix matrix = new Matrix();
                        mOriginalToCurrentScale = mCurrentWidth / mOriginalImageWidth;
                        matrix.setScale((float) mOriginalToCurrentScale, (float) mOriginalToCurrentScale);
                        resource = Bitmap.createBitmap(resource, 0, 0, mOriginalImageWidth, mOriginalImageHeight, matrix, true);
                        setImageBitmap(resource);
                        //图片位置相对于控件 居中
//                        moveImage((float) ((mImageViewWidth - mCurrentWidth) / 2), (float) ((mImageViewHeight - mCurrentHeight) / 2), matrix);
                        //图片位置相对于控件 居中
                        Matrix ivMatrix = getMatrix();
                        ivMatrix.setTranslate((int) ((mImageViewWidth - mCurrentWidth) / 2), (int) ((mImageViewHeight - mCurrentHeight) / 2));
                        setImageMatrix(ivMatrix);
                    }
                });
    }

    /**
     * 移动图片的位置（不是移动到（x,y）位置）
     *
     * @param x 往下移动x距离
     * @param y 往上移动y距离
     */
//    private void moveImage(float x, float y, Matrix matrix) {
//        mCurrentInView.x += x;
//        mCurrentInView.y += y;
//        matrix.setTranslate(mCurrentInView.x, mCurrentInView.y);
//        setImageMatrix(matrix);
//    }

    /**
     * 缩放图片
     *
     * @param midX  中心点x
     * @param midY  中心点y
     * @param scale 缩放比例
     */
    private void scaleImage(float midX, float midY, float scale, Matrix matrix) {
        matrix.postScale(scale, scale, midX, midY);
        setImageMatrix(matrix);
    }

    /**
     * 两点间的距离
     */
    private float pointDistance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * 两点间的中心点
     */
    private PointF pointMid(float x1, float y1, float x2, float y2) {
        return new PointF((x1 + x2) / 2, (y1 + y2) / 2);
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mImageViewWidth = getMeasuredWidth();
        mImageViewHeight = getMeasuredHeight();
        Log.i("aa", "----------onSizeChanged-------------");
        Log.i("aa", "getMeasuredHeight:" + getMeasuredHeight() + " getMeasuredWidth:" + getMeasuredWidth());
        Log.i("aa", "getHeight:" + getHeight() + " getWidth" + getWidth());
        Log.i("aa", "----------onSizeChanged-------------");
    }
}
