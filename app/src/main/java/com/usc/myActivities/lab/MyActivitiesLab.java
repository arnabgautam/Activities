package com.usc.myActivities.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.usc.myActivities.db.MyActivitiesLoggerDbSchema;
import com.usc.myActivities.wrappers.MyActivitiesCursorWrapper;
import com.usc.myActivities.db.MyActivitiesLoggerHelper;
import com.usc.myActivities.model.MyActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyActivitiesLab {

    private static MyActivitiesLab sMyActivitiesLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private List<MyActivity> mMyActivities;

    public static MyActivitiesLab get(Context context){
        if(sMyActivitiesLab == null){
            sMyActivitiesLab = new MyActivitiesLab(context);
        }
        return sMyActivitiesLab;
    }

    private MyActivitiesLab(Context context){

        mContext = context.getApplicationContext();
        mDatabase = new MyActivitiesLoggerHelper(mContext).getWritableDatabase();

    }

    public MyActivity addActivity(MyActivity t){
        ContentValues values = getContentValues(t);

        mDatabase.insert(MyActivitiesLoggerDbSchema.ActivityTable.NAME,null,values);
        return t;
    }

    public List<MyActivity> getActivities(){
        List<MyActivity> activities = new ArrayList<>();
        MyActivitiesCursorWrapper cursor = queryActivity(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                activities.add(cursor.getActivity());
                cursor.moveToNext();
            }
        } finally {

        }

        return activities;

    }

    public MyActivity getActivities(UUID id){

        MyActivitiesCursorWrapper cursor = queryActivity(MyActivitiesLoggerDbSchema.ActivityTable.Cols.UUID + " =?",new String[]{ id.toString() });

        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getActivity();

        } finally {
            cursor.close();
        }

    }

    public void updateTrip(MyActivity t){

        String uuidString = t.getId().toString();
        ContentValues values = getContentValues(t);

        mDatabase.update(MyActivitiesLoggerDbSchema.ActivityTable.NAME, values, MyActivitiesLoggerDbSchema.ActivityTable.Cols.UUID + " =?", new String[] { uuidString });

    }

    public void deleteTrip(MyActivity t){
        String uuidString = t.getId().toString();
        mDatabase.delete(MyActivitiesLoggerDbSchema.ActivityTable.NAME, MyActivitiesLoggerDbSchema.ActivityTable.Cols.UUID + " =?", new String[] { uuidString });
    }

    private static ContentValues getContentValues(MyActivity activities){

        ContentValues values = new ContentValues();
        values.put(MyActivitiesLoggerDbSchema.ActivityTable.Cols.UUID,activities.getId().toString());
        values.put(MyActivitiesLoggerDbSchema.ActivityTable.Cols.TITLE,activities.getTitle());
        values.put(MyActivitiesLoggerDbSchema.ActivityTable.Cols.COMMENT,activities.getComment());
        values.put(MyActivitiesLoggerDbSchema.ActivityTable.Cols.DURATION,activities.getDuration());
        values.put(MyActivitiesLoggerDbSchema.ActivityTable.Cols.LOCATION,activities.getLocation());
        values.put(MyActivitiesLoggerDbSchema.ActivityTable.Cols.TYPE,activities.getType());
        values.put(MyActivitiesLoggerDbSchema.ActivityTable.Cols.DATE,activities.getDate());
        values.put(MyActivitiesLoggerDbSchema.ActivityTable.Cols.DESTINATION,activities.getDestination());

        return values;
    }

    private MyActivitiesCursorWrapper queryActivity(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                MyActivitiesLoggerDbSchema.ActivityTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new MyActivitiesCursorWrapper(cursor);
    }

}
