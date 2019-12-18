package com.dawnstars.hrmanagersys.management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dawnstars.hrmanagersys.R;
import com.dawnstars.hrmanagersys.management.personnelInfo.AttendanceProjectActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void attendanceProjectActivity(View view) {
        Intent intent = new Intent(this, AttendanceProjectActivity.class);
        startActivity(intent);
    }
}
