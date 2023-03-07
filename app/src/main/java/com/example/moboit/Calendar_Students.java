package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Calendar_Students extends AppCompatActivity {
    DrawerLayout drawerLayout;
    CalendarView calendar;
    CalendarEdit_Staff calStaff;
    DatabaseReference ref_calendar;
    int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_students);

        drawerLayout = findViewById(R.id.drawer_layout);
        calendar = findViewById(R.id.calendarView2);
        calStaff = new CalendarEdit_Staff();
        ref_calendar = FirebaseDatabase.getInstance().getReference().child("Calendar");

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                year = i;
                month = i1+1;
                day = i2;

                ref_calendar.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(year + "_" + month + "_" + day))
                        {
                            String events = snapshot.child(year + "_" + month + "_" + day).child("events").getValue().toString();
                            String[] array = events.split(",");
                            calStaff.dialogEvents(Calendar_Students.this, array, year + "/" + month + "/" + day);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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

        Calendar_Staff.redirectActivity(this, HomePage_Students.class);
    }

    public void ClickCalendar(View view){

        recreate();
    }
    public void ClickAboutUs(View view){

        Calendar_Staff.redirectActivity(this, About.class);
    }

    public void ClickProfile(View view){

        Calendar_Staff.redirectActivity(this, Profile.class);
    }

    public void ClickModules(View view){

        Calendar_Staff.redirectActivity(this, ModulesSemester_Students.class);
    }

    public void ClickExamResults(View view){

        Calendar_Staff.redirectActivity(this, ResultsSemester_Students.class);
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