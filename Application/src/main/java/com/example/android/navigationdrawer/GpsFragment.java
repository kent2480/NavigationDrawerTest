package com.example.android.navigationdrawer;

/**
 * Created by Kent_Zheng on 2015/5/26.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class GpsFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";

    public GpsFragment() {
        // Empty constructor required for fragment subclasses
    }

    public static Fragment newInstance(int position) {
        Fragment fragment = new GpsFragment();
        Bundle args = new Bundle();
        args.putInt(GpsFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_planet, container, false);

        int imageId = getResources().getIdentifier("gps",
                "drawable", getActivity().getPackageName());
        ImageView iv = ((ImageView) rootView.findViewById(R.id.image));
        iv.setImageResource(imageId);

        getActivity().setTitle("Gps");
        return rootView;
    }
}
