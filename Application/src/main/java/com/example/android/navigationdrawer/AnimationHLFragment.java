package com.example.android.navigationdrawer;

/**
 * Created by Kent_Zheng on 2015/5/26.
 */

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        btnProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
    }

    private void startAnimation() {
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 1f);
        animation.setTarget(imageProperty);
        animation.setDuration(5000);
        animation.start();
    }

    private Button btnProperty;
    private ImageView imageProperty;

    public void initView(){
        btnProperty = (Button) mView.findViewById(R.id.btn_property);
        imageProperty = (ImageView) mView.findViewById(R.id.image_property);

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
