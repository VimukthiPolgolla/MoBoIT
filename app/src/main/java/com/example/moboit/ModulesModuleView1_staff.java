package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ModulesModuleView1_staff extends AppCompatActivity {
    Button edit;
    DrawerLayout drawerLayout;
    ModulesModule_Staff1 modStaff;
    TextView topic;
    TextView owner, assistant, outline;
    MainActivity main;
    DatabaseReference ref_staff, ref_staffModules,  ref_moduleInfo;
    String index, moduleName; //user's index number and module name
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules_module_view1_staff);

        edit = findViewById(R.id.editModule);
        drawerLayout = findViewById(R.id.drawer_layout);
        modStaff = new ModulesModule_Staff1();
        main = new MainActivity();
        index = main.indexNumber;
        moduleName = modStaff.module;
        owner = findViewById(R.id.ModuleOwnerName);
        assistant = findViewById(R.id.moduleAssistantName);
        outline = findViewById(R.id.moduleOutlineName);
        topic = findViewById(R.id.textView2);

        ref_staff = FirebaseDatabase.getInstance().getReference().child("Staff");
        ref_staffModules = FirebaseDatabase.getInstance().getReference().child("Staff_Modules");
        ref_moduleInfo = FirebaseDatabase.getInstance().getReference().child("Module_Information");


        topic.setText(moduleName);

        //page information
        ref_moduleInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(moduleName))
                {
                    if (snapshot.child(moduleName).hasChild("owner"))
                    {
                        owner.setText(snapshot.child(moduleName).child("owner").getValue().toString());
                    }
                    if (snapshot.child(moduleName).hasChild("assistant"))
                    {
                        assistant.setText(snapshot.child(moduleName).child("assistant").getValue().toString());
                    }
                    if (snapshot.child(moduleName).hasChild("outcomes"))
                    {
                        outline.setText(snapshot.child(moduleName).child("outcomes").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //accessing edit page
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref_staffModules.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(index))
                        {
                            Boolean found = false;
                            if (snapshot.child(index).hasChild("module5"))
                            {
                                if (snapshot.child(index).child("module5").getValue().toString().equals(moduleName) ||
                                        snapshot.child(index).child("module4").getValue().toString().equals(moduleName) ||
                                        snapshot.child(index).child("module3").getValue().toString().equals(moduleName) ||
                                        snapshot.child(index).child("module2").getValue().toString().equals(moduleName) ||
                                        snapshot.child(index).child("module1").getValue().toString().equals(moduleName))
                                {
                                    found = true;
                                }

                            }
                            else if (snapshot.child(index).hasChild("module4"))
                            {
                                if (snapshot.child(index).child("module1").getValue().toString().equals(moduleName) ||
                                        snapshot.child(index).child("module4").getValue().toString().equals(moduleName) ||
                                        snapshot.child(index).child("module3").getValue().toString().equals(moduleName) ||
                                        snapshot.child(index).child("module2").getValue().toString().equals(moduleName))
                                {
                                    found = true;
                                }
                            }
                            else if (snapshot.child(index).hasChild("module3"))
                            {
                                if (snapshot.child(index).child("module1").getValue().toString().equals(moduleName) ||
                                        snapshot.child(index).child("module2").getValue().toString().equals(moduleName) ||
                                        snapshot.child(index).child("module3").getValue().toString().equals(moduleName))
                                {
                                    found = true;
                                }
                            }
                            else if (snapshot.child(index).hasChild("module2"))
                            {
                                if (snapshot.child(index).child("module1").getValue().toString().equals(moduleName) ||
                                        snapshot.child(index).child("module2").getValue().toString().equals(moduleName))
                                {
                                    found = true;
                                }
                            }
                            else if (snapshot.child(index).hasChild("module1"))
                            {
                                if (snapshot.child(index).child("module1").getValue().toString().equals(moduleName))
                                {
                                    found = true;
                                }
                            }
                            if (found == true)
                            {
                                Intent edit = new Intent(ModulesModuleView1_staff.this, ModulesEdit1_staff.class);
                                startActivity(edit);
                            }
                            else
                            {
                                Toast.makeText(ModulesModuleView1_staff.this, "You don't have access to edit!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(ModulesModuleView1_staff.this, "You don't have access to edit!", Toast.LENGTH_SHORT).show();

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
    public void ClickAboutUs(View view){

        Calendar_Staff.redirectActivity(this, About.class);
    }

    public void ClickProfile(View view){

        Calendar_Staff.redirectActivity(this, Profile.class);
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