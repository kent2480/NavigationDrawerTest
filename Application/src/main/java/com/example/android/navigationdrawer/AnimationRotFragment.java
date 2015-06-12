package com.example.android.navigationdrawer;

/**
 * Created by Kent_Zheng on 2015/5/26.
 */

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class AnimationRotFragment extends Fragment {

    private static final String TAG = "AnimationProFragment";
    private Context ctx;
    private View mView;


    public AnimationRotFragment() {
        // Empty constructor required for fragment subclasses
    }

    public static Fragment newInstance() {
        Fragment fragment = new AnimationRotFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rot_animation, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mView = getView();
        ctx = mView.getContext();

        initView();

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aniRunning = !aniRunning;

                if (aniRunning) {
                    PropertyValuesHolder mX = PropertyValuesHolder.ofFloat("rotation", 0f,
                            359f);
//                    PropertyValuesHolder tlEY = PropertyValuesHolder.ofFloat("translationY",
//                            dip2px(ctx, 600f), dip2px(ctx, 360f));
//                    PropertyValuesHolder tlEX = PropertyValuesHolder.ofFloat("translationX",
//                            dip2px(ctx, -80f), 0f);
                    mObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(mImageView,
                            mX).setDuration(30000);
                    mObjectAnimator.setRepeatMode(ValueAnimator.RESTART);
                    mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
                    mObjectAnimator.start();

//                    AnimatorSet animSetXY = new AnimatorSet();
//                    animSetXY.play(mObjectAnimator);
//                    animSetXY.start();
                }
            }
        });

        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aniRunning) {
                    mObjectAnimator.cancel();
                    aniRunning = false;
                }
            }
        });

    }


    private Boolean aniRunning = false;
    private Button mBtnStart, mBtnStop;
    private ImageView mImageView;
    private ObjectAnimator mObjectAnimator;

    private void initView() {
        mBtnStart = (Button) mView.findViewById(R.id.btn_start_ani);
        mBtnStop = (Button) mView.findViewById(R.id.btn_stop_ani);
        mImageView = (ImageView) mView.findViewById(R.id.image_man);
    }

    public static float density; // density = 2

    public static float dip2px(Context context, float dipValue) {
        if (density == 0) {
            density = context.getApplicationContext().getResources().getDisplayMetrics().density;
        }
        Log.d(TAG, "dip2px = " + (dipValue * density + 0.5f));
        return (dipValue * density + 0.5f);
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
