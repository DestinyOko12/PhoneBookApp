package com.ghostdev.PhoneBookApp.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghostdev.PhoneBookApp.models.ContactsModel
import com.ghostdev.PhoneBookApp.repo.ContactsRepo
import kotlinx.coroutines.launch

class MainActivityViewModel(private val contactsRepo: ContactsRepo): ViewModel(), Observable {

    lateinit var allContacts: LiveData<List<ContactsModel>>

    init {
        getAllContacts()
    }

    private fun getAllContacts() {
        viewModelScope.launch {
            allContacts = contactsRepo.getAllContacts()
        }
    }

    fun addContact(contact: ContactsModel) {
        viewModelScope.launch {
            contactsRepo.addContact(contact)
            getAllContacts()
        }
    }

    fun deleteContact(contact: ContactsModel) {
        viewModelScope.launch {
            contactsRepo.deleteContact(contact)
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}