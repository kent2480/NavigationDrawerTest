package com.example.android.navigationdrawer;

/**
 * Created by Kent_Zheng on 2015/5/26.
 */

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;


public class GpsFragment extends Fragment implements LocationListener {

    private static final String TAG = "GpsFragment";
    private RadioGroup mRadioGroup;
    private static String PROVIDER = LocationManager.GPS_PROVIDER;
    private LocationManager mLocationManager = null;
    private Location mLocation = null;
    private EditText input_time, input_dis;
    private TextView out_lon, out_lat, out_alt, out_bea, out_acc;
    private Button start, stop, tcxoStart, tcxoStop;

    private int time, dis;
    private Context ctx;
    private View mView;
    private final String SD_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String FILE_PATH = "/AppGps";
    public static final String FILE_NAME = "appGps";
    public File outFile, mFilePath;
    public BufferedWriter fileos;
    private Timer timer = new Timer(true);

    public GpsFragment() {
        // Empty constructor required for fragment subclasses
    }

    public static Fragment newInstance() {
        Fragment fragment = new GpsFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gps, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mView = getView();
        ctx = mView.getContext();
        mLocationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);

        initView();

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radiobtn_gps:
                        PROVIDER = LocationManager.GPS_PROVIDER;
                        break;

                    case R.id.radiobtn_network:
                        PROVIDER = LocationManager.NETWORK_PROVIDER;
                        break;

                    case R.id.radiobtn_passive:
                        PROVIDER = LocationManager.PASSIVE_PROVIDER;
                        break;
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG, "Start GPS");
                start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG, "Stop GPS");
                stop();
            }
        });

        tcxoStart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG, "tcxo Start");
                getActivity().startService(new Intent(getActivity(), TcxoService.class));

            }
        });

        tcxoStop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG, "tcxo Stop");
                getActivity().stopService(new Intent(getActivity(), TcxoService.class));
            }
        });

    }
    public void initView(){
        mRadioGroup = (RadioGroup) mView.findViewById(R.id.radiogroup);
        input_time = (EditText) mView.findViewById(R.id.input_time);
        input_dis = (EditText) mView.findViewById(R.id.input_dis);
        out_lon = (TextView) mView.findViewById(R.id.out_lon);
        out_lat = (TextView) mView.findViewById(R.id.out_lat);
        out_alt = (TextView) mView.findViewById(R.id.out_alt);
        out_bea = (TextView) mView.findViewById(R.id.out_bea);
        out_acc = (TextView) mView.findViewById(R.id.out_acc);
        start = (Button) mView.findViewById(R.id.start);
        stop = (Button) mView.findViewById(R.id.stop);
        tcxoStart = (Button) mView.findViewById(R.id.tcxo_start);
        tcxoStop = (Button) mView.findViewById(R.id.tcxo_stop);

        mFilePath = new File(SD_PATH + FILE_PATH);
        if(!mFilePath.exists()){
            mFilePath.mkdirs();
        }
        outFile = new File(SD_PATH + FILE_PATH + "/" + FILE_NAME);
        if(outFile.exists()){
            outFile.delete();
        }
    }

    public void start(){
        if(input_time.getText() != null){
            time = Integer.parseInt(input_time.getText().toString());

        }

        if(input_dis.getText() != null){
            dis = Integer.parseInt(input_dis.getText().toString());
        }

        if(time < 0) time = 0;
        if(dis < 0) dis = 0;

        Log.d(TAG, "PROVIDER = " + PROVIDER + ", Time = " + time + ", Dis = " + dis);

        mLocationManager.requestLocationUpdates(PROVIDER, time, dis, GpsFragment.this);
        //mLocationManager.requestLocationUpdates("fused", time, dis, GpsFragement.this);
        //mLocationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, GpsFragement.this, null);
    }

    public void stop(){
        mLocationManager.removeUpdates(GpsFragment.this);
        if(fileos != null){
            try {
                fileos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
        if(mLocationManager != null){
            mLocationManager.removeUpdates(GpsFragment.this);
        }
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        if(mLocationManager != null){
            mLocationManager.removeUpdates(GpsFragment.this);
        }
    }

    public void onLocationChanged(Location location) {
        mLocation = location;

        Log.d(TAG, "Longitude = " + mLocation.getLongitude() + "\n");
        Log.d(TAG, "Latitude = " + mLocation.getLatitude() + "\n");
        Log.d(TAG, "Accuracy = " + mLocation.getAccuracy() + "\n");
        Log.d(TAG, "Altitude = " + mLocation.getAltitude() + "\n");
        Log.d(TAG, "Time = " + mLocation.getTime() + "\n");
        Log.d(TAG, "Speed = " + mLocation.getSpeed() + "\n");
        Log.d(TAG, "Bearing = " + mLocation.getBearing() + "\n");

        out_lon.setText(String.valueOf(mLocation.getLongitude()));
        out_lat.setText(String.valueOf(mLocation.getLatitude()));
        out_alt.setText(String.valueOf(mLocation.getAltitude()));
        out_bea.setText(String.valueOf(mLocation.getBearing()));
        out_acc.setText(String.valueOf(mLocation.getAccuracy()));

        try {
            fileos = new BufferedWriter(new FileWriter(outFile, true), 8196);
            //fileos.write(mLocation.getLongitude());
            fileos.append("Longitude = " + mLocation.getLongitude() + ", ");
            fileos.append("Latitude = " + mLocation.getLatitude() + ", ");
            fileos.append("Accuracy = " + mLocation.getAltitude() + ", ");
            fileos.append("Bearing = " + mLocation.getBearing() + ", ");
            fileos.append("Accuracy = " + mLocation.getAccuracy() + "\n");

            fileos.flush();
            fileos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
}
