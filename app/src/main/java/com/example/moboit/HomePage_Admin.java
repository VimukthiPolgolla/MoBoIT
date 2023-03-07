package com.example.moboit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage_Admin extends AppCompatActivity {
    Button addStudent, addStaff, removeStaffBtn, contact, removeStudentBtn, addModules;
    EditText studentEmail,studentName,studentIndex,staffEmail,staffName, removeStaff, staffId, removeStudent;
    DatabaseReference ref_staff, ref_students;
    Staff staff;
    Students students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_admin);

        addStaff = findViewById(R.id.adminAddStaff);
        addStudent = findViewById(R.id.adminAddStudent);
        removeStaffBtn = findViewById(R.id.adminRemoveStaffBtn);
        removeStudentBtn = findViewById(R.id.adminRemoveStudentBtn);
        studentEmail = findViewById(R.id.adminStudentEmail);
        studentName = findViewById(R.id.adminStudentName);
        studentIndex = findViewById(R.id.adminStudentIndex);
        staffEmail = findViewById(R.id.adminStaffEmail);
        staffName = findViewById(R.id.adminStaffName);
        removeStaff = findViewById(R.id.adminRemoveStaff);
        removeStudent = findViewById(R.id.adminRemoveStudent);
        staffId = findViewById(R.id.adminStaffNumber);
        contact = findViewById(R.id.adminContact);
        addModules = findViewById(R.id.adminAddModules);
        staff = new Staff();
        students = new Students();
        ref_staff = FirebaseDatabase.getInstance().getReference().child("Staff");
        ref_students = FirebaseDatabase.getInstance().getReference().child("Students");

        //add modules page
        addModules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addmodules = new Intent(HomePage_Admin.this, AddModules_Admin.class);
                startActivity(addmodules);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update_contacts = new Intent(HomePage_Admin.this, ContactsUpdate_Admin.class);
                startActivity(update_contacts);
            }
        });

        addStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = staffEmail.getText().toString();
                String name = staffName.getText().toString();
                String id = staffId.getText().toString();
                staff.setEmail(email);
                staff.setName(name);
                staff.setID(id);
                ref_staff.child(id).setValue(staff);
                staffEmail.getText().clear();
                staffName.getText().clear();
                staffId.getText().clear();
                Toast.makeText(HomePage_Admin.this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = studentEmail.getText().toString();
                String name = studentName.getText().toString();
                String index = studentIndex.getText().toString();
                students.setEmail(email);
                students.setName(name);
                students.setIndex(index);
                ref_students.child(index).setValue(students);
                studentEmail.getText().clear();
                studentName.getText().clear();
                studentIndex.getText().clear();
                Toast.makeText(HomePage_Admin.this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        removeStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = removeStaff.getText().toString();
                ref_staff.child(id).removeValue();
                removeStaff.getText().clear();
                Toast.makeText(HomePage_Admin.this, "Removed Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        removeStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String index = removeStudent.getText().toString();
                ref_students.child(index).removeValue();
                removeStudent.getText().clear();
                Toast.makeText(HomePage_Admin.this, "Removed Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}