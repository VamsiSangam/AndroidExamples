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
    private final static int DB_VERSION = 2;
    public final static String TABLE_COURSES = "courses";
    public final static String COL_ID = "_id";
    public final static String COL_NAME = "name";
    public final static String COL_DURATION = "duration";
    public final static String COL_FEE = "fee";
    private final static String CREATE_COURSES_TABLE_SQL =
            "create table " + TABLE_COURSES + " ( "
                    + COL_ID + " integer primary key autoincrement,"
                    + COL_NAME + " text,"
                    + COL_FEE + " integer,"
                    + COL_DURATION + " integer"
            + ")";

    public final static String TABLE_TOPICS = "topics";
    public final static String COL_COURSE = "course";
    private final static String CREATE_TOPICS_TABLE_SQL =
            "create table " + TABLE_TOPICS + " ( "
                    + COL_ID + " integer primary key autoincrement,"
                    + COL_COURSE + " integer,"
                    + COL_NAME + " text,"
                    + COL_DURATION + " integer,"
                    + " foreign key (" + COL_COURSE + ") references " + TABLE_COURSES + "(" + COL_ID + ")"
            + ")";

    public CourseDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        log("onCreate() database");
        db.execSQL(CREATE_COURSES_TABLE_SQL);
        db.execSQL(CREATE_TOPICS_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // take some action like dropping or adding
        // some tables while a database upgrade.
        log("onUpgrade() database");
        db.execSQL(CREATE_TOPICS_TABLE_SQL);
    }
}
