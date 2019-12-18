package com.dawnstars.hrmanagersys.management.personnelInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dawnstars.hrmanagersys.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddAttendanceProjectActivity extends Activity implements View.OnClickListener{
    private TextView textDate;
    private TextView textTimeStart;
    private TextView textTimeEnd;
    private TextView textProjectName;
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance_project);

        textDate = findViewById(R.id.selected_date);
        textTimeStart = findViewById(R.id.selected_timeStart);
        textTimeEnd = findViewById(R.id.selected_timeEnd);
        textProjectName = findViewById(R.id.editText_projectName);

        textDate.setText(calendar.get(Calendar.YEAR)+" - "+(calendar.get(Calendar.MONTH)+1)+" - "
                +calendar.get(Calendar.DAY_OF_MONTH));
        textTimeStart.setText("0 : 0");
        textTimeEnd.setText("23 : 59");

        textDate.setOnClickListener(this);
        textTimeStart.setOnClickListener(this);
        textTimeEnd.setOnClickListener(this);
    }

    public void showDatePickerDialog(Activity activity, final TextView tv, Calendar calendar) {
        DatePickerDialog dpd = new DatePickerDialog(activity, 0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear += 1;
                String monthOfYearText;
                String dayOfMonthText;
                if(monthOfYear < 10) {
                    monthOfYearText = "0"+monthOfYear;
                } else monthOfYearText = Integer.toString(monthOfYear);
                if(dayOfMonth < 10) {
                    dayOfMonthText = "0"+dayOfMonth;
                } else dayOfMonthText = Integer.toString(dayOfMonth);

                tv.setText(year+" - "+monthOfYearText+" - "+dayOfMonthText);
            }
        }
                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        // 设置不能选择当前日期以前的日期
        DatePicker dp = dpd.getDatePicker();
        dp.setMinDate(new Date().getTime());

        dpd.show();
    }

    public void showTimePickerDialogStart(Activity activity, final TextView tvStart, Calendar calendar, final TextView tvEnd) {
        new TimePickerDialog(activity, 0,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hourOfDayText;
                        String minuteText;
                        if(hourOfDay < 10) {
                            hourOfDayText = "0"+hourOfDay;
                        } else hourOfDayText = Integer.toString(hourOfDay);
                        if(minute < 10) {
                            minuteText = "0"+minute;
                        } else minuteText = Integer.toString(minute);

                        String selectedTimeText = hourOfDayText+" : "+minuteText;

                        // 判断选取的结束时间是否在开始时间之后
                        if(textTimeEnd.getText().toString().compareTo(selectedTimeText) < 0) {
                            showDialog("开始时间应该在结束时间之前");
                        }
                        else tvStart.setText(hourOfDayText+" : "+minuteText);
                    }
                }
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                ,true).show();
    }

    public void showTimePickerDialogEnd(Activity activity, final TextView tvEnd, Calendar calendar, final TextView tvStart) {
        new TimePickerDialog(activity, 0,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hourOfDayText;
                        String minuteText;
                        if(hourOfDay < 10) {
                            hourOfDayText = "0"+hourOfDay;
                        } else hourOfDayText = Integer.toString(hourOfDay);
                        if(minute < 10) {
                            minuteText = "0"+minute;
                        } else minuteText = Integer.toString(minute);

                        String selectedTimeText = hourOfDayText+" : "+minuteText;

                        // 判断选取的结束时间是否在开始时间之后
                        if(textTimeStart.getText().toString().compareTo(selectedTimeText) > 0) {
                            showDialog("结束时间应该在开始时间之后");
                        }
                        else tvEnd.setText(selectedTimeText);
                    }
                }
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                ,true).show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selected_date:
                showDatePickerDialog(this, textDate, calendar);
                break;
            case R.id.selected_timeStart:
                showTimePickerDialogStart(this, textTimeStart, calendar, textTimeEnd);
                break;
            case R.id.selected_timeEnd:
                showTimePickerDialogEnd(this, textTimeEnd, calendar, textTimeStart);
                break;
            default:
                break;
        }
    }

    public void showDialog(String words) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage(words);
        AlertDialog ad = builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        }).create();
        ad.show();
    }

    public void showOptionDialog(String words) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage(words);
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create();
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        AlertDialog ad = builder.create();
        ad.show();
    }

    public void addAttendanceProject(View view) {
        /* 将内容保存至数据库中 */
        finish();
    }

    public void addAttendanceProjectReturn(View view) {
        showOptionDialog("退出添加考勤项目信息页面而不保存？");
    }
}
