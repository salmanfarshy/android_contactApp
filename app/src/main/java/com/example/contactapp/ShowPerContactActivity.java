package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowPerContactActivity extends AppCompatActivity {

    private MyRepository myRepository;
    TextView arrowBtn1;
    TextView showFirstName;
    TextView showLastName;
    TextView showPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_per_contact);

        myRepository = new MyRepository(getApplication());
        arrowBtn1 = findViewById(R.id.arrowBtn1);

        long contactId = getIntent().getLongExtra("contactId", -1);
        showFirstName = findViewById(R.id.showFirstName);
        showLastName = findViewById(R.id.showLastName);
        showPhoneNumber = findViewById(R.id.showPhoneNumber);


        myRepository.getContactById(contactId).observe(this, new Observer<Contact>() {
            @Override
            public void onChanged(Contact contact) {
                if (contact != null) {

                    showFirstName = findViewById(R.id.showFirstName);
                    showLastName = findViewById(R.id.showLastName);
                    showPhoneNumber = findViewById(R.id.showPhoneNumber);

                    showFirstName.setText(contact.getFirstName());
                    showLastName.setText(contact.getLastName());
                    showPhoneNumber.setText(contact.getPhoneNumber());
                }
            }
        });

        arrowBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}