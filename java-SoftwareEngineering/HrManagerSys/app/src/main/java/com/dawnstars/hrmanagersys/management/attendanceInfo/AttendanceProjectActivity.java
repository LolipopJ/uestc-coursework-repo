package com.dawnstars.hrmanagersys.management.attendanceInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dawnstars.hrmanagersys.R;
import com.dawnstars.hrmanagersys.data.model.Attendance;

import java.util.ArrayList;

public class AttendanceProjectActivity extends Activity {
    public ArrayList<Attendance> ALAttendances = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_project);
    }

    public void addAttendanceProjectActivity(View view) {
        /* 没有链接数据库，重置属性防止应用崩溃 */
        ALAttendances = new ArrayList<>();

        Intent intent = new Intent(this, AddAttendanceProjectActivity.class);
        startActivityForResult(intent, 0);
    }

    public void attendanceProjectReturn(View view) {
        finish();
    }

    private void addAttendanceProjectData(ArrayList<Attendance> attendances) {
        ListView lv = findViewById(R.id.project_list);

        String[] project = new String[attendances.size()];
        int i = 0;
        for (Attendance attendance : attendances) {
            project[i] = attendance.getAttendanceProjectName()+"       "+attendance.getAttendanceDate();
        }

        lv.setAdapter(new ArrayAdapter<>(
                AttendanceProjectActivity.this,
                android.R.layout.simple_list_item_1,
                project
        ));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            /* 刷新页面获取数据库存放的项目资料 */

            // 本地化操作
            String projectName = data.getStringExtra("projectName");
            String date = data.getStringExtra("date");
            String timeStart = data.getStringExtra("timeStart");
            String timeEnd = data.getStringExtra("timeEnd");
            Toast.makeText(getApplicationContext(), "建立考勤项目 "+projectName, Toast.LENGTH_LONG).show();

            Attendance attendance = new Attendance(projectName, date, timeStart, timeEnd);
            ALAttendances.add(attendance);

            addAttendanceProjectData(ALAttendances);
        }
    }
}
