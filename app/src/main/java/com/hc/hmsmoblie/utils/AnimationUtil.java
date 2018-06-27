package com.hc.hmsmoblie.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.hc.hmsmoblie.mvp.contact.LoginC;

/**
 * Created by Administrator on 2018/6/22.
 */

public class AnimationUtil {
    public static void getDismissAndShowAlpha(View dismissView, View showView) {
        ObjectAnimator showAnim = ObjectAnimator.ofFloat(showView, "alpha", 0f, 1f);
        AnimatorSet showAnimatorSet = new AnimatorSet();  //组合动画
        showAnimatorSet.playTogether(showAnim); //设置动画
        showAnimatorSet.setDuration(1000);  //设置动画时间

        //动画的监听
        showAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                showView.setVisibility(View.VISIBLE);


            }

            @Override
            public void onAnimationEnd(Animator animator) {
                showView.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animator) {


            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
            ObjectAnimator anim = ObjectAnimator.ofFloat(dismissView, "alpha", 1f, 0f);
            AnimatorSet animatorSet = new AnimatorSet();  //组合动画
            animatorSet.playTogether(anim); //设置动画
            animatorSet.setDuration(1000);  //设置动画时间
            animatorSet.start(); //启动
            dismissView.setClickable(false);
            //动画的监听
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {


                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    dismissView.setClickable(true);
                    dismissView.setVisibility(View.GONE);
                    showAnimatorSet.start(); //启动
                    showView.setVisibility(View.VISIBLE);
                    showView.setClickable(false);

                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    dismissView.setClickable(true);
                    dismissView.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

    }
    public static void rightAroundRotation(View view) {
        ObjectAnimator ra = ObjectAnimator.ofFloat(view,"rotation", 0f, 360f,360f ,0f);
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        animatorSet.playTogether(ra); //设置动画
        animatorSet.setDuration(2000);  //设置动画时间
        animatorSet.start(); //启动
        view.setClickable(false);
        //动画的监听
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override

            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                view.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    public static void rightRotation(View view) {
        ObjectAnimator ra = ObjectAnimator.ofFloat(view,"rotation", 0f, 360f);
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        animatorSet.playTogether(ra); //设置动画
        animatorSet.setDuration(1000);  //设置动画时间
        animatorSet.start(); //启动
        view.setClickable(false);
        //动画的监听
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                view.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    public static void LeftRotation(View view) {
        ObjectAnimator ra = ObjectAnimator.ofFloat(view,"rotation", 360f, 0f);
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        animatorSet.playTogether(ra); //设置动画
        animatorSet.setDuration(1000);  //设置动画时间
        animatorSet.start(); //启动
        view.setClickable(false);
        //动画的监听
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                view.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public static void getShowAlpha(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        animatorSet.playTogether(anim); //设置动画
        animatorSet.setDuration(1000);  //设置动画时间
        animatorSet.start(); //启动
        view.setVisibility(View.VISIBLE);
        view.setClickable(false);
        //动画的监听
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {



            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animator) {


            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    public static void getDismissAlpha(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        animatorSet.playTogether(anim); //设置动画
        animatorSet.setDuration(1000);  //设置动画时间
        animatorSet.start(); //启动
        view.setClickable(false);
        //动画的监听
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {


            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setClickable(true);
                view.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationCancel(Animator animator) {
                view.setClickable(true);
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
