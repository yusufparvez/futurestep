package com.example.yusuf.futurestep;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yusuf.futurestep.data.User;
import com.example.yusuf.futurestep.data.UserDBHelper;

import static com.example.yusuf.futurestep.data.UserContract.UserEntry.COLUMN_USER_MOBILE;
import static com.example.yusuf.futurestep.data.UserContract.UserEntry.COLUMN_USER_PASSWORD;
import static com.example.yusuf.futurestep.data.UserContract.UserEntry.COLUMN_USER_USERNAME;
import static com.example.yusuf.futurestep.data.UserContract.UserEntry.TABLE_NAME;
import static com.example.yusuf.futurestep.data.UserContract.UserEntry._ID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView signUpTextView = (TextView) findViewById(R.id.signup_text_view);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);startActivity(i);
            }
        });

        final EditText usernameEditText = (EditText) findViewById(R.id.lUsername_edit_text);
        final EditText passwordEditText = (EditText) findViewById(R.id.lPassword_edit_text);
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameString = usernameEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();
                Log.d("donkey", "Inside OnCLick");

                if (authenticate(usernameString, passwordString)) {
                    Toast.makeText(MainActivity.this, "Sign in Sucessful", Toast.LENGTH_LONG).show();
                    Intent j = new Intent(MainActivity.this, HomescreenActivity.class);
                    startActivity(j);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Wrong Id or Password!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean authenticate(String username, String password) {

        UserDBHelper myDb = new UserDBHelper(this);
        SQLiteDatabase db = myDb.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
//                new String[]{_ID, COLUMN_USER_USERNAME, COLUMN_USER_PASSWORD},//Selecting columns want to query
//                COLUMN_USER_USERNAME + "=?",
//                new String[]{username},//Where clause
//                null, null, null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                                        " WHERE TRIM(username) = '" + username.toLowerCase() + "'",
                                        null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            // if cursor has value then in user database there is
            // user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (password.equalsIgnoreCase(user1.getPassword())) {
                cursor.close();
                return true;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return false;
    }
}
