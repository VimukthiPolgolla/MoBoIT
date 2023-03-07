package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class AddModules_Admin extends AppCompatActivity {
    Button add;
    EditText staffID;
    Spinner modules;
    DatabaseReference ref_staff, ref_staffModules;
    Staff_Modules staff_modules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modules_admin);

        staffID = findViewById(R.id.indexSubmit);
        add = findViewById(R.id.addModulesBtn);
        modules = findViewById(R.id.ModuleSpinner);
        staff_modules = new Staff_Modules();

        ref_staff = FirebaseDatabase.getInstance().getReference().child("Staff");
        ref_staffModules = FirebaseDatabase.getInstance().getReference().child("Staff_Modules");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.modules, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modules.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = staffID.getText().toString().trim();
                String mod = modules.getSelectedItem().toString();

                if (TextUtils.isEmpty(id))
                {
                    staffID.setError("Fill this form!");
                }
                else
                {
                    ref_staff.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(id))
                            {
                                ref_staffModules.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        //if id is already present in staff_modules database
                                        if (snapshot.hasChild(id))
                                        {
                                            boolean isDuplicate = false;
                                            String[] names = {"module1", "module2", "module3", "module4", "module5"};

                                            if (snapshot.child(id).hasChild("module5"))
                                            {
                                                for (int n = 0 ; n < 5 ; n++)
                                                {
                                                    if (snapshot.child(id).child(names[n]).getValue().toString().equals(mod))
                                                    {
                                                        isDuplicate = true;
                                                    }
                                                }
                                            }
                                            else if (snapshot.child(id).hasChild("module4"))
                                            {
                                                for (int n = 0 ; n < 4 ; n++)
                                                {
                                                    if (snapshot.child(id).child(names[n]).getValue().toString().equals(mod))
                                                    {
                                                        isDuplicate = true;
                                                    }
                                                }
                                            }
                                            else if (snapshot.child(id).hasChild("module3"))
                                            {
                                                for (int n = 0 ; n < 3 ; n++)
                                                {
                                                    if (snapshot.child(id).child(names[n]).getValue().toString().equals(mod))
                                                    {
                                                        isDuplicate = true;
                                                    }
                                                }
                                            }
                                            else if (snapshot.child(id).hasChild("module2"))
                                            {
                                                for (int n = 0 ; n < 2 ; n++)
                                                {
                                                    if (snapshot.child(id).child(names[n]).getValue().toString().equals(mod))
                                                    {
                                                        isDuplicate = true;
                                                    }
                                                }
                                            }
                                            else if (snapshot.child(id).hasChild("module1"))
                                            {
                                                for (int n = 0 ; n < 1 ; n++)
                                                {
                                                    if (snapshot.child(id).child(names[n]).getValue().toString().equals(mod))
                                                    {
                                                        isDuplicate = true;
                                                    }
                                                }
                                            }

                                            if (isDuplicate)
                                            {
                                                Toast.makeText(AddModules_Admin.this, "Module is already added!", Toast.LENGTH_SHORT).show();

                                            }
                                            //if 5 modules are already added
                                            else if (snapshot.child(id).hasChild("module5"))
                                            {
                                                Toast.makeText(AddModules_Admin.this, "Maximum modules reached!", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                if (snapshot.child(id).hasChild("module4"))
                                                {
                                                    String mod1 = snapshot.child(id).child("module1").getValue().toString();
                                                    String mod2 = snapshot.child(id).child("module2").getValue().toString();
                                                    String mod3 = snapshot.child(id).child("module3").getValue().toString();
                                                    String mod4 = snapshot.child(id).child("module4").getValue().toString();
                                                    staff_modules.setModule1(mod1);
                                                    staff_modules.setModule2(mod2);
                                                    staff_modules.setModule3(mod3);
                                                    staff_modules.setModule4(mod4);
                                                    staff_modules.setModule5(mod);
                                                    ref_staffModules.child(id).setValue(staff_modules);
                                                }
                                                else if (snapshot.child(id).hasChild("module3"))
                                                {
                                                    String mod1 = snapshot.child(id).child("module1").getValue().toString();
                                                    String mod2 = snapshot.child(id).child("module2").getValue().toString();
                                                    String mod3 = snapshot.child(id).child("module3").getValue().toString();
                                                    staff_modules.setModule1(mod1);
                                                    staff_modules.setModule2(mod2);
                                                    staff_modules.setModule3(mod3);
                                                    staff_modules.setModule4(mod);
                                                    ref_staffModules.child(id).setValue(staff_modules);
                                                }
                                                else if (snapshot.child(id).hasChild("module2"))
                                                {
                                                    String mod1 = snapshot.child(id).child("module1").getValue().toString();
                                                    String mod2 = snapshot.child(id).child("module2").getValue().toString();
                                                    staff_modules.setModule1(mod1);
                                                    staff_modules.setModule2(mod2);
                                                    staff_modules.setModule3(mod);
                                                    ref_staffModules.child(id).setValue(staff_modules);
                                                }
                                                else if (snapshot.child(id).hasChild("module1"))
                                                {
                                                    String mod1 = snapshot.child(id).child("module1").getValue().toString();
                                                    staff_modules.setModule1(mod1);
                                                    staff_modules.setModule2(mod);
                                                    ref_staffModules.child(id).setValue(staff_modules);
                                                }
                                                Toast.makeText(AddModules_Admin.this, "Added successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        //if id is new to staff_modules database
                                        else
                                        {
                                            staff_modules.setModule1(mod);
                                            ref_staffModules.child(id).setValue(staff_modules);
                                            Toast.makeText(AddModules_Admin.this, "Module added successfully", Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                            else
                            {
                                staffID.setError("ID not found!");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                staffID.getText().clear();
            }
        });
    }
}