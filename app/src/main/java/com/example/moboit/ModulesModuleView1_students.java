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

public class ModulesModuleView1_students extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView topic;
    MainActivity main;
    TextView owner, assistant, outline;
    ModulesModule_Students1 modStudent;
    DatabaseReference ref_moduleInfo;
    String index, moduleName; //user's index number and module name
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules_module_view1_students);

        drawerLayout = findViewById(R.id.drawer_layout);
        modStudent = new ModulesModule_Students1();
        main = new MainActivity();
        index = main.indexNumber;
        moduleName = modStudent.module;
        topic = findViewById(R.id.textView2);
        owner = findViewById(R.id.ModuleOwnerName);
        assistant = findViewById(R.id.moduleAssistantName);
        outline = findViewById(R.id.moduleOutlineName);

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

        Calendar_Staff.redirectActivity(this, ResultsSemester_Students.class);
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