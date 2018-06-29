package com.hc.hmsmoblie.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

/**
 *
 */

public class MatrixImageView extends AppCompatImageView {
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
    private PointF mImgInViewPoint = new PointF(0, 0);//图片相对于控件左上角的位置
    private MatrixOnTouchListener mMatrixOnTouchListener;
    private OnDoubleClick mDoubleClick;

    private enum MotionEventModeEnum {
        //拖动图片，放大缩小图片
        DEFAULT, MOVE, SCALE
    }

    public MatrixImageView(Context context) {
        super(context);
        init(context);
    }

    public MatrixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MatrixImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        imageView = this;
        mContext = context;
        mMatrixOnTouchListener = new MatrixOnTouchListener(imageView, new MatrixOnTouchListener.DoubleClick() {
            @Override
            public void onClick(PointF imgInViewPoint, float clickX, float clickY, float imageScale) {
                Log.e("asda", "双击时位置;" + imgInViewPoint.x + "  " + imgInViewPoint.y + " " + imageScale + " " + clickX + " " + clickY);
                //点在图的坐标
                float pointInViewX = (clickX - imgInViewPoint.x);
                float pointInViewY = (clickY - imgInViewPoint.y);
                mDoubleClick.onClick(pointInViewX, pointInViewY, imageScale * mOriginalToCurrentScale);

            }
        });
        setOnTouchListener(mMatrixOnTouchListener);
//        setOnTouchListener((v,event)->motionEvent(event));
    }

    public void setOnDoubleClick(OnDoubleClick doubleClick) {
        mDoubleClick = doubleClick;
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
                        mImgInViewPoint.set((float) ((mImageViewWidth - mCurrentWidth) / 2), (float) ((mImageViewHeight - mCurrentHeight) / 2));
                        ivMatrix.setTranslate(mImgInViewPoint.x, mImgInViewPoint.y);
                        setImageMatrix(ivMatrix);
                        Log.e("asda", "原始时位置;" + mImgInViewPoint.x + "  " + mImgInViewPoint.y);
                        mMatrixOnTouchListener.setImgInViewPoint(mImgInViewPoint.x, mImgInViewPoint.y);
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

    public interface OnDoubleClick {
        void onClick(float pointInViewX, float pointInViewY, double scale);
    }
}
