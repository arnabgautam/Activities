package com.usc.myActivities.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.usc.myActivities.db.MyActivitiesLoggerDbSchema;
import com.usc.myActivities.model.MyActivity;

import java.util.Date;
import java.util.UUID;


public class MyActivitiesCursorWrapper extends CursorWrapper {

    public MyActivitiesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public MyActivity getActivity(){

        String uuidString  = getString(getColumnIndex(MyActivitiesLoggerDbSchema.ActivityTable.Cols.UUID));
        String title = getString(getColumnIndex(MyActivitiesLoggerDbSchema.ActivityTable.Cols.TITLE));
        long date = getLong(getColumnIndex(MyActivitiesLoggerDbSchema.ActivityTable.Cols.DATE));
        String destination = getString(getColumnIndex(MyActivitiesLoggerDbSchema.ActivityTable.Cols.DESTINATION));
        String comment = getString(getColumnIndex(MyActivitiesLoggerDbSchema.ActivityTable.Cols.COMMENT));
        String duration = getString(getColumnIndex(MyActivitiesLoggerDbSchema.ActivityTable.Cols.DURATION));
        String location = getString(getColumnIndex(MyActivitiesLoggerDbSchema.ActivityTable.Cols.LOCATION));
        String type = getString(getColumnIndex(MyActivitiesLoggerDbSchema.ActivityTable.Cols.TYPE));

        MyActivity activity = new MyActivity(UUID.fromString(uuidString));
        activity.setTitle(title);
        activity.setDestination(destination);
        activity.setDuration(duration);
        activity.setComment(comment);
        activity.setLocation(location);
        activity.setType(type);
        activity.setDate(new Date(date).toString());
        return activity;

    }

}
