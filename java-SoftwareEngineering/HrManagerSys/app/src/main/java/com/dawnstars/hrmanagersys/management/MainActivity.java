package com.dawnstars.hrmanagersys.management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dawnstars.hrmanagersys.R;
import com.dawnstars.hrmanagersys.management.attendanceInfo.AttendanceClassActivity;
import com.dawnstars.hrmanagersys.management.attendanceInfo.AttendanceProjectActivity;
import com.dawnstars.hrmanagersys.management.attendanceInfo.AttendanceSettingActivity;
import com.dawnstars.hrmanagersys.management.attendanceInfo.VacationSettingActivity;
import com.dawnstars.hrmanagersys.management.personnelInfo.*;
import com.dawnstars.hrmanagersys.ui.login.LoginActivity;

public class MainActivity extends Activity {
    public Boolean loginStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void attendanceProjectActivity(View view) {
        if (!loginStatus) loginActivity();
        else {
            Intent intent = new Intent(this, AttendanceProjectActivity.class);
            startActivity(intent);
        }
    }

    public void attendanceClassActivity(View view) {
        if (!loginStatus) loginActivity();
        else {
            Intent intent = new Intent(this, AttendanceClassActivity.class);
            startActivity(intent);
        }
    }

    public void attendanceSettingActivity(View view) {
        if (!loginStatus) loginActivity();
        else {
            Intent intent = new Intent(this, AttendanceSettingActivity.class);
            startActivity(intent);
        }
    }

    public void vacationSettingActivity(View view) {
        if (!loginStatus) loginActivity();
        else {
            Intent intent = new Intent(this, VacationSettingActivity.class);
            startActivity(intent);
        }
    }

    public void dismissActivity(View view) {
        if (!loginStatus) loginActivity();
        else {
            Intent intent = new Intent(this, DismissActivity.class);
            startActivity(intent);
        }
    }

    public void probationActivity(View view) {
        if (!loginStatus) loginActivity();
        else {
            Intent intent = new Intent(this, ProbationActivity.class);
            startActivity(intent);
        }
    }

    public void retireActivity(View view) {
        if (!loginStatus) loginActivity();
        else {
            Intent intent = new Intent(this, RetireActivity.class);
            startActivity(intent);
        }
    }

    public void trialActivity(View view) {
        if (!loginStatus) loginActivity();
        else {
            Intent intent = new Intent(this, TrialActivity.class);
            startActivity(intent);
        }
    }

    public void loginButton(View view) {
        if (!loginStatus) loginActivity();
        else Toast.makeText(getApplicationContext(), "您已登录!", Toast.LENGTH_LONG).show();
    }

    public void loginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            loginStatus = true;
        }
    }
}
