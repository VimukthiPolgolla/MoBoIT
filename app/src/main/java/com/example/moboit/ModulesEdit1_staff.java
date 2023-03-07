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

public class ModulesEdit1_staff extends AppCompatActivity {
    EditText desc;
    Button owner;
    Button lecturer;
    Button outcomes;
    TextView topic;
    DatabaseReference ref_moduleInfo;
    DrawerLayout drawerLayout;
    ModulesModule_Staff1 modStaff;
    String module;
    Module_Information moduleInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules_edit1_staff);

        desc = findViewById(R.id.moduleEdit);
        owner = findViewById(R.id.moduleOwner);
        lecturer = findViewById(R.id.moduleLecturers);
        outcomes = findViewById(R.id.moduleOutcomes);
        drawerLayout = findViewById(R.id.drawer_layout);
        topic = findViewById(R.id.textView2);
        modStaff = new ModulesModule_Staff1();
        module = modStaff.module;
        moduleInformation = new Module_Information();

        ref_moduleInfo = FirebaseDatabase.getInstance().getReference().child("Module_Information");

        topic.setText(modStaff.module);

        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String description = desc.getText().toString();
                if (TextUtils.isEmpty(description))
                {
                    desc.setError("Fill this box!");
                }
                //if box is not empty
                else
                {
                    ref_moduleInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //if module is present already
                            if (snapshot.hasChild(module))
                            {
                                if (snapshot.child(module).hasChild("assistant"))
                                {
                                    moduleInformation.setAssistant(snapshot.child(module).child("assistant").getValue().toString());
                                }
                                if (snapshot.child(module).hasChild("outcomes"))
                                {
                                    moduleInformation.setOutcomes(snapshot.child(module).child("outcomes").getValue().toString());
                                }

                            }
                            moduleInformation.setOwner(description);
                            ref_moduleInfo.child(module).setValue(moduleInformation);
                            desc.getText().clear();
                            Toast.makeText(ModulesEdit1_staff.this, "Added successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
        lecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = desc.getText().toString();
                if (TextUtils.isEmpty(description))
                {
                    desc.setError("Fill this box!");
                }
                //if box is not empty
                else
                {
                    ref_moduleInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //if module is present already
                            if (snapshot.hasChild(module))
                            {
                                if (snapshot.child(module).hasChild("owner"))
                                {
                                    moduleInformation.setOwner(snapshot.child(module).child("owner").getValue().toString());
                                }
                                if (snapshot.child(module).hasChild("outcomes"))
                                {
                                    moduleInformation.setOutcomes(snapshot.child(module).child("outcomes").getValue().toString());
                                }

                            }
                            moduleInformation.setAssistant(description);
                            ref_moduleInfo.child(module).setValue(moduleInformation);
                            desc.getText().clear();
                            Toast.makeText(ModulesEdit1_staff.this, "Added successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
        outcomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = desc.getText().toString();
                if (TextUtils.isEmpty(description))
                {
                    desc.setError("Fill this box!");
                }
                //if box is not empty
                else
                {
                    ref_moduleInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //if module is present already
                            if (snapshot.hasChild(module))
                            {
                                if (snapshot.child(module).hasChild("assistant"))
                                {
                                    moduleInformation.setAssistant(snapshot.child(module).child("assistant").getValue().toString());
                                }
                                if (snapshot.child(module).hasChild("owner"))
                                {
                                    moduleInformation.setOwner(snapshot.child(module).child("owner").getValue().toString());
                                }
                                moduleInformation.setOutcomes(description);
                                ref_moduleInfo.child(module).setValue(moduleInformation);
                                desc.getText().clear();
                                Toast.makeText(ModulesEdit1_staff.this, "Added successfully", Toast.LENGTH_SHORT).show();

                            }
                            //if new module
                            else
                            {
                                moduleInformation.setOutcomes(description);
                                ref_moduleInfo.child(module).setValue(moduleInformation);
                                desc.getText().clear();
                                Toast.makeText(ModulesEdit1_staff.this, "Added successfully", Toast.LENGTH_SHORT).show();

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

        Calendar_Staff.redirectActivity(this, HomePage_Staff.class);
    }

    public void ClickCalendar(View view){

        Calendar_Staff.redirectActivity(this, CalendarEdit_Staff.class);
    }

    public void ClickModules(View view){

        Calendar_Staff.redirectActivity(this, ModulesSemester_Staff.class);
    }

    public void ClickExamResults(View view){

        Calendar_Staff.redirectActivity(this, Results_Staff.class);
    }
    public void ClickAboutUs(View view){

        Calendar_Staff.redirectActivity(this, About.class);
    }

    public void ClickProfile(View view){

        Calendar_Staff.redirectActivity(this, Profile.class);
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