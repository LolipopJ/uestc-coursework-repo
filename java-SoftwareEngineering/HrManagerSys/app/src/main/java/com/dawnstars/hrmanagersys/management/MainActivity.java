package com.dawnstars.hrmanagersys.management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dawnstars.hrmanagersys.R;
import com.dawnstars.hrmanagersys.management.personnelInfo.AttendanceProjectActivity;
import com.dawnstars.hrmanagersys.ui.login.LoginActivity;

public class MainActivity extends Activity {
    public Boolean loginStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void attendanceProjectActivity(View view) {
        Intent intent = new Intent(this, AttendanceProjectActivity.class);
        startActivity(intent);
    }

    public void loginActivity(View view) {
        if (!loginStatus) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, 0);
        }
        else Toast.makeText(getApplicationContext(), "您已登录!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            loginStatus = true;
        }
    }
}
