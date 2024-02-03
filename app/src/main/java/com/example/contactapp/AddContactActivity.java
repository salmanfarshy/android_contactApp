package com.example.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

public class AddContactActivity extends AppCompatActivity {

    private ContactViewModel contactViewModel;
    TextView arrowBtn;
    Button addBtn;
    EditText firstName, lastName, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        //Referencing
        arrowBtn = findViewById(R.id.arrowBtn);
        addBtn = findViewById(R.id.addBtn);

        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = findViewById(R.id.firstName);
                lastName = findViewById(R.id.lastName);
                phoneNumber = findViewById(R.id.phoneNumber);

                String first_Name = firstName.getText().toString();
                String last_Name = lastName.getText().toString();
                String phone_number = phoneNumber.getText().toString();


                if (!first_Name.isEmpty() && !phone_number.isEmpty()){
                    // Insert a new contact
                    contactViewModel.insert(first_Name, last_Name, phone_number);
                    firstName.setText("");
                    lastName.setText("");
                    phoneNumber.setText("");
                    Toast.makeText(getApplicationContext(), "Contact has been saved.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "First name and phone number are required.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}