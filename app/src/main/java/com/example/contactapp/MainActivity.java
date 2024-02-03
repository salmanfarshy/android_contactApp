package com.example.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactAdapter.OnContactItemClickListener {

    private ContactAdapter contactAdapter;
    private ContactViewModel contactViewModel;
    TextView circleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(contactAdapter);

        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contactEntities) {
                contactAdapter.setContacts(contactEntities);
            }
        });

        circleButton = findViewById(R.id.circleButton);

        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    public void onContactItemClick(long contactId) {

        Intent intent = new Intent(MainActivity.this, ShowPerContactActivity.class);
        intent.putExtra("contactId", contactId);
        startActivity(intent);
    }
}