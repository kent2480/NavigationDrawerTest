package com.example.android.navigationdrawer;

/**
 * Created by Kent_Zheng on 2015/5/26.
 */

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;


public class AnimationHLFragment extends Fragment {

    private static final String TAG = "AnimationHLFragment";
    private Context ctx;
    private View mView;


    public AnimationHLFragment() {
        // Empty constructor required for fragment subclasses
    }

    public static Fragment newInstance() {
        Fragment fragment = new AnimationHLFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hl_animation, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mView = getView();
        ctx = mView.getContext();

        initView();

        btnFlip.setOnClickListener(new AnimationImplement());
        btnAlpha.setOnClickListener(new AnimationImplement());
        btnScale.setOnClickListener(new AnimationImplement());
        btnTranslate.setOnClickListener(new AnimationImplement());
        btnRotate.setOnClickListener(new AnimationImplement());
        btnSet.setOnClickListener(new AnimationImplement());
        btnPopup.setOnClickListener(new AnimationImplement());

    }

    private class  AnimationImplement implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClik");
            switch(v.getId()) {
                case R.id.btn_flip:
                    performFlip();
                    break;

                case R.id.btn_alpha:
                    performAlpha();
                    break;

                case R.id.btn_scale:
                    performScale();

                    break;

                case R.id.btn_translate:
                    performTranslate();

                    break;

                case R.id.btn_rotate:
                    performRotate();
                    break;

                case R.id.btn_set:
                    performSet();
                    break;

                case R.id.btn_popup:
                    performPopup();

                default:
                    break;
            }
        }


    }

    private void performPopup() {
        Log.d(TAG, "performPopup");
        AnimatorSet mAnimatorSetPopup = new AnimatorSet();
        Log.d(TAG, "Image Height = " + image.getHeight());
        Log.d(TAG, "Image Width = " + image.getWidth());

        ObjectAnimator mAnimatorSetPivotX =
                ObjectAnimator.ofFloat(image, "pivotX", 0.0f + 6);

        ObjectAnimator mAnimatorSetPivotY =
                ObjectAnimator.ofFloat(image, "pivotY", 0.0f + image.getWidth()-6);

        ObjectAnimator mAnimatorScaleX =
                ObjectAnimator.ofFloat(image, "scaleX", 0.0f, 1.0f);
        mAnimatorScaleX.setRepeatCount(0);
        mAnimatorScaleX.setRepeatMode(Animation.REVERSE);
        mAnimatorScaleX.setDuration(3000);

        ObjectAnimator mAnimatorScaleY =
                ObjectAnimator.ofFloat(image, "scaleY", 0.0f, 1.0f);
        mAnimatorScaleY.setRepeatCount(0);
        mAnimatorScaleY.setRepeatMode(Animation.REVERSE);
        mAnimatorScaleY.setDuration(3000);

        mAnimatorSetPopup.play(mAnimatorSetPivotX)
                .with(mAnimatorSetPivotY)
                .with(mAnimatorScaleX)
                .with(mAnimatorScaleY);
//        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
//        mAnimatorSet.play(mAnimatorSetRotateY).before(mAnimatorSetRotateX);

        mAnimatorSetPopup.start();
    }

    private void performFlip() {
        ObjectAnimator filp = ObjectAnimator.ofFloat(image, "rotationX", 0.0f, 360.0f);
        filp.setInterpolator(new AccelerateInterpolator());
        filp.setRepeatCount(1);
        filp.setDuration(2500);
        filp.start();
    }

    private void performAlpha() {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(image, "alpha", 1.0f, 0.0f);
        alpha.setRepeatCount(1);
        alpha.setDuration(2500);
        alpha.start();
    }

    private void performScale() {
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(image, "scaleX", 1.0f, 0.0f);
        mAnimatorScaleX.setRepeatMode(Animation.REVERSE);
        mAnimatorScaleX.setRepeatCount(1);
        mAnimatorScaleX.setDuration(1000);

        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(image, "scaleY", 1.0f, 0.0f);
        mAnimatorScaleY.setRepeatMode(Animation.REVERSE);
        mAnimatorScaleY.setRepeatCount(1);
        mAnimatorScaleY.setDuration(1000);

        mAnimatorScaleX.start();
        mAnimatorScaleY.start();
    }

    private void performTranslate() {
        ObjectAnimator mAnimatorTranslateX =
                ObjectAnimator.ofFloat(image, "translationX", 0.0f, 100.0f);
        mAnimatorTranslateX.setRepeatMode(Animation.REVERSE);
        mAnimatorTranslateX.setRepeatCount(1);
        mAnimatorTranslateX.setDuration(1000);

        ObjectAnimator mAnimatorTranslateY =
                ObjectAnimator.ofFloat(image, "translationY", 0.0f, 100.0f);
        mAnimatorTranslateY.setRepeatMode(Animation.REVERSE);
        mAnimatorTranslateY.setRepeatCount(1);
        mAnimatorTranslateY.setDuration(1000);

        mAnimatorTranslateX.start();
        mAnimatorTranslateY.start();

    }

    private void performRotate() {
        ObjectAnimator mAnimatorRotate = ObjectAnimator.ofFloat(image, "rotation", 0.0f, 360.0f);
        mAnimatorRotate.setRepeatMode(Animation.REVERSE);
        mAnimatorRotate.setRepeatCount(1);
        mAnimatorRotate.setDuration(2000);

        mAnimatorRotate.start();
    }


    private void performSet() {
        AnimatorSet mAnimatorSet = new AnimatorSet();

        ObjectAnimator mAnimatorSetRotateX =
                ObjectAnimator.ofFloat(image, "rotationX", 0.0f,360.0f);
        mAnimatorSetRotateX.setDuration(3000);

        ObjectAnimator mAnimatorSetRotateY =
                ObjectAnimator.ofFloat(image, "rotationY", 0.0f,360.0f);
        mAnimatorSetRotateY.setDuration(3000);

        ObjectAnimator mAnimatorScaleX =
                ObjectAnimator.ofFloat(image, "scaleX", 1.0f, 0.5f);
        mAnimatorScaleX.setRepeatCount(1);
        mAnimatorScaleX.setRepeatMode(Animation.REVERSE);
        mAnimatorScaleX.setDuration(1500);

        ObjectAnimator mAnimatorScaleY =
                ObjectAnimator.ofFloat(image, "scaleY", 1.0f, 0.5f);
        mAnimatorScaleY.setRepeatCount(1);
        mAnimatorScaleY.setRepeatMode(Animation.REVERSE);
        mAnimatorScaleY.setDuration(1500);

        mAnimatorSet.play(mAnimatorSetRotateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.play(mAnimatorSetRotateY).before(mAnimatorSetRotateX);

        mAnimatorSet.start();
    }



    private Button btnFlip, btnAlpha, btnScale, btnTranslate, btnRotate, btnSet,
            btnPopup;
    private ImageView image;

    public void initView(){
        btnFlip = (Button) mView.findViewById(R.id.btn_flip);
        btnAlpha = (Button) mView.findViewById(R.id.btn_alpha);
        btnScale = (Button) mView.findViewById(R.id.btn_scale);
        btnTranslate = (Button) mView.findViewById(R.id.btn_translate);
        btnRotate = (Button) mView.findViewById(R.id.btn_rotate);
        btnSet = (Button) mView.findViewById(R.id.btn_set);
        btnPopup = (Button) mView.findViewById(R.id.btn_popup);

        image = (ImageView) mView.findViewById(R.id.image_property);


    }



    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
