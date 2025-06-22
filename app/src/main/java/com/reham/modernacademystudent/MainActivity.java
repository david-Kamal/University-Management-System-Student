package com.reham.modernacademystudent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.reham.modules.CommonModule;
import com.reham.modules.IEndPoints;
import com.reham.modules.RetrofitClient;
import com.reham.modules.SessionManager;

public class MainActivity extends AppCompatActivity {


    private  static final String BASE_URL = "http://10.0.2.2/gradproj/api/mobileapis/";
    public static IEndPoints client = RetrofitClient.getInstance(BASE_URL).create(IEndPoints.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);


        onBackPressed();


}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.option_logout)
        {
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.setLogin(false);
            sessionManager.setUsername("");
            sessionManager.setPassword("");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            Toast.makeText(this, "logged out", Toast.LENGTH_SHORT).show();
            return true;
        }else
        {
            return false;
        }
    }


boolean doubleBackToExitPressedOnce = false;

            @Override
            public void onBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }



            public void showAlertDialog() {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Are you sure?");
                builder.setMessage("Are you sure you want to Log out?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                AlertDialog alert = builder.create();
                alert.show();
            }

    public void Exam(View view)
    {
        Intent intent = new Intent(MainActivity.this, ExamActivity.class);
        startActivity(intent);
    }

    public void Remaining(View view)
    {
        Intent intent = new Intent(MainActivity.this, RemainingActivity.class);
        startActivity(intent);
    }


    public void Profile(View view)
    {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void Result(View view)
    {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        startActivity(intent);
    }

    public void About(View view)
    {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }

    public void Chat(View view)
    {
        Intent intent = new Intent(MainActivity.this, com.reham.chat.LoginActivity.class);
        startActivity(intent);
    }

    public void attendance(View view)
    {
        Intent intent = new Intent(MainActivity.this, AttendanceActivity.class);
        startActivity(intent);
    }
}
