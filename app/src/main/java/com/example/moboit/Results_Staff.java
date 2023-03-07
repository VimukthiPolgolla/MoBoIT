package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Results_Staff extends AppCompatActivity {
    Button viewResults, SubmitBtn;
    Spinner modules, modulesView;
    Spinner grade;
    EditText submitIndex, viewIndex;
    DatabaseReference ref_staffModules, ref_examResults, ref_students;
    List <String> names;
    ExamResults examResults;
    ExamResultsGrades examGrades;

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_staff);
        viewResults = findViewById(R.id.rSemester1);
        drawerLayout = findViewById(R.id.drawer_layout);
        modules = findViewById(R.id.ModuleSpinner);
        modulesView = findViewById(R.id.moduleSpinner2);
        grade = findViewById(R.id.gradeSpinner);
        submitIndex = findViewById(R.id.indexSubmit);
        viewIndex = findViewById(R.id.indexView);
        SubmitBtn = findViewById(R.id.submitBtn);
        MainActivity main = new MainActivity();
        names = new ArrayList<>();
        examResults = new ExamResults();
        examGrades = new ExamResultsGrades();
        ref_staffModules = FirebaseDatabase.getInstance().getReference().child("Staff_Modules");
        ref_examResults = FirebaseDatabase.getInstance().getReference().child("Exam_Results");
        ref_students = FirebaseDatabase.getInstance().getReference().child("Students");
        //getting modules taught by the staff member
        ref_staffModules.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child(main.indexNumber).hasChild("module5"))
                {
                    names.add(snapshot.child(main.indexNumber).child("module1").getValue(String.class));
                    names.add(snapshot.child(main.indexNumber).child("module2").getValue(String.class));
                    names.add(snapshot.child(main.indexNumber).child("module3").getValue(String.class));
                    names.add(snapshot.child(main.indexNumber).child("module4").getValue(String.class));
                    names.add(snapshot.child(main.indexNumber).child("module5").getValue(String.class));
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Results_Staff.this, android.R.layout.simple_spinner_item, names);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    modulesView.setAdapter(adapter2);
                    modules.setAdapter(adapter2);
                }
                else if (snapshot.child(main.indexNumber).hasChild("module4"))
                {
                    names.add(snapshot.child(main.indexNumber).child("module1").getValue(String.class));
                    names.add(snapshot.child(main.indexNumber).child("module2").getValue(String.class));
                    names.add(snapshot.child(main.indexNumber).child("module3").getValue(String.class));
                    names.add(snapshot.child(main.indexNumber).child("module4").getValue(String.class));
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Results_Staff.this, android.R.layout.simple_spinner_item, names);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    modulesView.setAdapter(adapter2);
                    modules.setAdapter(adapter2);
                }
                else if (snapshot.child(main.indexNumber).hasChild("module3"))
                {
                    names.add(snapshot.child(main.indexNumber).child("module1").getValue(String.class));
                    names.add(snapshot.child(main.indexNumber).child("module2").getValue(String.class));
                    names.add(snapshot.child(main.indexNumber).child("module3").getValue(String.class));
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Results_Staff.this, android.R.layout.simple_spinner_item, names);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    modules.setAdapter(adapter2);
                    modulesView.setAdapter(adapter2);
                }
                else if (snapshot.child(main.indexNumber).hasChild("module2"))
                {
                    names.add(snapshot.child(main.indexNumber).child("module1").getValue(String.class));
                    names.add(snapshot.child(main.indexNumber).child("module2").getValue(String.class));
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Results_Staff.this, android.R.layout.simple_spinner_item, names);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    modules.setAdapter(adapter2);
                    modulesView.setAdapter(adapter2);
                }
                else if (snapshot.child(main.indexNumber).hasChild("module1"))
                {
                    names.add(snapshot.child(main.indexNumber).child("module1").getValue(String.class));
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Results_Staff.this, android.R.layout.simple_spinner_item, names);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    modules.setAdapter(adapter2);
                    modulesView.setAdapter(adapter2);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.grades, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grade.setAdapter(adapter1);
        //submitting details
        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref_staffModules.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //if staff member has modules
                        if (snapshot.hasChild(main.indexNumber))
                        {
                            String index = submitIndex.getText().toString().trim();
                            String Grade = grade.getSelectedItem().toString();
                            String Module = modules.getSelectedItem().toString();

                            if (TextUtils.isEmpty(index))
                            {
                                submitIndex.setError("Fill this box!");
                            }
                            else
                            {
                                ref_students.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.hasChild(index))
                                        {
                                            ref_examResults.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    //if student data is already present
                                                    examGrades.setGrade(Grade);
                                                    ref_examResults.child(index).child(getSemester(Module)).child(Module).setValue(examGrades);
                                                    Toast.makeText(Results_Staff.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                        }
                                        else
                                        {
                                            Toast.makeText(Results_Staff.this, "Student not found!", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }
                        else
                        {
                            Toast.makeText(Results_Staff.this, "No modules selected!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        viewResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String index = viewIndex.getText().toString().trim();
                String Module = modulesView.getSelectedItem().toString();

                ref_students.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(index))
                        {
                            ref_examResults.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.child(index).child(getSemester(Module)).hasChild(Module))
                                    {
                                        dialog(Results_Staff.this, Module + ": " + snapshot.child(index).child(getSemester(Module)).child(Module).child("grade").getValue().toString());
                                    }
                                    else
                                    {
                                        dialog(Results_Staff.this, "Result not added yet!!");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                        else
                        {
                            Toast.makeText(Results_Staff.this, "Student not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
    //method to get the semester when module is entered
    public String getSemester(String module)
    {
        if (module.equals("Computer Systems") ||
            module.equals("Programming Concepts") ||
            module.equals("Mathematics for Computing") ||
            module.equals("Professional Practice") ||
            module.equals("Communication in Tech World"))
        {
            return "semester1";
        }
        else if (module.equals("Data Technologies") ||
                module.equals("Business Analysis and Software Design") ||
                module.equals("Internet Technologies") ||
                module.equals("Probability and Statistics") ||
                module.equals("Entrepreneurship and Start-up Culture"))
        {
            return "semester2";
        }
        else if (module.equals("Arts for Technology") ||
                module.equals("Object Oriented Programming") ||
                module.equals("Communication Protocols and Models") ||
                module.equals("Information Security") ||
                module.equals("Programming with Vectors and Matrices"))
        {
            return "semester3";
        }
        else if (module.equals("Data Structures and Algorithms") ||
                module.equals("Operating Systems and Platforms") ||
                module.equals("Cloud Computing Fundamentals") ||
                module.equals("Technology Challenge Competition") ||
                module.equals("Project Management"))
        {
            return "semester4";
        }
        else
            return "Not found";
    }
    //method to dialog box
    public static void dialog(Activity activity, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Result");
        builder.setMessage(msg);
        builder.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public void ClickMenu(View view){

        Calendar_Staff.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){

        Calendar_Staff.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){

        Calendar_Staff.redirectActivity(this, HomePage_Staff.class);
    }

    public void ClickCalendar(View view){

        Calendar_Staff.redirectActivity(this, CalendarEdit_Staff.class);
    }

    public void ClickModules(View view){

        Calendar_Staff.redirectActivity(this, ModulesSemester_Staff.class);
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