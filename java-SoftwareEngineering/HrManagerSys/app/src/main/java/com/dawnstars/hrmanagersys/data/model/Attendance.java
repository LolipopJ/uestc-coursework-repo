package com.dawnstars.hrmanagersys.data.model;

import android.util.ArrayMap;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lolipop
 * @version 2019/12/18
 */
public class Attendance {
    // 考勤项目名
    private String attendanceProjectName;

    // 考勤情况
    private ArrayMap<Integer, String> attendanceInfo;

    // 考勤日期
    private String attendanceDate;

    // 设定考勤时间，在此期间考勤才算成功
    private String attendanceTimeStart;
    private String attendanceTimeEnd;

    public Attendance(String projectName, String date, String timeStart, String timeEnd) {
        this.attendanceProjectName = projectName;
        this.attendanceDate = date;

        this.attendanceTimeStart = timeStart;
        this.attendanceTimeEnd = timeEnd;

        this.attendanceInfo = new ArrayMap<>();
    }

    private void setAttendanceProjectName(String name) {
        this.attendanceProjectName = name;
    }

    private void setAttendanceDate(String date) {
        this.attendanceDate = date;
    }

    private void setAttendanceTimeStart(String timeStart) {
        this.attendanceTimeStart = timeStart;
    }

    private void setAttendanceTimeEnd(String timeEnd) {
        this.attendanceTimeEnd = timeEnd;
    }

    private void setAttendanceInfo(int staffId) {
        // 检验是否已经签到
        if (attendanceInfo.containsKey(staffId)) {
            // 提示已经签到成功
            return;
        }

        // 基准时间格式
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

        // 获取当前时间
        Date now = new Date();
        String nowDate = sdfDate.format(now);
        String nowTime = sdfTime.format(now);

        // 签到日期基准
        String attendanceDate = sdfDate.format(this.attendanceDate);

        // 判断日期
        if (!nowDate.equals(attendanceDate)) {
            // 提示不在指定日期
            return;
        }

        // 判断签到时间
        /*if (now.after(this.attendanceTimeEnd) || now.before(this.attendanceTimeStart)) {
            // 提示不在签到时间
            return;
        }*/

        // 添加考勤信息
        attendanceInfo.put(staffId, nowTime);
    }

    private String getAttendanceProjectName() {
        return this.attendanceProjectName;
    }

    private String getAttendanceDate() {
        return this.attendanceDate;
    }

    private String getAttendanceTimeStart() {
        return this.attendanceTimeStart;
    }

    private String getAttendanceTimeEnd() {
        return this.attendanceTimeEnd;
    }

    private Map<Integer, String> getAttendanceInfo() {
        return this.attendanceInfo;
    }
}
