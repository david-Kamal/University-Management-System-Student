package com.reham.modernacademystudent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.reham.modules.CommonModule;
import com.reham.modules.IEndPoints;
import com.reham.modules.IRetrofitResponseListener;
import com.reham.modules.Requests.LoginRequest;
import com.reham.modules.Requests.LoginRes;
import com.reham.modules.SessionManager;
import com.reham.modules.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity
{

    private View parent_view;
    private EditText mgetUserName;
    private EditText mgetPassword;
    private SessionManager sessionManager;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent_view = findViewById(android.R.id.content);
//        fab_login = (Button) findViewById(R.id.fab_login);
        mgetUserName =  findViewById(R.id.getUserName);
        mgetPassword =  findViewById(R.id.getPassword);
        sessionManager = new SessionManager(this);
    }

    public void login(View view)
    {
        final String username = mgetUserName.getText().toString().trim();
        final String password = mgetPassword.getText().toString().trim();

        LoginRequest loginReq = new LoginRequest(username, password);
        Call<LoginRes> call = MainActivity.client.studentLogin(loginReq);
        try {
            call.enqueue(new Callback<LoginRes>() {
                @Override
                public void onResponse(Call<LoginRes> call, Response<LoginRes> response)
                {
                    sessionManager.setLogin(true);
                    sessionManager.setUsername(username);
                    sessionManager.setPassword(password);
                    sessionManager.setStudentId(response.body().getRows().getId());
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    getDataFromWs();
                    openMainActivity();
                }

                @Override
                public void onFailure(Call<LoginRes> call, Throwable t)
                {
                    sessionManager.setLogin(false);
                    sessionManager.setUsername("");
                    sessionManager.setPassword("");
                    sessionManager.setStudentId(0);
                    showAlert(t.getMessage());
                }
            });
        }catch (Exception e)
        {
            showAlert(e.getMessage());
        }
    }

    private void openMainActivity()
    {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    private void showAlert(String msg)
    {
        builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle("Error");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void getDataFromWs()
    {
        CommonModule commonModule = new CommonModule();
        commonModule.getExamsData(sessionManager.getStudentId());
        commonModule.getRemainingData(sessionManager.getStudentId());
        commonModule.getAttendance(sessionManager.getStudentId());

    }

    public void openChoosingActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }


    private boolean attemptLogin() {

        // Reset errors.
        mgetUserName.setError(null);
        mgetPassword.setError(null);

        // Store values at the time of the login attempt.
        String username = mgetUserName.getText().toString();
        String password = mgetPassword.getText().toString();


        if (password.isEmpty()) {
            mgetPassword.setError(getString(R.string.error_field_required));
            return false;
        }
        if (username.isEmpty()) {
            mgetUserName.setError(getString(R.string.error_field_required));
            return false;
        }
        return true;
    }
}
