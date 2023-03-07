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

public class ResultsView_Students extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MainActivity main;
    ResultsSemester_Students result_semester;
    DatabaseReference ref_examResults;
    TextView activityName, Module1, Module2, Module3, Module4, Module5, Result1, Result2, Result3, Result4, Result5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_view_students);

        drawerLayout = findViewById(R.id.drawer_layout);
        main = new MainActivity();
        result_semester = new ResultsSemester_Students();
        activityName = findViewById(R.id.textView2);
        Module1 = findViewById(R.id.module1);
        Module2 = findViewById(R.id.module2);
        Module3 = findViewById(R.id.module3);
        Module4 = findViewById(R.id.module4);
        Module5 = findViewById(R.id.module5);
        Result1 = findViewById(R.id.result1);
        Result2 = findViewById(R.id.result2);
        Result3 = findViewById(R.id.result3);
        Result4 = findViewById(R.id.result4);
        Result5 = findViewById(R.id.result5);
        ref_examResults = FirebaseDatabase.getInstance().getReference().child("Exam_Results");

        activityName.setText(result_semester.semesterName);

        ref_examResults.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (result_semester.semester.equals("semester1"))
                {
                    Module1.setText("Computer Systems");
                    Module2.setText("Programming Concepts");
                    Module3.setText("Mathematics for Computing");
                    Module4.setText("Professional Practice");
                    Module5.setText("Communication in Tech World");
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Computer Systems"))
                    {
                        Result1.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Computer Systems").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Programming Concepts"))
                    {
                        Result2.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Programming Concepts").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Mathematics for Computing"))
                    {
                        Result3.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Mathematics for Computing").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Professional Practice"))
                    {
                        Result4.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Professional Practice").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Communication in Tech World"))
                    {
                        Result5.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Communication in Tech World").child("grade").getValue().toString());
                    }
                }
                else if (result_semester.semester.equals("semester2"))
                {
                    Module1.setText("Data Technologies");
                    Module2.setText("Business Analysis and Software Design");
                    Module3.setText("Internet Technologies");
                    Module4.setText("Probability and Statistics");
                    Module5.setText("Entrepreneurship and Start-up Culture");
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Data Technologies"))
                    {
                        Result1.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Data Technologies").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Business Analysis and Software Design"))
                    {
                        Result2.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Business Analysis and Software Design").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Internet Technologies"))
                    {
                        Result3.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Internet Technologies").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Probability and Statistics"))
                    {
                        Result4.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Probability and Statistics").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Entrepreneurship and Start-up Culture"))
                    {
                        Result5.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Entrepreneurship and Start-up Culture").child("grade").getValue().toString());
                    }
                }
                else if (result_semester.semester.equals("semester3"))
                {
                    Module1.setText("Arts for Technology");
                    Module2.setText("Object Oriented Programming");
                    Module3.setText("Communication Protocols and Models");
                    Module4.setText("Information Security");
                    Module5.setText("Programming with Vectors and Matrices");
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Arts for Technology"))
                    {
                        Result1.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Arts for Technology").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Object Oriented Programming"))
                    {
                        Result2.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Object Oriented Programming").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Communication Protocols and Models"))
                    {
                        Result3.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Communication Protocols and Models").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Information Security"))
                    {
                        Result4.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Information Security").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Programming with Vectors and Matrices"))
                    {
                        Result5.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Programming with Vectors and Matrices").child("grade").getValue().toString());
                    }
                }
                else if (result_semester.semester.equals("semester4"))
                {
                    Module1.setText("Data Structures and Algorithms");
                    Module2.setText("Operating Systems and Platforms");
                    Module3.setText("Cloud Computing Fundamentals");
                    Module4.setText("Technology Challenge Competition");
                    Module5.setText("Project Management");
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Data Structures and Algorithms"))
                    {
                        Result1.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Data Structures and Algorithms").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Operating Systems and Platforms"))
                    {
                        Result2.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Operating Systems and Platforms").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Cloud Computing Fundamentals"))
                    {
                        Result3.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Cloud Computing Fundamentals").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Technology Challenge Competition"))
                    {
                        Result4.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Technology Challenge Competition").child("grade").getValue().toString());
                    }
                    if (snapshot.child(main.indexNumber).child(result_semester.semester).hasChild("Project Management"))
                    {
                        Result5.setText(snapshot.child(main.indexNumber).child(result_semester.semester).child("Project Management").child("grade").getValue().toString());
                    }
                }
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

        Calendar_Staff.redirectActivity(this, HomePage_Students.class);
    }
    public void ClickAboutUs(View view){

        Calendar_Staff.redirectActivity(this, About.class);
    }

    public void ClickProfile(View view){

        Calendar_Staff.redirectActivity(this, Profile.class);
    }
    public void ClickCalendar(View view){

        Calendar_Staff.redirectActivity(this, Calendar_Students.class);
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