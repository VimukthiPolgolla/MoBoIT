package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class About extends AppCompatActivity {
    DrawerLayout drawerLayout;
    DatabaseReference ref_students;
    MainActivity main;
    TextView imesh, maduka, pola, adeeptha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        drawerLayout = findViewById(R.id.drawer_layout);
        ref_students = FirebaseDatabase.getInstance().getReference().child("Students");
        imesh = findViewById(R.id.textView5);
        pola = findViewById(R.id.textView10);
        maduka = findViewById(R.id.textView15);
        adeeptha = findViewById(R.id.textView20);
        main = new MainActivity();

        imesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.linkedin.com/in/imesh-ranawella/"));
                startActivity(viewIntent);
            }
        });
        pola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.linkedin.com/in/vimukthi-polgolla-2a69aa209/"));
                startActivity(viewIntent);
            }
        });
        maduka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.linkedin.com/in/madhuka-dilshan-3a223a1ab/"));
                startActivity(viewIntent);
            }
        });
        adeeptha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.linkedin.com/in/adeeptha-hettiarachchi-2aa1581b1/"));
                startActivity(viewIntent);
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
        ref_students.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(main.indexNumber))
                {
                    Calendar_Staff.redirectActivity(About.this, HomePage_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(About.this, HomePage_Staff.class);
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
                    Calendar_Staff.redirectActivity(About.this, Calendar_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(About.this, CalendarEdit_Staff.class);
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
                    Calendar_Staff.redirectActivity(About.this, ModulesSemester_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(About.this, ModulesSemester_Staff.class);
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
                    Calendar_Staff.redirectActivity(About.this, ResultsSemester_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(About.this, Results_Staff.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void ClickForum(View view){

        Calendar_Staff.redirectActivity(this, Forum.class);
    }

    public void ClickContactInformation(View view){

        Calendar_Staff.redirectActivity(this, Contacts.class);
    }

    public void ClickAboutUs(View view){

        recreate();
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