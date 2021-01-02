package com.swipetouch.parentsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swipetouch.parentsapp.R;
import com.swipetouch.parentsapp.adapter.AbsentAdapter;
import com.swipetouch.parentsapp.adapter.AttendanceAdapter;
import com.swipetouch.parentsapp.response.Student;
import com.swipetouch.parentsapp.response.StudentAttendance;
import com.swipetouch.parentsapp.util.LocalManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import life.sabujak.roundedbutton.RoundedButton;

public class SubmittedAttendActivity extends AppCompatActivity implements View.OnClickListener{

    RoundedButton submitAttend, editAttend;
    RecyclerView attendantRecycler;
    List<Student> attendList = new ArrayList<>();
    List<Student> absentList = new ArrayList<>();
    Map<Integer, String> absentMap;
    StudentAttendance studentAttendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitted);
        attendList = LocalManager.getInstance().getAttendList();
        absentMap = LocalManager.getInstance().getAbsentMap();
        studentAttendance = LocalManager.getInstance().getStudentAttendance();
        //getAttendList();

        for (Map.Entry<Integer, String> entry : absentMap.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            if(value.equals("absent")){
                absentList.add(attendList.get(key));


            }

        }

        initUi();
    }

    private void initUi() {

        editAttend= (RoundedButton)findViewById(R.id.editAttend);
        editAttend.setOnClickListener(this);


        RecyclerView attendantRecycler = (RecyclerView)findViewById(R.id.attendantRecycler);
        attendantRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AbsentAdapter adapter = new AbsentAdapter(this, absentList);
        attendantRecycler.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){
            case R.id.editAttend:
                finish();


                break;
        }
    }
}
