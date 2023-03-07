package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CalendarEdit_Staff extends AppCompatActivity {
    EditText name;
    Button add;
    Button delete;
    DrawerLayout drawerLayout;
    CalendarView calendar;
    DatabaseReference ref_calendar;
    int year, month, day;
    Calendar cal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_edit_staff);

        name = findViewById(R.id.calendar_event);
        add = findViewById(R.id.calendar_add_event);
        delete = findViewById(R.id.calendar_delete_event);
        drawerLayout = findViewById(R.id.drawer_layout);
        calendar = findViewById(R.id.calendarView3);
        ref_calendar = FirebaseDatabase.getInstance().getReference().child("Calendar");
        cal = new Calendar();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                year = i;
                month = i1+1;
                day = i2;

                ref_calendar.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(year + "_" + month + "_" + day))
                        {
                            String events = snapshot.child(year + "_" + month + "_" + day).child("events").getValue().toString();
                            String[] array = events.split(",");
                            dialogEvents(CalendarEdit_Staff.this, array, year + "/" + month + "/" + day);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String event = name.getText().toString();
                if (TextUtils.isEmpty(event))
                {
                    name.setError("Field is empty!");
                }
                else
                {
                    ref_calendar.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //if already present
                            if(snapshot.hasChild(year + "_" + month + "_" + day))
                            {
                                String data = snapshot.child(year + "_" + month + "_" + day).child("events").getValue().toString();
                                cal.setEvents(data + "," + event);
                            }
                            else
                            {
                                cal.setEvents(event);
                            }
                            ref_calendar.child(year + "_" + month + "_" + day).setValue(cal);
                            name.getText().clear();
                            Toast.makeText(CalendarEdit_Staff.this, "Added successfully!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String event = name.getText().toString();
                if (TextUtils.isEmpty(event))
                {
                    name.setError("Field is empty!");
                }
                else
                {
                    ref_calendar.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(year + "_" + month + "_" + day))
                            {
                                String events = snapshot.child(year + "_" + month + "_" + day).child("events").getValue().toString();
                                String[] array = events.split(",");
                                List<String> list = new ArrayList<String>(Arrays.asList(array));
                                Boolean isPresent = false;
                                for(String value:array)
                                {
                                    if (value.equals(event))
                                    {
                                        isPresent = true;
                                    }
                                }
                                if (isPresent && array.length == 1)
                                {
                                    ref_calendar.child(year + "_" + month + "_" + day).removeValue();
                                }
                                else if (isPresent)
                                {
                                    list.remove(event);
                                    array = list.toArray(new String[0]);
                                    events = array[0];
                                    for (int i = 1 ; i < array.length ; i++)
                                    {
                                        events = events + "," + array[i];
                                    }
                                    cal.setEvents(events);
                                    ref_calendar.child(year + "_" + month + "_" + day).setValue(cal);
                                    name.getText().clear();
                                    Toast.makeText(CalendarEdit_Staff.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(CalendarEdit_Staff.this, "No such event!!", Toast.LENGTH_SHORT).show();
                                }

                            }
                            else
                            {
                                Toast.makeText(CalendarEdit_Staff.this, "No such event!!", Toast.LENGTH_SHORT).show();
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

    //method to dialog box
    public static void dialogEvents(Activity activity, String[] events, String date) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Events for " + date);
        String message;
        message = "Event 1: " + events[0];
        for (int i = 1 ; i < events.length ; i++)
        {
            int n = i + 1;
            message = message + "\n" + "Event " + n + ": " + events[i];
        }
        builder.setMessage(message);
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

        recreate();
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
