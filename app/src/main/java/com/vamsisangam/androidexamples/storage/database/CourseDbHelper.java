package com.vamsisangam.androidexamples.storage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.vamsisangam.androidexamples.App.*;
/**
 * Created by Vamsi on 07-06-2017.
 */

public class CourseDbHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "androidexamples";
    private final static int DB_VERSION = 1;
    public final static String TABLE_NAME = "courses";
    public final static String COL_ID = "_id";
    public final static String COL_NAME = "name";
    public final static String COL_DURATION = "duration";
    public final static String COL_FEE = "fee";
    private final static String CREATE_TABLE_SQL =
            "create table " + TABLE_NAME + " ( "
                    + COL_ID + " integer primary key autoincrement,"
                    + COL_NAME + " text,"
                    + COL_FEE + " integer,"
                    + COL_DURATION + " integer"
            + ")";

    public CourseDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        log("onCreate() database");
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // take some action like dropping or adding
        // some tables while a database upgrade.
        log("onUpgrade() database");
    }
}
