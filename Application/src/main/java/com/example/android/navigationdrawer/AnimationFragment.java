package com.example.android.navigationdrawer;

/**
 * Created by Kent_Zheng on 2015/5/26.
 */

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class AnimationFragment extends Fragment {

    private static final String TAG = "AnimationFragment";
    private Context ctx;
    private View mView;


    public AnimationFragment() {
        // Empty constructor required for fragment subclasses
    }

    public static Fragment newInstance() {
        Fragment fragment = new AnimationFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_animation, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mView = getView();
        ctx = mView.getContext();

        initView();

        btnTranslate.setOnClickListener(new AnimationImp());
        btnScale.setOnClickListener(new AnimationImp());
        btnRotate.setOnClickListener(new AnimationImp());
        btnAlpha.setOnClickListener(new AnimationImp());

    }

    private Button btnTranslate, btnScale, btnRotate, btnAlpha;
    private ImageView imageTranslate, imageScale, imageRotate, imageAlpha;

    public void initView(){
        btnTranslate = (Button) mView.findViewById(R.id.btn_translate);
        imageTranslate = (ImageView) mView.findViewById(R.id.image_translate);

        btnScale = (Button) mView.findViewById(R.id.btn_scale);
        imageScale = (ImageView) mView.findViewById(R.id.image_scale);

        btnRotate = (Button) mView.findViewById(R.id.btn_rotate);
        imageRotate = (ImageView) mView.findViewById(R.id.image_rotate);

        btnAlpha = (Button) mView.findViewById(R.id.btn_alpha);
        imageRotate = (ImageView) mView.findViewById(R.id.image_rotate);
    }

    private class AnimationImp implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btn_translate:
                    Animation amTranslate = new
                            TranslateAnimation(0.0f, 100.0f, 0.0f, 100.0f);
                    amTranslate.setDuration(2000);
                    amTranslate.setRepeatCount(2);
                    imageTranslate.startAnimation(amTranslate);
                    break;

                case R.id.btn_scale:
                    Animation amScale = new ScaleAnimation(0.0f, 2.5f, 0.0f, 2.5f);
                    amScale.setDuration(2000);
                    amScale.setRepeatCount(2);
                    imageScale.startAnimation(amScale);
                    break;

                case R.id.btn_rotate:
                    Animation amRotate = new RotateAnimation(0.0f, 360.0f, 0.0f, 100.0f);
                    amRotate.setDuration(2000);
                    amRotate.setRepeatCount(2);
                    imageRotate.startAnimation(amRotate);

                    break;

                case R.id.btn_alpha:
                    Animation amAlpha = new AlphaAnimation(1.0f, 0.0f);
                    amAlpha.setDuration(2000);
                    amAlpha.setRepeatCount(2);
                    imageRotate.startAnimation(amAlpha);
                    break;

                default:
                    break;
            }
        }
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
