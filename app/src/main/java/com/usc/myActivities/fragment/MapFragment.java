package com.usc.myActivities.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.usc.myActivities.R;
import com.usc.myActivities.activity.MyActivitiesActivity;
import com.usc.myActivities.lab.MyActivitiesLab;
import com.usc.myActivities.lab.SettingsLab;
import com.usc.myActivities.model.MyActivity;
import com.usc.myActivities.model.Settings;

import java.util.UUID;


public class MapFragment extends Fragment {

    private MyActivity myActivities;
    GoogleMap googleMap;
    LatLng myPosition;

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
        View view = inflater.inflate(R.layout.fragment_map,container,false);



        return view;

    }


}
