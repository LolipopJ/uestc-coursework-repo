package com.dawnstars.hrmanagersys.management.personnelInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dawnstars.hrmanagersys.R;

public class AttendanceProjectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_project);
    }

    public void addAttendanceProjectActivity(View view) {
        Intent intent = new Intent(this, AddAttendanceProjectActivity.class);
        startActivity(intent);
    }

    public void attendanceProjectReturn(View view) {
        finish();
    }
}
