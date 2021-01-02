package com.swipetouch.parentsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.swipetouch.parentsapp.R;
import com.swipetouch.parentsapp.adapter.AttendanceAdapter;
import com.swipetouch.parentsapp.network.DataFetcher;
import com.swipetouch.parentsapp.response.Student;
import com.swipetouch.parentsapp.response.StudentAttendance;
import com.swipetouch.parentsapp.util.AppController;
import com.swipetouch.parentsapp.util.LocalManager;
import com.swipetouch.parentsapp.util.UiConstants;
import com.swipetouch.parentsapp.util.UiUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import life.sabujak.roundedbutton.RoundedButton;

public class AttdendanceACtivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView attendantRecycler;
    List<Student>attendList = new ArrayList<>();
    AttendanceAdapter adapter;
    RoundedButton submitAttend;
    TextView heading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        //getAttendList();
        getAttendList2();
        initUi();
    }

    private void initUi() {

        heading= (TextView) findViewById(R.id.heading);
        attendantRecycler = (RecyclerView)findViewById(R.id.attendantRecycler);

        attendantRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new AttendanceAdapter(this, attendList);
        attendantRecycler.setAdapter(adapter);


        submitAttend = (RoundedButton)findViewById(R.id.submitAttend);

        submitAttend.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){
            case R.id.submitAttend:
                startActivity(new Intent(AttdendanceACtivity.this,SubmittedAttendActivity.class));

                break;
        }
    }

    private void getAttendList2() {

        DataFetcher.getAllStudentsAttend(AttdendanceACtivity.this, classListResponse(), StudentAttendance.class, updateErrorListener(), UiConstants.SERVER_IP+"ParentAppApi.php?Parameter=GetStudentAttendanceList&StudentId=12345&NoticeId=2-names", null);


    }

    public void getAttendList(){
        String url = UiConstants.SERVER_IP+"ParentAppApi.php?Parameter=GetStudentAttendanceList&StudentId=12345&NoticeId=2-names";

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("tagtagtagtagtagtag", response.toString());

                StudentAttendance attend = new Gson().fromJson(response.toString(), StudentAttendance.class);
                adapter.notifyStudent(attend.getAttendance());
                LocalManager.getInstance().setAttendList(attend.getAttendance());
                LocalManager.getInstance().setStudentAttendance(attend);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("tag", "Error: " + error.getMessage());
                Log.e("tag", "Site Info Error: " + error.getMessage());


            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("key", "Value");
                return headers;
            }
        };
        RequestQueue mRequestQueue = ((AppController)  getApplicationContext()).getQueue();
        req.setTag(url);
        mRequestQueue.add(req);
        //DataFetcher.getAllStudentsAttend(AttdendanceACtivity.this, classListResponse(), StudentAttendance.class, updateErrorListener(), UiConstants.SERVER_IP+"ParentAppApi.php?Parameter=GetStudentAttendanceList&StudentId=12345&NoticeId=2-names", null);


    }


    private Response.Listener<StudentAttendance> classListResponse() {
        return new Response.Listener<StudentAttendance>()  {

            @Override
            public void onResponse(StudentAttendance response)  {

                Log.d("StudentAttendanceSt ",new Gson().toJson(response));

                adapter.notifyStudent(response.getAttendance());
                LocalManager.getInstance().setAttendList(response.getAttendance());
                LocalManager.getInstance().setStudentAttendance(response);
                heading.setText("Attendance Entry For Class "+response.getClassName()+" Dated "+response.getAttendance_date());




            }
        };
    }


    private Response.ErrorListener updateErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                UiUtil.cancelProgressDialogue();
                //Log.d("errorr-111111-error",""+error.networkResponse.statusCode);
                //Toast.makeText(getActivity(), "Network error.Please try after some time",Toast.LENGTH_SHORT).show();
                //UiUtil.cancelProgressDialogue();
//                UiUtil.showErrorStatus(error, getActivity());
                //showErrorStatus(error);
                //UiUtil.getTwoButtonDialog(getActivity(), "", getString(R.string.request_not_processed), "OK", "").show();
            }
        };
    }

    public void clickedItem(int position, String status) {
        Log.d("statusstatusstatus",status);
        Map<Integer, String> absentMap = LocalManager.getInstance().getAbsentMap();
        if(absentMap == null){
            absentMap = new HashMap<>();
        }
        absentMap.put(position, status);
        LocalManager.getInstance().setAbsentMap(absentMap);
    }
}