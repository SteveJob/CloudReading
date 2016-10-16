package com.kongshanxinyu.cloudreading.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.kongshanxinyu.cloudreading.R;
import com.kongshanxinyu.cloudreading.util.ScreenUtil;

/**
 * Created by Steve on 16/10/9.
 * E-mail: singleframe@aliyun.com
 */
public class BlackRecord extends View implements ValueAnimator.AnimatorUpdateListener {

    private Drawable mTopBoundary;
    private Drawable mBgTransparent;

    private Rect mTopBoundaryRect;
    private Rect mBgTransparentRect;

    private Bitmap mCdFrame;
    private Bitmap mCdCover;
    private Bitmap mNeedle;

    private Matrix mMatrixCd;
    private Matrix mMatrixCdCover;
    private Matrix mMatrixNeedle;

    private Point mBgTransparentPoint;

    private Point mNeedleTopLeft;
    private Point mCdFrameTopLeft;
    private Point mCdCoverTopLeft;

    private Point mNeedleRotationCenter;
    private Point mCdRotationCenter;

    private final float CD_START_ANGLE = 0;
    private final float NEEDLE_START_ANGLE = 0;
    private final float NEEDLE_END_ANGLE = -30;

    private float mCdAngle = 0;
    private float mNeedleAngle = 0;

    private ValueAnimator startAnimator;
    private ValueAnimator pauseAnimator;

    private boolean isPlaying;


    public BlackRecord(Context context) {
        this(context, null);
    }

    public BlackRecord(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlackRecord(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTopBoundary = getResources().getDrawable(R.drawable.blackrecord_top_boundry);
        mBgTransparent = getResources().getDrawable(R.drawable.blackrecord_cd_bg_transparent);

        mCdFrame = BitmapFactory.decodeResource(getResources(), R.mipmap.play_page_disc);
        mCdCover = BitmapFactory.decodeResource(getResources(), R.mipmap.play_page_default_cover);
        mNeedle = BitmapFactory.decodeResource(getResources(), R.mipmap.play_page_needle);

        mMatrixCd = new Matrix();
        mMatrixCdCover=new Matrix();
        mMatrixNeedle = new Matrix();

        startAnimator = ValueAnimator.ofFloat(NEEDLE_START_ANGLE, NEEDLE_END_ANGLE);
        startAnimator.setDuration(200);
        startAnimator.addUpdateListener(this);
        pauseAnimator = ValueAnimator.ofFloat(NEEDLE_START_ANGLE, NEEDLE_END_ANGLE);
        startAnimator.setDuration(200);
        pauseAnimator.addUpdateListener(this);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        preDraw();
    }

    private void preDraw() {

        mTopBoundaryRect = new Rect(0, 0, getWidth(), (int) (getWidth() + ScreenUtil.dp2px(1)));

        mCdFrameTopLeft.x = (getWidth() - mCdFrame.getWidth()) / 2;
        mCdFrameTopLeft.y = mNeedle.getHeight() / 2;
        mCdCoverTopLeft.x = (getWidth() - mCdCover.getWidth()) / 2;
        mCdCoverTopLeft.y = mCdFrameTopLeft.y + (mCdFrame.getHeight() - mCdCover.getHeight()) / 2;
        mNeedleTopLeft.x = ScreenUtil.getScreenWidth() / 2;
        mNeedleTopLeft.y = -10;

        mBgTransparentPoint.x = (int) (mCdFrameTopLeft.x - ScreenUtil.dp2px(2));
        mBgTransparentPoint.y = (int) (mCdFrameTopLeft.y - ScreenUtil.dp2px(2));
        mBgTransparentRect = new Rect(mBgTransparentPoint.x, mBgTransparentPoint.y, (int) (mCdFrameTopLeft.x + mCdFrame.getWidth() + ScreenUtil.dp2px(2)), (int) (mCdFrameTopLeft.y + mCdFrame.getHeight() + ScreenUtil.dp2px(2)));

        mNeedleRotationCenter.x = mNeedleTopLeft.x;
        mNeedleRotationCenter.y = 0;
        mCdRotationCenter.x = getWidth() / 2;
        mCdRotationCenter.y = mCdFrameTopLeft.y + mCdFrame.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制topBoundry
        mTopBoundary.setBounds(mTopBoundaryRect);
        mTopBoundary.draw(canvas);
        //绘制bgTransparent
        mBgTransparent.setBounds(mBgTransparentRect);
        mBgTransparent.draw(canvas);
        //绘制CD
        mMatrixCd.setRotate(mCdAngle,mCdRotationCenter.x,mCdRotationCenter.y);
        mMatrixCd.preTranslate(mCdFrameTopLeft.x,mCdFrameTopLeft.y);
        canvas.drawBitmap(mCdFrame,mMatrixCd,null);
        mMatrixCdCover.setRotate(mCdAngle,mCdRotationCenter.x,mCdRotationCenter.y);
        canvas.drawBitmap(mCdCover,mMatrixCdCover,null);
        //绘制Needle
        mMatrixNeedle.setRotate(mNeedleAngle,mNeedleRotationCenter.x,mNeedleRotationCenter.y);
        mMatrixNeedle.preTranslate(mNeedleTopLeft.x,mNeedleTopLeft.y);
        canvas.drawBitmap(mNeedle,mMatrixNeedle,null);
    }

    public void setmCdCover(Bitmap mCdCover) {
        this.mCdCover = mCdCover;
        invalidate();
    }

    public void startPlay() {
        if (!isPlaying){
            startAnimator.start();
            isPlaying=true;
            handler.postDelayed(runnable,20);
        }
    }

    public void pausePlay() {
        if (isPlaying){
            pauseAnimator.start();
            isPlaying=false;
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mNeedleAngle = (float) animation.getAnimatedValue();
        invalidate();
    }

    private Handler handler=new Handler();
    private CallBackRunnable runnable=new CallBackRunnable();
    private class CallBackRunnable implements Runnable{
        @Override
        public void run() {
            if (isPlaying){
                mCdAngle+=0.5;
                if (mCdAngle>360){
                    mCdAngle=0;
                }
                invalidate();
                handler.postDelayed(runnable,20);
            }
        }
    }
}
