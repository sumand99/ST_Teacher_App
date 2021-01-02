package com.swipetouch.parentsapp.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentAttendance {

    private  String  status;
    private  String  message;
    private  String  className;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSec_Id() {
        return sec_Id;
    }

    public void setSec_Id(String sec_Id) {
        this.sec_Id = sec_Id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(String attendance_date) {
        this.attendance_date = attendance_date;
    }

    public List<Student> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Student> attendance) {
        this.attendance = attendance;
    }

    private  String  sec_Id;
    private  String  period;
    private  String  attendance_date;

    private List<Student> attendance;


}
