package com.reham.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.reham.modernacademystudent.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class GroupChatActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private ImageButton sendMessageBtn;
    private EditText userMessageInput;
    private ScrollView mScrollView;
    private TextView displayTextMessages;

    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef, GroupNameRef, GroupMessageKeyRef;




    private String currentGroupName, currentUserID, currentUserName, currentDate, currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        // ana h3del w h3ml zay ma tege m3aya w h comment mesh hms7 3shan lma a5ls law 3aiz 7aga ok ?
        // mashy

        currentGroupName = getIntent().getExtras().get("groupName").toString();
        setTitle(currentGroupName);


        //Toast.makeText(GroupChatActivity.this, currentGroupName, Toast.LENGTH_SHORT).show();


        // enta kunt m7tag el toolbar da f a ? any wa7d asdk dah


        //mToolBar = (Toolbar) findViewById(R.id.group_chat_bar_layout);
//        setSupportActionBar(mToolBar);
//        getSupportActionBar().setTitle(currentGroupName);
//

        // la da el custom el kunt 3amlu bosy ana msh fakr ana kol ally kont b3mlo any sh8al m3 al
        // ragl w lma al brnamg atzbt w asht8l an ad5lto gowa w shlt  al fragments w 7tat mknha
        // al activity bt3t al groups wl ba2y kan tmam bs b3dkda 7slt moshklt al login ally kan bygbha
        // anma ana bsra7a msh fakr awy ana kont 3mlha lah bs akleed msh mn dma8y.

        // typ el login mknsh feh mushkela enta bs kunt btwadeh 3la el main activity el bara 5ales mesh el
        // feh el listview bs 7ata f friendly chat mknsh mzhr el fragment rl feh asamy el grups
        // f 7aga mn 2 ya ema kunt bt3ml t3dela w mkmlthash
        // ya ema el nos5a el kant sh8ala 3ndy 3 el gehaz ana mn2lthash
        // l2v howa ana abl mdeky al gehaz bta3k kont wa5d mno kol 7aga  f m3tkdsh de w 7tt any mkmltsh
        // t3deela l2 bardo bs msh fakr bs ana kont na2l kol 7aga kant deh bs al moshkla


        // anyway ...

        sendMessageBtn = (ImageButton) findViewById(R.id.send_message_btn);
        userMessageInput = (EditText) findViewById(R.id.input_group_message);
        displayTextMessages = (TextView) findViewById(R.id.group_chat_text_display);
        mScrollView = (ScrollView) findViewById(R.id.my_scroll_view);



        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        // reference of any group we select
        GroupNameRef = FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGroupName);




        GetUserInfo();


        // Send the text message to the database server.
        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveMessageInfoToDatabase();
                userMessageInput.setText("");
            }
        });

    }

    // retrieve the data from the database
    @Override
    protected void onStart()
    {
        super.onStart();

        GroupNameRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                if (dataSnapshot.exists())
                {
                    DisplayMessages(dataSnapshot);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                if (dataSnapshot.exists())
                {
                    DisplayMessages(dataSnapshot);
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    private void SaveMessageInfoToDatabase()
    {
        String message = userMessageInput.getText().toString();
        // create a key
        String messageKey = GroupNameRef.push().getKey();

        if(TextUtils.isEmpty(message))
        {
            Toast.makeText(this, "Please write a message first...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Calendar calForDate = Calendar.getInstance();
            // getting date and time of the sent message
            SimpleDateFormat currentDateFormat = new SimpleDateFormat("MMM dd, yyyy");
            currentDate = currentDateFormat.format(calForDate.getTime());

            Calendar calForTime = Calendar.getInstance();
            // getting date and time of the sent message
            SimpleDateFormat currentTimeFormat = new SimpleDateFormat("hh:mm a");
            currentTime = currentTimeFormat.format(calForTime.getTime());


            HashMap<String, Object> groupMessageKey = new HashMap<>();
            GroupNameRef.updateChildren(groupMessageKey);
            //get key

            GroupMessageKeyRef = GroupNameRef.child(messageKey);

            // formatting the message inside the database server
            HashMap<String,Object> messageInfoMap = new HashMap<>();
            messageInfoMap.put("name", currentUserName);
            messageInfoMap.put("message", message);
            messageInfoMap.put("date", currentDate);
            messageInfoMap.put("time", currentTime);

            GroupMessageKeyRef.updateChildren(messageInfoMap);

        }

    }

    private void GetUserInfo()
    {
        UsersRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    currentUserName = dataSnapshot.child("name").getValue().toString();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void DisplayMessages(DataSnapshot dataSnapshot)
    {
        // it will move line by line to get specific group message
        Iterator iterator = dataSnapshot.getChildren().iterator();

        // get the key values (name, id,message ...)
        while(iterator.hasNext())
        {
            // get the first child message and all data keys
            String chatDate = (String) ((DataSnapshot)iterator.next()).getValue();
            String chatMessage = (String) ((DataSnapshot)iterator.next()).getValue();
            String chatName = (String) ((DataSnapshot)iterator.next()).getValue();
            String chatTime = (String) ((DataSnapshot)iterator.next()).getValue();

            // display them all

            displayTextMessages.append(chatName + " :\n"+chatMessage + "\n"+ chatTime + "    "+chatDate +"\n\n\n");



        }
    }

}
