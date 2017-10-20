package com.usc.myActivities.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.usc.myActivities.R;
import com.usc.myActivities.activity.MyActivitiesActivity;
import com.usc.myActivities.fragment.MyActivitiesViewFragment;
import com.usc.myActivities.lab.MyActivitiesLab;
import com.usc.myActivities.model.MyActivity;

import java.util.List;

public class MyActivitiesAdapter extends RecyclerView.Adapter<MyActivitiesAdapter.TripHolder> {

    private Context mContext;
    private List<MyActivity> mTrips;

    public MyActivitiesAdapter(Context context, List<MyActivity> trips){
        this.mContext = context;
        this.mTrips = trips;
    }

    @Override
    public TripHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.trip_item,parent,false);
        return new TripHolder(view);

    }

    @Override
    public void onBindViewHolder(TripHolder holder, int position) {
        final MyActivity myactivity = mTrips.get(position);
        holder.bindTrip(myactivity);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                MyActivitiesViewFragment myFragment = new MyActivitiesViewFragment();

                Bundle bundle = new Bundle();
                bundle.putString(MyActivitiesActivity.EXTRA_TRIP_ID, myactivity.getId().toString());
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });



    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }

    public class TripHolder extends RecyclerView.ViewHolder  {

        private MyActivity mTrip;

        private TextView txtTitle;
        private TextView txtDate;
        private TextView txtDesc;

        public TripHolder(final View itemView){
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.title);
            txtDate = (TextView) itemView.findViewById(R.id.date);
            txtDesc = (TextView) itemView.findViewById(R.id.desc);
        }

        public void bindTrip(MyActivity trip){
            mTrip = trip;
            txtTitle.setText(mTrip.getTitle());
            txtDate.setText(mTrip.getDate());
            txtDesc.setText(mTrip.getDestination());
        }

    }

    public void setTrips(List<MyActivity> trips){
        mTrips = trips;
    }

}
