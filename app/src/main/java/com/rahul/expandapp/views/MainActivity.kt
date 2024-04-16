package com.rahul.expandapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.expandapp.R
import com.rahul.expandapp.databinding.ActivityMainBinding
import com.rahul.expandapp.viewModel.Repository
import com.rahul.expandapp.viewModel.TransactionsViewModel


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var repository: Repository
    lateinit var viewModel: TransactionsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        repository = Repository(this)
        viewModel = ViewModelProvider(this)[TransactionsViewModel::class.java]
        viewModel.setTransactionList(repository.getAllTransactions())
        setupRV()

    }

    private fun setupRV() {
        val allList = viewModel.uniqueList
        val adapter = ExpandableAdapter(allList)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
    }
}