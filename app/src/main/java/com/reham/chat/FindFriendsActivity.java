package com.reham.chat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.reham.modernacademystudent.R;

import java.util.HashMap;

public class FindFriendsActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private RecyclerView FindFriendsRecyclerList;
    private DatabaseReference UsersRef;

    FirebaseRecyclerAdapter<Contacts, FindFriendsViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        FindFriendsRecyclerList = (RecyclerView) findViewById(R.id.find_friends_recycler_list);
        //FindFriendsRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        FindFriendsRecyclerList.setLayoutManager(linearLayoutManager);


    }


    @Override
    protected void onStart()
    {
        super.onStart();

        Query query = FirebaseDatabase.getInstance().getReference("/Users");
// swany kda ana 3ayz a2olk 7aga, fl video kan 3aml ank t8yry al username, w image w status
        //bs ana m3mltsh kda f momkn tb2a de al moshkla
        System.out.println(((DatabaseReference) query).getParent());
        System.out.println(((DatabaseReference) query).getRef());
        System.out.println(((DatabaseReference) query).getKey());

        FirebaseRecyclerOptions<Contacts> options =
                new FirebaseRecyclerOptions.Builder<Contacts>()
                        .setQuery(query, Contacts.class).build();


        adapter = new FirebaseRecyclerAdapter<Contacts, FindFriendsViewHolder>(options)
        {
            @Override
            public void startListening()
            {
                super.startListening();
                System.out.println("startListening");
            }

            @Override
            public void onError(DatabaseError error)
            {
                super.onError(error);
                Log.e("onError", error.getDetails());
            }

            @Override
                    protected void onBindViewHolder(@NonNull FindFriendsViewHolder holder, int position, @NonNull Contacts model)
                    {
                        // get the name of the user from the database
                        holder.userName.setText(model.getName());

                    }

                    @Override
                    public FindFriendsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        // connect layout
                        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users_display_layout, viewGroup, false);
                        FindFriendsViewHolder viewHolder = new FindFriendsViewHolder(view);
                        return viewHolder;
                    }
                };

        FindFriendsRecyclerList.setAdapter(adapter);

        adapter.startListening();

    }

    public static class FindFriendsViewHolder extends RecyclerView.ViewHolder
    {

        TextView userName;

        public FindFriendsViewHolder(@NonNull View itemView)
        {
            super(itemView);

            userName = (TextView) itemView.findViewById(R.id.user_profile_name);
        }
    }

    @Override
    protected void onDestroy()
    {
        if (adapter != null)
        {
            adapter.stopListening();
        }
        super.onDestroy();
    }
}
