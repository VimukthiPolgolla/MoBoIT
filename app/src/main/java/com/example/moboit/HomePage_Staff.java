package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage_Staff extends AppCompatActivity {
    Button calender;
    Button modules;
    Button results;
    Button forum;
    Button contacts;
    DrawerLayout drawerLayout;
    TextView text, textIndex;
    DatabaseReference ref_forum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_staff);

        calender = findViewById(R.id.homeCalender);
        modules = findViewById(R.id.homeModules);
        results = findViewById(R.id.homeExam);
        forum = findViewById(R.id.homeForum);
        contacts = findViewById(R.id.homeContact);
        drawerLayout = findViewById(R.id.drawer_layout);
        text = findViewById(R.id.textView2);
        textIndex = findViewById(R.id.textView9);
        ref_forum = FirebaseDatabase.getInstance().getReference().child("Forum");

        MainActivity main = new MainActivity();
        text.setText(main.name);
        textIndex.setText(main.indexNumber);


        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calender = new Intent(HomePage_Staff.this, CalendarEdit_Staff.class);
                startActivity(calender);
            }
        });
        modules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent modules = new Intent(HomePage_Staff.this, ModulesSemester_Staff.class);
                startActivity(modules);
            }
        });
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent results = new Intent(HomePage_Staff.this, Results_Staff.class);
                startActivity(results);
            }
        });
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forum = new Intent(HomePage_Staff.this, Forum.class);
                startActivity(forum);
            }
        });
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contacts = new Intent(HomePage_Staff.this, Contacts.class);
                startActivity(contacts);
            }
        });
    }

    public void ClickMenu(View view){

        Calendar_Staff.openDrawer(drawerLayout);

    }
    public void ClickLogo(View view){

        Calendar_Staff.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){

        recreate();
    }

    public void ClickCalendar(View view){

        Calendar_Staff.redirectActivity(this, CalendarEdit_Staff.class);
    }

    public void ClickModules(View view){

        Calendar_Staff.redirectActivity(this, ModulesSemester_Staff.class);
    }

    public void ClickExamResults(View view){

        Calendar_Staff.redirectActivity(this, Results_Staff.class);
    }
    public void ClickAboutUs(View view){

        Calendar_Staff.redirectActivity(this, About.class);
    }

    public void ClickProfile(View view){

        Calendar_Staff.redirectActivity(this, Profile.class);
    }

    public void ClickForum(View view){

        Calendar_Staff.redirectActivity(this, Forum.class);
    }

    public void ClickContactInformation(View view){

        Calendar_Staff.redirectActivity(this, Contacts.class);
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