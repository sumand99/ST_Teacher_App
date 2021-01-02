package com.swipetouch.parentsapp.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;


import com.google.gson.Gson;

/**
 * Created by sjena on 12/02/19.
 */

public class UiUtil {

    public static ProgressDialog pDialogue;
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private static int PRIVATE_MODE = 0;


    public static void showProgressDialogue(Context context, String tittle, String msg) {
        if(context != null) {
            if (pDialogue != null && pDialogue.isShowing()) {
                pDialogue.setTitle(tittle);
                pDialogue.setMessage(msg);
            } else {
                pDialogue = new ProgressDialog(context);
                pDialogue.setTitle(tittle);
                pDialogue.setMessage(msg);
                pDialogue.show();
            }
            pDialogue.setCanceledOnTouchOutside(true);
            pDialogue.setCancelable(true);
        }
    }

    public static void cancelProgressDialogue() {
        try {
            if (pDialogue != null && pDialogue.isShowing()) {
                pDialogue.dismiss();
                pDialogue = null;
            }
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        }
    }

    public static void showToast(Context context, String msg) {
        if (context == null)
            return;
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

    public static void showLog(String tag, String msg) {

        Log.d(tag, msg);

    }








}
