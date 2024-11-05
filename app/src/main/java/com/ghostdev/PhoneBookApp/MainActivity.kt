package com.ghostdev.PhoneBookApp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghostdev.PhoneBookApp.database.ContactDB
import com.ghostdev.PhoneBookApp.databinding.ActivityMainBinding
import com.ghostdev.PhoneBookApp.models.ContactsModel
import com.ghostdev.PhoneBookApp.repo.ContactsRepo
import com.ghostdev.PhoneBookApp.viewmodels.MainActivityViewModel
import com.ghostdev.PhoneBookApp.viewmodels.viewmodelfactory.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        enableEdgeToEdge()
        //Change 1
        //Change 2

        val dao = ContactDB.getInstance(this)
        val repository = ContactsRepo(dao)
        val factory = MainActivityViewModelFactory(repository)
        
        mainActivityViewModel = ViewModelProvider(this, factory) [MainActivityViewModel::class.java]

        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.addContactButton)

        button.setOnClickListener {
            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)
        }
        initRecyclerView()
    }
    
    private fun initRecyclerView() {
        binding.recyeleview.layoutManager = LinearLayoutManager(this)
        displayContactList()
    }

    private fun displayContactList() {
        mainActivityViewModel.allContacts.observe(this, Observer {
            binding.recyeleview.adapter = RecyclerViewAdapter(it, {selectedItem: ContactsModel -> listItemClicked(selectedItem)}
                , {selectedItem: ContactsModel -> deleteItem(selectedItem)})
        })
    }

    private fun listItemClicked(selectedItem: ContactsModel) {
        val intent = Intent(this, EditContact::class.java)
        intent.putExtra("id", selectedItem.id)
        startActivity(intent)
    }

    private fun deleteItem(selectedItem: ContactsModel) {
        mainActivityViewModel.deleteContact(selectedItem)
    }
}