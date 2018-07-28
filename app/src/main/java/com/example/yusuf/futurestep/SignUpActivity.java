package com.example.yusuf.futurestep;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.yusuf.futurestep.data.UserContract.UserEntry;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yusuf.futurestep.data.UserDBHelper;

public class SignUpActivity extends AppCompatActivity {

    /** EditText field to enter the pet's name */
    private EditText mUserNameEditText;

    /** EditText field to enter the pet's breed */
    private EditText mPasswordEditText;

    /** EditText field to enter the pet's weight */
    private EditText mNameEditText;

    /** EditText field to enter the pet's gender */
    private EditText mMobileEditText;


    private UserDBHelper mDbHelper;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUserNameEditText = (EditText) findViewById(R.id.username_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);
        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        mMobileEditText = (EditText) findViewById(R.id.mobile_edit_text);


    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView signUpTextView = (TextView) findViewById(R.id.signup_text_view);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        Button button = (Button) findViewById(R.id.signup_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUser();
            }
        });

    }

    private void insertUser() {

        mDbHelper = new UserDBHelper(this);
        // Gets the database in write mode
        db = mDbHelper.getWritableDatabase();
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String usernameString = mUserNameEditText.getText().toString().trim().toLowerCase();
        String passwordString = mPasswordEditText.getText().toString().trim();
        String nameString = mNameEditText.getText().toString().trim();
        String mobileString = mMobileEditText.getText().toString().trim();
        // Create database helper
        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();

        values.put(UserEntry.COLUMN_USER_USERNAME, usernameString);
        values.put(UserEntry.COLUMN_USER_PASSWORD, passwordString);
        values.put(UserEntry.COLUMN_USER_NAME, nameString);
        values.put(UserEntry.COLUMN_USER_MOBILE, mobileString);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(UserEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show();

        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Registration Successful: " +newRowId, Toast.LENGTH_SHORT).show();

        }
    }


/*
    public void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UserEntry._ID,
                UserEntry.COLUMN_USER_USERNAME,
                UserEntry.COLUMN_USER_PASSWORD,
                UserEntry.COLUMN_USER_NAME,
                UserEntry.COLUMN_USER_MOBILE };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                UserEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(UserEntry._ID + " - " +
                    UserEntry.COLUMN_USER_USERNAME + " - " +
                    UserEntry.COLUMN_USER_PASSWORD + " - " +
                    UserEntry.COLUMN_USER_NAME + " - " +
                    UserEntry.COLUMN_USER_MOBILE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(UserEntry._ID);
            int usernameColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_USER_USERNAME);
            int passwordColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_USER_PASSWORD);
            int nameColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_USER_NAME);
            int mobileColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_USER_MOBILE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentUserName = cursor.getString(usernameColumnIndex);
                String currentPassword = cursor.getString(passwordColumnIndex);
                int currentName = cursor.getInt(nameColumnIndex);
                int currentMobile = cursor.getInt(mobileColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentUserName + " - " +
                        currentPassword + " - " +
                        currentName + " - " +
                        currentMobile));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
*/

}

