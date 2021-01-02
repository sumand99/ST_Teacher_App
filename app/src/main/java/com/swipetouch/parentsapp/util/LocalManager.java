package com.swipetouch.parentsapp.util;


import com.swipetouch.parentsapp.response.Student;
import com.swipetouch.parentsapp.response.StudentAttendance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalManager {



    private static  LocalManager localManager;


    public static LocalManager getLocalManager() {
        return localManager;
    }
    public static synchronized  LocalManager getInstance() {
        if (localManager == null) {
            localManager = new  LocalManager();
        }
        return localManager;
    }


    public Map<Integer, String> getAbsentMap() {
        return absentMap;
    }

    public void setAbsentMap(Map<Integer, String> absentMap) {
        this.absentMap = absentMap;
    }

    private Map<Integer,String>absentMap = new HashMap<>();


    public List<Student> getAttendList() {
        return attendList;
    }

    public void setAttendList(List<Student> attendList) {
        this.attendList = attendList;
    }

    List<Student> attendList;

    public StudentAttendance getStudentAttendance() {
        return studentAttendance;
    }

    public void setStudentAttendance(StudentAttendance studentAttendance) {
        this.studentAttendance = studentAttendance;
    }

    StudentAttendance studentAttendance;

}
