package com.usc.myActivities.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.UUID;


public class MyActivitiesLoggerHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "myActivites.db";

    public MyActivitiesLoggerHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + MyActivitiesLoggerDbSchema.ActivityTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                MyActivitiesLoggerDbSchema.ActivityTable.Cols.UUID + ", "+
                MyActivitiesLoggerDbSchema.ActivityTable.Cols.TITLE + ", "+
                MyActivitiesLoggerDbSchema.ActivityTable.Cols.DATE + ", "+
                MyActivitiesLoggerDbSchema.ActivityTable.Cols.DURATION + ", "+
                MyActivitiesLoggerDbSchema.ActivityTable.Cols.COMMENT + ", "+
                MyActivitiesLoggerDbSchema.ActivityTable.Cols.LOCATION + ", "+
                MyActivitiesLoggerDbSchema.ActivityTable.Cols.TYPE + ", "+
                MyActivitiesLoggerDbSchema.ActivityTable.Cols.DESTINATION +
            ")"
        );

        db.execSQL("create table " + MyActivitiesLoggerDbSchema.SettingsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.UUID + ", "+
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.NAME + ", "+
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.ID + ", "+
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.EMAIL + ", "+
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.GENDER + ", "+
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.COMMENT +
                ")"
        );

        db.execSQL("insert into " + MyActivitiesLoggerDbSchema.SettingsTable.NAME + "(" +
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.UUID + ", "+
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.NAME + ", "+
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.ID + ", "+
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.EMAIL + ", "+
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.GENDER + ", "+
                MyActivitiesLoggerDbSchema.SettingsTable.Cols.COMMENT +
                ") values ('"+ UUID.randomUUID()+"','Binod Pariyar','1105344','B_P054@student.usc.edu.au','Male','No Comments')"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
