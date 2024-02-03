package com.example.contactapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private MyRepository myRepository;
    private LiveData<List<Contact>> allContacts;

    private LiveData<Contact> singleContact;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
        allContacts = myRepository.getAllContacts();
    }

    public void insert(String firstName, String lastName, String phoneNumber) {
        myRepository.insert(firstName, lastName, phoneNumber);
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    public LiveData<Contact> getSingleContact(long contactId) {
        singleContact = myRepository.getContactById(contactId);
        return singleContact;
    }
}
