package com.example.moboit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactsUpdate_Admin extends AppCompatActivity {
    EditText email;
    EditText contact;
    EditText description;
    Button update;
    DatabaseReference ref_contacts;
    ContactsDatabase contactsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_update_admin);

        email = findViewById(R.id.indexSubmit);
        contact = findViewById(R.id.addModulesModuleName);
        description = findViewById(R.id.examModule5);
        update = findViewById(R.id.addModulesBtn);
        contactsDatabase = new ContactsDatabase();
        ref_contacts = FirebaseDatabase.getInstance().getReference().child("Contacts");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Number = contact.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Description = description.getText().toString().trim();
                if (TextUtils.isEmpty(Email))
                {
                    email.setError("Fill this field!");
                }
                else if (TextUtils.isEmpty(Number))
                {
                    contact.setError("Fill this field!");
                }

                else if (TextUtils.isEmpty(Description))
                {
                    description.setError("Fill this field!");
                }
                else
                {
                    contactsDatabase.setEmail(Email);
                    contactsDatabase.setNumber(Number);
                    contactsDatabase.setDescription(Description);
                    ref_contacts.setValue(contactsDatabase);
                    contact.getText().clear();
                    description.getText().clear();
                    email.getText().clear();
                    Toast.makeText(ContactsUpdate_Admin.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}