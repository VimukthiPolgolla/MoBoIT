package com.example.moboit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import com.google.firebase.firestore.model.FieldPath;

public class MainActivity extends AppCompatActivity {
    Button login;
    TextView signup;
    EditText password;
    EditText ID;
    public static String name;
    public static String indexNumber;
    Staff staff;
    Students students;
    DatabaseReference ref_staff, ref_students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.loginButton);
        signup = findViewById(R.id.loginCreateAccount);
        password = findViewById(R.id.loginPassword);
        ID = findViewById(R.id.loginID);

        staff = new Staff();
        students = new Students();
        ref_staff = FirebaseDatabase.getInstance().getReference().child("Staff");
        ref_students = FirebaseDatabase.getInstance().getReference().child("Students");

        //for notifications
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        //calling create account page
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = ID.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (TextUtils.isEmpty(id))
                {
                    ID.setError("Enter ID!");
                }
                else if (TextUtils.isEmpty(pass))
                {
                    password.setError("Enter Password!");
                }
                else if (hasSymbols(id) || hasSymbols(pass))
                {
                    Toast.makeText(MainActivity.this, "Use only '@' symbol for username and password!", Toast.LENGTH_SHORT).show();
                }
                //admin account
                else if(id.equals("admin") && pass.equals("abcd"))
                {
                    Intent admin_homepage = new Intent(MainActivity.this, HomePage_Admin.class);
                    startActivity(admin_homepage);
                }
                else
                {
                    ref_students.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //if student
                            if (snapshot.hasChild(id))
                            {
                                if (snapshot.child(id).hasChild("password"))
                                {
                                    if (pass.equals(snapshot.child(id).child("password").getValue().toString()))
                                    {
                                        Intent student_homepage = new Intent(MainActivity.this, HomePage_Students.class);
                                        name = snapshot.child(id).child("name").getValue().toString();
                                        indexNumber = snapshot.child(id).child("index").getValue().toString();
                                        startActivity(student_homepage);
                                    }
                                    else
                                    {
                                        Toast.makeText(MainActivity.this, "Please check your ID & Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Create your account!", Toast.LENGTH_SHORT).show();

                                }


                            }
                            //if staff
                            else
                            {
                                ref_staff.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.hasChild(id))
                                        {
                                            if (snapshot.child(id).hasChild("password"))
                                            {
                                                if (pass.equals(snapshot.child(id).child("password").getValue().toString()))
                                                {
                                                    Intent staff_homepage = new Intent(MainActivity.this, HomePage_Staff.class);
                                                    name = snapshot.child(id).child("name").getValue().toString();
                                                    indexNumber = snapshot.child(id).child("id").getValue().toString();
                                                    startActivity(staff_homepage);
                                                }
                                                else
                                                {
                                                    Toast.makeText(MainActivity.this, "Please check your ID & Password", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(MainActivity.this, "Create your account!", Toast.LENGTH_SHORT).show();

                                            }

                                        }
                                        else
                                        {
                                            Toast.makeText(MainActivity.this, "Please check your ID & Password", Toast.LENGTH_SHORT).show();
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
    public static boolean hasSymbols(String word)
    {
        if (word.contains("#") || word.contains("$") || word.contains("^") || word.contains("."))
        {
            return true;
        }
        return false;
    }

    public static void createNotification(Activity activity,String text)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, "My Notification");
//        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(activity);
        managerCompat.notify(1, builder.build());
    }

}