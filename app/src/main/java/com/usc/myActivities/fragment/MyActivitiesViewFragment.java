package com.usc.myActivities.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.usc.myActivities.R;
import com.usc.myActivities.activity.MyActivitiesActivity;
import com.usc.myActivities.activity.MyActivitiesListActivity;
import com.usc.myActivities.lab.MyActivitiesLab;
import com.usc.myActivities.model.MyActivity;

import java.util.UUID;

import static android.app.Activity.RESULT_OK;


public class MyActivitiesViewFragment extends Fragment {

    private MyActivity myActivities;

    private TextView mTitleField,mDateField,mDestinationField,mDurationField,mCommentField,mLocationField,mTypeField;
    private ImageView mImageView;
    private Button mCancelBtn,mMapBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String activityID = "";

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            activityID = bundle.getString(MyActivitiesActivity.EXTRA_TRIP_ID, "");
        }
        myActivities = MyActivitiesLab.get(getActivity()).getActivities(UUID.fromString(activityID));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_view,container,false);


        mTitleField = (TextView) view.findViewById(R.id.title);
        mDateField = (TextView) view.findViewById(R.id.date);
        mDestinationField = (TextView) view.findViewById(R.id.destination);
        mDurationField = (TextView) view.findViewById(R.id.duration);
        mCommentField = (TextView) view.findViewById(R.id.comment);
        mLocationField = (TextView) view.findViewById(R.id.location);
        mTypeField = (TextView) view.findViewById(R.id.type);

        mTitleField.setText(myActivities.getTitle());
        mDateField.setText(myActivities.getDate());
        mDestinationField.setText(myActivities.getDestination());
        mDurationField.setText(myActivities.getDuration());
        mCommentField.setText(myActivities.getComment());
        mLocationField.setText(myActivities.getLocation());
        mTypeField.setText(myActivities.getType());

        mImageView = (ImageView) view.findViewById(R.id.imageView);
        mCancelBtn = (Button) view.findViewById(R.id.btn_cancel);
        mMapBtn = (Button) view.findViewById(R.id.btn_map);

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyActivitiesLab.get(getActivity()).deleteTrip(myActivities);
                startActivity(MyActivitiesListActivity.newIntent(getActivity()));
            }
        });

        mMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                MyActivitiesViewFragment myFragment = new MyActivitiesViewFragment();

                Bundle bundle = new Bundle();
                bundle.putString(MyActivitiesActivity.EXTRA_TRIP_ID, myActivities.getId().toString());
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();


            }
        });

        return view;

    }

}
