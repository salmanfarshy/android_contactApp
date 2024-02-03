package com.example.contactapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class MyRepository {
    private AppDatabase appDatabase;
    private LiveData<List<Contact>> allContacts;
    public MyRepository(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "contacts-database").build();
        allContacts = appDatabase.contactDao().getAllContacts();
    }

    public void insert(String firstName, String lastName, String phoneNumber) {
        new InsertAsyncTask(appDatabase).execute(new Contact(firstName, lastName, phoneNumber));
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    public LiveData<Contact> getContactById(long contactId) {
        return appDatabase.contactDao().getContactById(contactId);
    }

    private static class InsertAsyncTask extends AsyncTask<Contact, Void, Void> {
        private ContactDao contactDao;

        InsertAsyncTask(AppDatabase appDatabase) {
            contactDao = appDatabase.contactDao();
        }

        @Override
        protected Void doInBackground(Contact... entities) {
            // Perform database operations on a background thread
            contactDao.insert(entities[0]);
            return null;
        }
    }
}
