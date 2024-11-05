package com.ghostdev.PhoneBookApp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghostdev.PhoneBookApp.models.ContactsModel
import com.ghostdev.PhoneBookApp.repo.ContactsRepo
import kotlinx.coroutines.launch

class AddContactViewModel(private val contactsRepo: ContactsRepo): ViewModel() {

    fun addContact(contact: ContactsModel) {
        viewModelScope.launch {
            contactsRepo.addContact(contact)
        }
    }
}