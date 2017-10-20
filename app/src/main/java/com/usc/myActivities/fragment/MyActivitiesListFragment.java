package com.usc.myActivities.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.usc.myActivities.activity.MyActivitiesActivity;
import com.usc.myActivities.adapter.MyActivitiesAdapter;
import com.usc.myActivities.lab.MyActivitiesLab;
import com.usc.myActivities.R;
import com.usc.myActivities.activity.SettingsActivity;
import com.usc.myActivities.model.MyActivity;

import java.util.List;

public class MyActivitiesListFragment extends Fragment {

    private RecyclerView mTripRecyclerView;
    private MyActivitiesAdapter mAdapter;

    private Button mLogButton;
    private Button mSettingsButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_list,container,false);

        mTripRecyclerView = (RecyclerView) view.findViewById(R.id.tripList);
        mTripRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mLogButton = (Button) view.findViewById(R.id.btn_log);
        mSettingsButton = (Button) view.findViewById(R.id.btn_settings);

        mLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyActivity a = new MyActivity();
                MyActivitiesLab.get(getActivity()).addActivity(a);
                Intent intent = MyActivitiesActivity.newIntent(getActivity(), a.getId());
                startActivity(intent);

            }
        });

        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = SettingsActivity.newIntent(getActivity());
                startActivity(intent);

            }
        });

        updateUI();

        return view;

    }

    private void updateUI() {
        MyActivitiesLab triplab = MyActivitiesLab.get(getActivity());
        List<MyActivity> trips = triplab.getActivities();

        if(mAdapter == null){
            mAdapter = new MyActivitiesAdapter(getActivity(),trips);
            mTripRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTrips(trips);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

}
