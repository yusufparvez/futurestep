package com.example.yusuf.futurestep.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.EditText;
import com.example.yusuf.futurestep.data.UserContract.UserEntry;

public class UserDBHelper extends SQLiteOpenHelper {
    /*****
     /** EditText field to enter the pet's name */
    private EditText mUserNameEditText;

    /**
     * EditText field to enter the pet's breed
     */
    private EditText mPasswordEditText;

    /**
     * EditText field to enter the pet's weight
     */
    private EditText mNameEditText;

    /**
     * EditText field to enter the pet's gender
     */
    private EditText mMobileEditText;

    public static final String LOG_TAG = UserDBHelper.class.getSimpleName();

    /*Name of the database file */
    private static final String DATABASE_NAME = "futurestep.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;


    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_USERINFO_TABLE = "CREATE TABLE " + UserEntry.TABLE_NAME + " ("
                + UserEntry.COLUMN_USER_USERNAME + " TEXT PRIMARY KEY NOT NULL, "
                + UserEntry.COLUMN_USER_PASSWORD + " TEXT, "
                + UserEntry.COLUMN_USER_NAME + " TEXT NOT NULL, "
                + UserEntry.COLUMN_USER_MOBILE + " TEXT NOT NULL );";

        db.execSQL(SQL_CREATE_USERINFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
    //inserting in database

}

