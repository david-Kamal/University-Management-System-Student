package com.reham.modules;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager
{
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "LoginSession";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String USERNAME= "USERNAME";
    private static final String PASSWORD= "PASSWORD";
    private static final String STUDENT_ID =  "ID";

    public void setStudentId(int id)
    {
        editor.putInt(STUDENT_ID, id);
        editor.commit();

    }

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setUsername(String username)
    {
        editor.putString(USERNAME, username);
        editor.commit();

    }
    public void setPassword(String password)
    {
        editor.putString(PASSWORD, password);
        editor.commit();

    }
    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
    }

    public String getUsername(){return pref.getString(USERNAME, "0");}
    public String getPassword(){return pref.getString(PASSWORD, "0");}
    public int getStudentId(){return pref.getInt(STUDENT_ID, 0);}
    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}