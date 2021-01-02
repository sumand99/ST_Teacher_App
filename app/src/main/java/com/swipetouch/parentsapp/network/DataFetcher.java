package com.swipetouch.parentsapp.network;

import android.content.Context;


import com.android.volley.Response;
import com.swipetouch.parentsapp.response.StudentAttendance;
import com.swipetouch.parentsapp.util.InternetDialog;

import java.lang.reflect.Type;
import java.util.List;

public class DataFetcher {



    public static void getAllStudentsAttend(Context context, Response.Listener<StudentAttendance> updateSuccessListener, Type repClass, Response.ErrorListener errorListener, String url, String token) {


        if(new InternetDialog(context).getInternetStatus(context)){

            HelperVolley.CallGetApi(context, url, url, updateSuccessListener, StudentAttendance.class, errorListener, token);
          }

    }



    public static void getAllSubjectNames(Context context, Response.Listener<List> updateSuccessListener, Type repClass, Response.ErrorListener errorListener, String url, String token) {


        if(new InternetDialog(context).getInternetStatus(context)){

            HelperVolley.CallGetApi(context, url, url, updateSuccessListener, List.class, errorListener, token);

        }


    }


/*
    public static void getAllLocationDetails(Context context, Response.Listener<GetAllLocationResponseList> updateSuccessListener, Type repClass, Response.ErrorListener errorListener, String url) {


        HelperVolley.CallGetApi(context, url, url, updateSuccessListener, GetAllLocationResponseList.class, errorListener);


    }


    public static void updateProfileInfo(Context context, String json, Response.Listener<GetAllLocationResponse> updateSuccessListener, Type repClass, Response.ErrorListener errorListener, String url) {


        HelperVolley.CallApiWithJson(context, url, url, updateSuccessListener, json, repClass, errorListener);

    }



    public static void getAllUserList(Context context, Response.Listener<GetAllLocationResponseList> updateSuccessListener, Type repClass, Response.ErrorListener errorListener, String url) {


        HelperVolley.CallGetApi(context, url, url, updateSuccessListener, GetAllLocationResponseList.class, errorListener);


    }

    public static void addNewUser(Context context, String json, Response.Listener<UserResponse> updateSuccessListener, Type repClass, Response.ErrorListener errorListener, String url) {

        //UiUtil.showProgressDialogue(context,"","Loading");

        HelperVolley.CallApiWithJson(context, url, url, updateSuccessListener, json, repClass, errorListener);

    }

    public static void getAllLastLoc(Context context, Response.Listener<LastLocList> updateSuccessListener, Type repClass, Response.ErrorListener errorListener, String url) {


        HelperVolley.CallGetApi(context, url, url, updateSuccessListener, LastLocList.class, errorListener);


    }



    public static void getTodaysNotification(Context context, Response.Listener<NotificationResonseList> updateSuccessListener, Type repClass, Response.ErrorListener errorListener, String url) {


        HelperVolley.CallGetApi(context, url, url, updateSuccessListener, NotificationResonseList.class, errorListener);


    }



    public static void getConstList(Context context, Response.Listener<ConstituencyList> updateSuccessListener, Type repClass, Response.ErrorListener errorListener, String url) {


        HelperVolley.CallGetApi(context, url, url, updateSuccessListener, ConstituencyList.class, errorListener);


    }
*/



}
