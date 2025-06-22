package com.reham.modernacademystudent;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.reham.modules.CommonModule;
import com.reham.modules.IRetrofitResponseListener;
import com.reham.modules.SessionManager;

public class Splash extends AppCompatActivity {


    CommonModule commonModule;

    AlertDialog.Builder alertDialog;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //CreatePhotoDirectory();

        sessionManager = new SessionManager(Splash.this);
        commonModule = new CommonModule();


        if(isWorkingInternetPersent()){
            splash();
        }
        else{
            runTask();
        }

    }

    public void splash()
    {

        checkIfLoggedIn();
    }

    public boolean isWorkingInternetPersent()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getBaseContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public void runTask ()
    {
        if(isWorkingInternetPersent())
        {
            splash();
        } else {
            alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Internet Connection");
            alertDialog.setMessage("Sorry there was an error getting data from the Internet.\nNetwork Unavailable!");

            alertDialog.setPositiveButton("Retry", (dialog, which) -> {
                dialog.dismiss();
                runTask();
            });
            AlertDialog dialog = alertDialog.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    private void checkIfLoggedIn()
    {
        if (sessionManager.isLoggedIn())
        {
            System.out.println("logged in");

            commonModule.getSubjectsData(new IRetrofitResponseListener() {
                @Override
                public void onSuccess()
                {
                    try {
                        getDataFromWs();
                        // law 3erf y connect 3la el server 3ady w gab data hykml y call ba2y el ws
                        startActivity(new Intent(Splash.this, MainActivity.class));
                        finish();
                    }catch (Exception ex)
                    {
                        showAlertDialog(ex.getMessage());
                    }
                }

                @Override
                public void onFailure()
                {
                    alertDialog = new AlertDialog.Builder(Splash.this);
                    alertDialog.setMessage("خطأ في الاتصال");

                    alertDialog.setPositiveButton("حاول مرة أخرى", (dialog, which) -> {
                        dialog.dismiss();
                        checkIfLoggedIn();
                    });
                    AlertDialog dialog = alertDialog.create();
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                }
            });

        }else {
            startActivity(new Intent(Splash.this, LoginActivity.class));
            finish();
        }
    }

    private void getDataFromWs()
    {
        CommonModule commonModule = new CommonModule();
        commonModule.getExamsData(sessionManager.getStudentId());
        commonModule.getRemainingData(sessionManager.getStudentId());
        commonModule.getAttendance(sessionManager.getStudentId());
    }

    private void showAlert(String message)
    {
        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = alertDialog.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    //fullscreen setup
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showAlertDialog(String message)
    {
        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = alertDialog.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}