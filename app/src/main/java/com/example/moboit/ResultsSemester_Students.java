package com.example.moboit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultsSemester_Students extends AppCompatActivity {
    Button sem1;
    Button sem2;
    Button sem3;
    Button sem4;
    DrawerLayout drawerLayout;
    public static String semesterName;
    public static String semester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_semester_students);

        sem1 = findViewById(R.id.rSemester1);
        sem2 = findViewById(R.id.rSemester2);
        sem3 = findViewById(R.id.rSemester3);
        sem4 = findViewById(R.id.rSemester4);
        drawerLayout = findViewById(R.id.drawer_layout);

        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem1 = new Intent(ResultsSemester_Students.this, ResultsView_Students.class);
                semester = "semester1";
                semesterName = "Semester 1";
                startActivity(sem1);
            }
        });
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem1 = new Intent(ResultsSemester_Students.this, ResultsView_Students.class);
                semester = "semester2";
                semesterName = "Semester 2";
                startActivity(sem1);
            }
        });
        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem1 = new Intent(ResultsSemester_Students.this, ResultsView_Students.class);
                semester = "semester3";
                semesterName = "Semester 3";
                startActivity(sem1);
            }
        });
        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sem1 = new Intent(ResultsSemester_Students.this, ResultsView_Students.class);
                semester = "semester4";
                semesterName = "Semester 4";
                startActivity(sem1);
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

        Calendar_Staff.redirectActivity(this, Calendar_Students.class);
    }

    public void ClickModules(View view){

        Calendar_Staff.redirectActivity(this, ModulesSemester_Students.class);
    }

    public void ClickExamResults(View view){

        recreate();
    }

    public void ClickForum(View view){

        Calendar_Staff.redirectActivity(this, Forum.class);
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