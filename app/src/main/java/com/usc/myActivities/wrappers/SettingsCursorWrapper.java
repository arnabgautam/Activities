package com.usc.myActivities.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.usc.myActivities.db.MyActivitiesLoggerDbSchema;
import com.usc.myActivities.model.Settings;

import java.util.UUID;


public class SettingsCursorWrapper extends CursorWrapper {

    public SettingsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Settings getSetting(){

        String uuidString  = getString(getColumnIndex(MyActivitiesLoggerDbSchema.SettingsTable.Cols.UUID));
        String name = getString(getColumnIndex(MyActivitiesLoggerDbSchema.SettingsTable.Cols.NAME));
        String id = getString(getColumnIndex(MyActivitiesLoggerDbSchema.SettingsTable.Cols.ID));
        String email = getString(getColumnIndex(MyActivitiesLoggerDbSchema.SettingsTable.Cols.EMAIL));
        String gender = getString(getColumnIndex(MyActivitiesLoggerDbSchema.SettingsTable.Cols.GENDER));
        String comment = getString(getColumnIndex(MyActivitiesLoggerDbSchema.SettingsTable.Cols.COMMENT));

        Settings setting = new Settings(UUID.fromString(uuidString));
        setting.setName(name);
        setting.setId(id);
        setting.setEmail(email);
        setting.setGender(gender);
        setting.setComment(comment);
        return setting;

    }

}
