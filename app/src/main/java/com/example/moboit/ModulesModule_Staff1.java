package com.example.moboit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ModulesModule_Staff1 extends AppCompatActivity {
    Button mod1;
    Button mod2;
    Button mod3;
    Button mod4;
    Button mod5;
    String module1, module2, module3, module4, module5;
    DrawerLayout drawerLayout;
    ModulesSemester_Staff modSem;
    public static String module;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules_module_staff1);

        mod1 = findViewById(R.id.rSemester1);
        mod2 = findViewById(R.id.rSemester2);
        mod3 = findViewById(R.id.rSemester3);
        mod4 = findViewById(R.id.rSemester4);
        mod5 = findViewById(R.id.module5);
        drawerLayout = findViewById(R.id.drawer_layout);
        modSem = new ModulesSemester_Staff();

        if (modSem.semester.equals("semester1"))
        {
            module1 = "Computer Systems";
            module2 = "Programming Concepts";
            module3 = "Mathematics for Computing";
            module4 = "Professional Practice";
            module5 = "Communication in Tech World";
        }
        else if (modSem.semester.equals("semester2"))
        {
            module1 = "Data Technologies";
            module2 = "Business Analysis and Software Design";
            module3 = "Internet Technologies";
            module4 = "Probability and Statistics";
            module5 = "Entrepreneurship and Start-up Culture";
        }
        else if (modSem.semester.equals("semester3"))
        {
            module1 = "Arts for Technology";
            module2 = "Object Oriented Programming";
            module3 = "Communication Protocols and Models";
            module4 = "Information Security";
            module5 = "Programming with Vectors and Matrices";
        }
        else if (modSem.semester.equals("semester4"))
        {
            module1 = "Data Structures and Algorithms";
            module2 = "Operating Systems and Platforms";
            module3 = "Cloud Computing Fundamentals";
            module4 = "Technology Challenge Competition";
            module5 = "Project Management";
        }

        mod1.setText(module1);
        mod2.setText(module2);
        mod3.setText(module3);
        mod4.setText(module4);
        mod5.setText(module5);

        mod1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mod1 = new Intent(ModulesModule_Staff1.this, ModulesModuleView1_staff.class);
                module = module1;
                startActivity(mod1);
            }
        });
        mod2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mod1 = new Intent(ModulesModule_Staff1.this, ModulesModuleView1_staff.class);
                module = module2;
                startActivity(mod1);
            }
        });
        mod3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mod1 = new Intent(ModulesModule_Staff1.this, ModulesModuleView1_staff.class);
                module = module3;
                startActivity(mod1);
            }
        });
        mod4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mod1 = new Intent(ModulesModule_Staff1.this, ModulesModuleView1_staff.class);
                module = module4;
                startActivity(mod1);
            }
        });
        mod5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mod1 = new Intent(ModulesModule_Staff1.this, ModulesModuleView1_staff.class);
                module = module5;
                startActivity(mod1);
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