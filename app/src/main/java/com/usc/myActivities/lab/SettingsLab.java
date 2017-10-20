package com.usc.myActivities.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.usc.myActivities.db.MyActivitiesLoggerDbSchema;
import com.usc.myActivities.model.Settings;
import com.usc.myActivities.wrappers.SettingsCursorWrapper;
import com.usc.myActivities.db.MyActivitiesLoggerHelper;

import java.util.ArrayList;
import java.util.List;

public class SettingsLab {

    private static SettingsLab sSettingsLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private List<Settings> mSettings;

    public static SettingsLab get(Context context){
        if(sSettingsLab == null){
            sSettingsLab = new SettingsLab(context);
        }
        return sSettingsLab;
    }

    private SettingsLab(Context context){

        mContext = context.getApplicationContext();
        mDatabase = new MyActivitiesLoggerHelper(mContext).getWritableDatabase();

    }

    public Settings getSettings(){
        List<Settings> settings = new ArrayList<>();
        SettingsCursorWrapper cursor = querySettings(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                settings.add(cursor.getSetting());
                cursor.moveToNext();
            }
        } finally {

        }

        return settings.get(0);

    }

    public void updateSettings(Settings s){

        String uuidString = s.getmId().toString();
        ContentValues values = getContentValues(s);

        mDatabase.update(MyActivitiesLoggerDbSchema.SettingsTable.NAME, values, MyActivitiesLoggerDbSchema.SettingsTable.Cols.UUID + " =?", new String[] { uuidString });

    }

    private static ContentValues getContentValues(Settings settings){

        ContentValues values = new ContentValues();
        values.put(MyActivitiesLoggerDbSchema.SettingsTable.Cols.UUID,settings.getmId().toString());
        values.put(MyActivitiesLoggerDbSchema.SettingsTable.Cols.NAME,settings.getName());
        values.put(MyActivitiesLoggerDbSchema.SettingsTable.Cols.ID,settings.getId());
        values.put(MyActivitiesLoggerDbSchema.SettingsTable.Cols.EMAIL,settings.getEmail());
        values.put(MyActivitiesLoggerDbSchema.SettingsTable.Cols.GENDER,settings.getGender());
        values.put(MyActivitiesLoggerDbSchema.SettingsTable.Cols.COMMENT,settings.getComment());

        return values;
    }

    private SettingsCursorWrapper querySettings(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                MyActivitiesLoggerDbSchema.SettingsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new SettingsCursorWrapper(cursor);
    }

}
