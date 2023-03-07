package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Contacts extends AppCompatActivity {
    DrawerLayout drawerLayout;
    TextView email;
    TextView contact;
    TextView desc;
    DatabaseReference ref_contacts, ref_students;
    MainActivity main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        email = findViewById(R.id.email);
        contact = findViewById(R.id.number);
        desc = findViewById(R.id.description);
        drawerLayout = findViewById(R.id.drawer_layout);
        ref_contacts = FirebaseDatabase.getInstance().getReference().child("Contacts");
        ref_students = FirebaseDatabase.getInstance().getReference().child("Students");
        main = new MainActivity();

        ref_contacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email.setText(snapshot.child("email").getValue().toString());
                contact.setText(snapshot.child("number").getValue().toString());
                desc.setText(snapshot.child("description").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                    Calendar_Staff.redirectActivity(Contacts.this, HomePage_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Contacts.this, HomePage_Staff.class);
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
                    Calendar_Staff.redirectActivity(Contacts.this, Calendar_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Contacts.this, CalendarEdit_Staff.class);
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
                    Calendar_Staff.redirectActivity(Contacts.this, ModulesSemester_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Contacts.this, ModulesSemester_Staff.class);
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
                    Calendar_Staff.redirectActivity(Contacts.this, ResultsSemester_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Contacts.this, Results_Staff.class);
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
    public void ClickAboutUs(View view){

        Calendar_Staff.redirectActivity(this, About.class);
    }

    public void ClickProfile(View view){

        Calendar_Staff.redirectActivity(this, Profile.class);
    }

    public void ClickContactInformation(View view){

        recreate();
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