package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ReadWritePreferencesActivity extends Activity {
    private final String PREFS_FILENAME = "app_custom_user_prefs";
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write_preferences);
        init();
    }

    private void init() {
        username = (EditText) findViewById(R.id.readWritePrefsUsername);
        password = (EditText) findViewById(R.id.readWritePrefsPassword);
    }

    public void saveUserPrefs(View v) {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("username", username.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.commit();    // must commit changes

        Toast.makeText(this, "All changes saved!", Toast.LENGTH_LONG).show();
    }

    public void readUserPrefs(View v) {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE);
        username.setText(prefs.getString("username", ""));
        password.setText(prefs.getString("password", ""));

        Toast.makeText(this, "All available data read!", Toast.LENGTH_LONG).show();
    }
}
