package com.example.yusuf.futurestep.data;

import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class UserContract{

    private UserContract(){}


    public static final class UserEntry implements BaseColumns{

        /** Name of database table for pets */
        public final static String TABLE_NAME = "userinfo";

        /**
         * Unique ID number for the pet (only for use in the database table).
         *
         * Type: INTEGER
         */

        /**
         * Name of the user.
         *
         * Type: TEXT
         */
        public final static String COLUMN_USER_USERNAME = "username";

        /**
         * Breed of the pet.
         *
         * Type: TEXT
         */
        public final static String COLUMN_USER_PASSWORD = "password";


        public final static String COLUMN_USER_NAME = "name";
        /**

         *
         * Type: INTEGER
         */
        public final static String COLUMN_USER_MOBILE = "mobile";

        /**
         * Weight of the pet.
         *
         * Type: INTEGER
         */

    }

}
