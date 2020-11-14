package com.coursework.lifecycletest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "LifeCycleTest";
    static final String TAG_MSG_OWNER = "MainActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG, TAG_MSG_OWNER + "onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, TAG_MSG_OWNER + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, TAG_MSG_OWNER + "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, TAG_MSG_OWNER + "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, TAG_MSG_OWNER + "onStop");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(TAG, TAG_MSG_OWNER + "onRestart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG_MSG_OWNER + "onDestroy");
    }
}