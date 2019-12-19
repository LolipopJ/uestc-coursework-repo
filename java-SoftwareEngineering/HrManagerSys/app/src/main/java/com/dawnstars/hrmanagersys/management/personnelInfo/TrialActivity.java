package com.dawnstars.hrmanagersys.management.personnelInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dawnstars.hrmanagersys.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrialActivity extends Activity {
    private List<String> staffs = new ArrayList<>();
    private final String[] setNames = {"邓聪", "周直臻", "宋璟珅", "Sekiro", "不愿透露姓名的大学生", "Android开发真难",
            "如蜜传如蜜", "缺哥哥", "韦天魔术棒"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        // 初始化界面变量
        for (String name : setNames) {
            staffs.add(name);
        }
        initStaffData();
    }

    public void trialActivityReturn(View view) {
        finish();
    }

    private void initStaffData() {
        ListView lv = findViewById(R.id.trail_staff_list);

        lv.setAdapter(new ArrayAdapter<>(
                TrialActivity.this,
                android.R.layout.simple_list_item_1,
                staffs
        ));

        // 点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String staffName = staffs.get(position);
                showOptionDialog(staffName);
            }
        });
    }

    public void showOptionDialog(final String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("试用员工");
        builder.setMessage("确认试用 "+name+" 吗？");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /* 数据库操作 */

                // 显示结果
                Toast.makeText(TrialActivity.this, "已将 "+ name +" 设置为试用员工",
                        Toast.LENGTH_LONG).show();
            }
        }).create();

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        AlertDialog ad = builder.create();
        ad.show();
    }
}
