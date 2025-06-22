package com.reham.chat;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.reham.modernacademystudent.LoginActivity;
import com.reham.modernacademystudent.R;

public class MainChatActivity extends AppCompatActivity {

//    private ViewPager mViewpager;
    private TabLayout mTablayout;
    private TabsAccessAdapter mTabsAccessAdapter;

    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;

    AlertDialog.Builder dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);


        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        Log.i("main", mAuth.getCurrentUser() + "");

        currentUser = mAuth.getCurrentUser();
        RootRef = FirebaseDatabase.getInstance().getReference();

//        mViewpager = (ViewPager) findViewById(R.id.main_tabs_pager);
        mTabsAccessAdapter = new TabsAccessAdapter(getSupportFragmentManager());
//        mViewpager.setAdapter(mTabsAccessAdapter);


        mTablayout = (TabLayout) findViewById(R.id.main_tabs);
//        mTablayout.setupWithViewPager(mViewpager);

        try {
            showGroups();
        }catch (Exception ex)
        {
            showAlert(ex.getMessage());
        }

    }

    private void showGroups()
    {
        FragmentTransaction mFragmentTransaction;
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_groups, new GroupsFragment());
        mFragmentTransaction.commit();
    }

    // Check wether the user is logged in previously

    @Override
    protected void onStart()
    {
        super.onStart();
        if (currentUser == null)
        {
            SendUserToLoginActivity();
        }
        // watch video number 14
        else
        {
            VerifyUserExistence();
        }

    }

    private void VerifyUserExistence()
    {
        String currentUserId = mAuth.getCurrentUser().getUid();

        // get user name from the database server
        RootRef.child("Users").child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if ((dataSnapshot.child("name").exists()))
                {
                    Toast.makeText(MainChatActivity.this, "Welcome...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SendUserToSettingsActivity();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




    // For the menu logout

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.options_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.main_logout)
        {
            mAuth.signOut();
            SendUserToLoginActivity();
        }
        if (item.getItemId() == R.id.find_friends_group_option)
        {
            SendUserToFindFriendsActivity();
        }

        if (item.getItemId() == R.id.main_create_group_option)
        {
            RequestNewGroup();
        }
        // main_settings_option
        if (item.getItemId() == R.id.main_settings_option)
        {
            SendUserToSettingsActivity();
        }

        return true;
    }

    private void RequestNewGroup()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainChatActivity.this, R.style.AlertDialog);
        builder.setTitle("Enter Group Name : ");
        final EditText groupNameField = new EditText(MainChatActivity.this);
        groupNameField.setHint("e.g Friends");
        builder.setView(groupNameField);

        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String groupName = groupNameField.getText().toString();
                if(TextUtils.isEmpty(groupName))
                {
                    Toast.makeText(MainChatActivity.this, "Please write Group Name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    CreateNewGroup(groupName);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void CreateNewGroup(final String groupName) {

        RootRef.child("Groups").child(groupName).setValue("")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainChatActivity.this, groupName +" group is created successfully!!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void SendUserToLoginActivity()
    {
        Intent intent = new Intent(MainChatActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    private void SendUserToSettingsActivity()
    {
        Intent intent = new Intent(MainChatActivity.this, SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        startActivity(intent);
        finish();
    }

    private void SendUserToFindFriendsActivity()
    {
        Intent intent = new Intent(MainChatActivity.this, FindFriendsActivity.class);
        startActivity(intent);

    }
    private void showAlert(String msg)
    {
        dialog = new AlertDialog.Builder(this);
        dialog.setMessage(msg);
        dialog.setTitle("Error");
        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}



