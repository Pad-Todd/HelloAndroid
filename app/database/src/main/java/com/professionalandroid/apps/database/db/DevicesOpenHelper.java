package com.professionalandroid.apps.database.db;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;


import com.professionalandroid.apps.database.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DevicesOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = DevicesOpenHelper.class.getSimpleName();
    private static final int SCHEMA_VERSION = 1;
    private static final String DB_NAME = "devices.db";
    private final Context context;
    private static DevicesOpenHelper instance;

    public synchronized static DevicesOpenHelper getInstance(Context context) {
        if(instance == null)
            instance = new DevicesOpenHelper(context.getApplicationContext());
        return instance;
    }

    private DevicesOpenHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA_VERSION);
        this.context = context;

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            SQLiteDatabase db = getWritableDatabase();
            db.enableWriteAheadLogging();
            db.execSQL("PRAGMA foregin_keys = ON");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(int i = 1; i <= SCHEMA_VERSION; i++) {
            applySqlFile(db,i);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(int i = (oldVersion + 1); i <= newVersion; i++) {
            applySqlFile(db, i);
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        setWriteAheadLoggingEnabled(true);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void applySqlFile(SQLiteDatabase db, int version) {
        BufferedReader reader = null;

        try{
            String filename = String.format("%s.%d.sql", DB_NAME, version);
            final InputStream inputStream = context.getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(inputStream));

            final StringBuilder statement = new StringBuilder();

            for (String line; (line = reader.readLine()) != null; ) {
                if(BuildConfig.DEBUG) {
                    Log.d(TAG, "Reading line -> " + line);
                }
                if(!TextUtils.isEmpty(line) && !line.startsWith("--")) {
                    statement.append(line.trim());
                }

                if(line.endsWith(";")) {
                    if(BuildConfig.DEBUG) {
                        Log.d(TAG, "Running statement " + statement);
                    }
                    db.execSQL(statement.toString());
                    statement.setLength(0);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Could not apply SQL file", e);
        } finally {
            if(reader != null) {
                try{
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
