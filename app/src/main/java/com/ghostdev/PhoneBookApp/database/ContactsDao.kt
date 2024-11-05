package com.ghostdev.PhoneBookApp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ghostdev.PhoneBookApp.models.ContactsModel

@Dao
interface ContactsDao {

    @Insert
    suspend fun addContact(contact: ContactsModel)

    @Update
    suspend fun editContact(contact: ContactsModel)

    @Delete
    suspend fun deleteContact(contact: ContactsModel)

    @Query("SELECT * FROM contacts_table")
    fun getAllContacts(): LiveData<List<ContactsModel>>

    @Query("SELECT * FROM contacts_table WHERE :id = id")
    suspend fun getContact(id: Int): ContactsModel
}