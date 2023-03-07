package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateAccount extends AppCompatActivity {
    EditText name;
    EditText ID;
    EditText password;
    EditText cpassword;
    Button signup;
    TextView login;
    Spinner type;
    Staff staff;
    Students students;
    DatabaseReference ref_staff, ref_students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        name = findViewById(R.id.signupName);
        ID = findViewById(R.id.signupID);
        password = findViewById(R.id.signupPassword);
        cpassword = findViewById(R.id.signupCPassword);
        type = findViewById(R.id.spinner);
        signup = findViewById(R.id.signupCreate);
        login = findViewById(R.id.signupLogin);

        staff = new Staff();
        students = new Students();
        ref_staff = FirebaseDatabase.getInstance().getReference().child("Staff");
        ref_students = FirebaseDatabase.getInstance().getReference().child("Students");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.staffStudent, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String id = ID.getText().toString();
                String Password = password.getText().toString();
                String CPassword = cpassword.getText().toString();
                Boolean stud = false;
                if (type.getSelectedItem().toString().equals("Student"))
                    stud = true;
                if (TextUtils.isEmpty(Name))
                {
                    name.setError("Enter your name!");
                }
                else if (TextUtils.isEmpty(id))
                {
                    ID.setError("Enter your ID!");
                }
                else if (TextUtils.isEmpty(Password))
                {
                    password.setError("Enter password!");
                }
                else if (TextUtils.isEmpty(CPassword))
                {
                    cpassword.setError("Enter confirmation!");
                }
                //if passwords do not match
                else if (Password.equals(CPassword) == false)
                    Toast.makeText(CreateAccount.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                //if passwords match
                else
                {
                    //If student
                    if (stud == true)
                    {
                        ref_students.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //If admin has added details
                                if (snapshot.hasChild(id))
                                {
                                    if(snapshot.child(id).hasChild("password"))
                                    {
                                        Toast.makeText(CreateAccount.this, "You already have an account", Toast.LENGTH_SHORT).show();

                                    }
                                    else
                                    {
                                        students.setEmail(snapshot.child(id).child("email").getValue().toString());
                                        students.setPassword(Password);
                                        students.setName(Name);
                                        students.setIndex(id);
                                        ref_students.child(id).setValue(students);
                                        Toast.makeText(CreateAccount.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    }
                                //If admin hasn't added details
                                else
                                {
                                    Toast.makeText(CreateAccount.this, "Please contact admin!", Toast.LENGTH_SHORT).show();
                                }
                                name.getText().clear();
                                ID.getText().clear();
                                password.getText().clear();
                                cpassword.getText().clear();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    //If staff
                    else
                    {
                        ref_staff.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //If admin has added details
                                if (snapshot.hasChild(id))
                                {
                                    if(snapshot.child(id).hasChild("password"))
                                    {
                                        Toast.makeText(CreateAccount.this, "You already have an account", Toast.LENGTH_SHORT).show();

                                    }
                                    else
                                    {
                                        staff.setEmail(snapshot.child(id).child("email").getValue().toString());
                                        staff.setPassword(Password);
                                        staff.setName(Name);
                                        staff.setID(id);
                                        ref_staff.child(id).setValue(staff);
                                        Toast.makeText(CreateAccount.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                //If admin hasn't added details
                                else
                                {
                                    Toast.makeText(CreateAccount.this, "Please contact admin!", Toast.LENGTH_SHORT).show();
                                }
                                name.getText().clear();
                                ID.getText().clear();
                                password.getText().clear();
                                cpassword.getText().clear();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                }
            }
        });
    }

}