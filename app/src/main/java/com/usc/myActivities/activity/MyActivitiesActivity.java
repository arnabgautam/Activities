package com.usc.myActivities.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.usc.myActivities.R;
import com.usc.myActivities.fragment.MyActivitiesFragment;

import java.util.UUID;

public class MyActivitiesActivity extends AppCompatActivity {

    public static final String EXTRA_TRIP_ID = "myActivities.activity id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_activity);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null){
            fragment = new MyActivitiesFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }

    }

    public static Intent newIntent(Context packageContent, UUID tripID){

        Intent intent = new Intent(packageContent, MyActivitiesActivity.class);
        intent.putExtra(EXTRA_TRIP_ID, tripID);
        return intent;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
