package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Forum extends AppCompatActivity {
    EditText message;
    Button send;
    DrawerLayout drawerLayout;
    DatabaseReference ref_staff, ref_students;
    MainActivity main;
    private RecyclerView recyclerView;
    personAdapter adapter;
    DatabaseReference ref_forum;
    String name, index;
    ForumData forumdata;
    long maxid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        message = findViewById(R.id.forumMessage);
        send = findViewById(R.id.forumSend);
        drawerLayout = findViewById(R.id.drawer_layout);
        main = new MainActivity();
        ref_staff = FirebaseDatabase.getInstance().getReference().child("Staff");
        ref_students = FirebaseDatabase.getInstance().getReference().child("Students");

        ref_forum = FirebaseDatabase.getInstance().getReference().child("Forum");

        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<person> options = new FirebaseRecyclerOptions.Builder<person>().setQuery(ref_forum, person.class).build();

        adapter = new personAdapter(options);

        recyclerView.setAdapter(adapter);

        name = main.name;
        index = main.indexNumber;
        forumdata = new ForumData();


        ref_forum.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    maxid = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = message.getText().toString();

                if(TextUtils.isEmpty(msg))
                {
                    message.setError("Form empty!");
                }
                else
                {
                    forumdata.setMessage(msg);
                    forumdata.setName(name);
                    forumdata.setId(index);
                    ref_forum.child(String.valueOf(maxid+1)).setValue(forumdata);
                    message.getText().clear();
                }
            }
        });
    }

    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }


    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
    public void ClickMenu(View view){

        Calendar_Staff.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){

        Calendar_Staff.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){
        ref_students.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(main.indexNumber))
                {
                    Calendar_Staff.redirectActivity(Forum.this, HomePage_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Forum.this, HomePage_Staff.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void ClickCalendar(View view){
        ref_students.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(main.indexNumber))
                {
                    Calendar_Staff.redirectActivity(Forum.this, Calendar_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Forum.this, CalendarEdit_Staff.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void ClickModules(View view){
        ref_students.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(main.indexNumber))
                {
                    Calendar_Staff.redirectActivity(Forum.this, ModulesSemester_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Forum.this, ModulesSemester_Staff.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void ClickExamResults(View view){
        ref_students.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(main.indexNumber))
                {
                    Calendar_Staff.redirectActivity(Forum.this, ResultsSemester_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Forum.this, Results_Staff.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void ClickForum(View view){

        recreate();
    }

    public void ClickContactInformation(View view){

        Calendar_Staff.redirectActivity(this, Contacts.class);
    }
    public void ClickAboutUs(View view){

        Calendar_Staff.redirectActivity(this, About.class);
    }

    public void ClickProfile(View view){

        Calendar_Staff.redirectActivity(this, Profile.class);
    }

    public void ClickLogout(View view){

        Calendar_Staff.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Calendar_Staff.closeDrawer(drawerLayout);
    }
}