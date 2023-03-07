package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    EditText pass;
    EditText conPass;
    EditText currentPass;
    Button updatePass;
    DrawerLayout drawerLayout;
    Staff staff;
    Students students;
    MainActivity main;
    TextView Profname, ProfId;
    DatabaseReference ref_students, ref_staff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pass = findViewById(R.id.profilePassword);
        conPass = findViewById(R.id.profileConfirmPassword);
        updatePass = findViewById(R.id.profilePasswordBtn);
        currentPass = findViewById(R.id.profileCurrentPassword);
        drawerLayout = findViewById(R.id.drawer_layout);
        Profname = findViewById(R.id.profName);
        ProfId = findViewById(R.id.profId);
        staff = new Staff();
        students = new Students();
        main = new MainActivity();
        String id = MainActivity.indexNumber;
        String name = MainActivity.name;

        ref_staff = FirebaseDatabase.getInstance().getReference().child("Staff");
        ref_students = FirebaseDatabase.getInstance().getReference().child("Students");

        Profname.setText(name);
        ProfId.setText(id);

        updatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = pass.getText().toString();
                String cpassword = conPass.getText().toString();
                String current = currentPass.getText().toString();
                if (TextUtils.isEmpty(current))
                {
                    currentPass.setError("Fill this form!");
                }
                else if (TextUtils.isEmpty(password))
                {
                    pass.setError("Fill this form!");
                }
                else if (TextUtils.isEmpty(cpassword))
                {
                    conPass.setError("Fill this form!");
                }
                else
                {
                    ref_students.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(id))
                            {
                                //if student
                                if (snapshot.child(id).child("password").getValue().toString().equals(current))
                                {
                                    students.setEmail(snapshot.child(id).child("email").getValue().toString());
                                    students.setIndex(snapshot.child(id).child("index").getValue().toString());
                                    students.setName(snapshot.child(id).child("name").getValue().toString());
                                    students.setPassword(password);
                                    ref_students.child(id).setValue(students);
                                    pass.getText().clear();
                                    conPass.getText().clear();
                                    currentPass.getText().clear();
                                    Toast.makeText(Profile.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(Profile.this, "Current password is incorrect", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                ref_staff.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        //if staff
                                        if (snapshot.child(id).child("password").getValue().toString().equals(current))
                                        {
                                            staff.setEmail(snapshot.child(id).child("email").getValue().toString());
                                            staff.setID(snapshot.child(id).child("id").getValue().toString());
                                            staff.setName(snapshot.child(id).child("name").getValue().toString());
                                            staff.setPassword(password);
                                            ref_staff.child(id).setValue(staff);
                                            pass.getText().clear();
                                            conPass.getText().clear();
                                            currentPass.getText().clear();
                                            Toast.makeText(Profile.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(Profile.this, "Current password is incorrect", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
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
                if (snapshot.hasChild(MainActivity.indexNumber))
                {
                    Calendar_Staff.redirectActivity(Profile.this, HomePage_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Profile.this, HomePage_Staff.class);
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
                if (snapshot.hasChild(MainActivity.indexNumber))
                {
                    Calendar_Staff.redirectActivity(Profile.this, Calendar_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Profile.this, CalendarEdit_Staff.class);
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
                if (snapshot.hasChild(MainActivity.indexNumber))
                {
                    Calendar_Staff.redirectActivity(Profile.this, ModulesSemester_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Profile.this, ModulesSemester_Staff.class);
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
                if (snapshot.hasChild(MainActivity.indexNumber))
                {
                    Calendar_Staff.redirectActivity(Profile.this, ResultsSemester_Students.class);
                }
                else
                {
                    Calendar_Staff.redirectActivity(Profile.this, Results_Staff.class);
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

        Calendar_Staff.redirectActivity(this, About.class);
    }

    public void ClickProfile(View view){
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