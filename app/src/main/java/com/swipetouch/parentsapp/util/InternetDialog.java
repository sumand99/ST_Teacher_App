package com.swipetouch.parentsapp.util;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.swipetouch.parentsapp.R;


/**
 * Created by kapil on 03/11/18.
 */

public class InternetDialog {
    private Context context;

    public InternetDialog(){

    }
    public InternetDialog(Context context){
        this.context = context;
    }

    public void showNoInternetDialog(Context context){
        final Dialog dialog1 = new Dialog(context, R.style.df_dialog);
        dialog1.setContentView(R.layout.dialog_no_internet);
        dialog1.setCancelable(true);
        dialog1.setCanceledOnTouchOutside(true);
        dialog1.findViewById(R.id.btnSpinAndWinRedeem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        UiUtil.cancelProgressDialogue();
        dialog1.show();
    }
    public  boolean getInternetStatus(Context context) {

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(!isConnected) {
            //show no internet dialog
            showNoInternetDialog(context);
        }
        return isConnected;
    }


}
