package com.ghostdev.PhoneBookApp.repo

import androidx.lifecycle.LiveData
import com.ghostdev.PhoneBookApp.database.ContactDB
import com.ghostdev.PhoneBookApp.models.ContactsModel

class ContactsRepo(private val contactDB: ContactDB) {

    suspend fun addContact(contact: ContactsModel) {
        contactDB.contactsDao().addContact(contact)
    }

    suspend fun editContact(contact: ContactsModel) {
        contactDB.contactsDao().editContact(contact)
    }

    suspend fun deleteContact(contact: ContactsModel) {
        contactDB.contactsDao().deleteContact(contact)
    }

    fun getAllContacts(): LiveData<List<ContactsModel>> {
       return contactDB.contactsDao().getAllContacts()
    }

    suspend fun getContact(id: Int): ContactsModel {
        return contactDB.contactsDao().getContact(id)
    }
}