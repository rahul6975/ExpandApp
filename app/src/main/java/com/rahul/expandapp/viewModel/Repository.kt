package com.rahul.expandapp.viewModel

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rahul.expandapp.R
import com.rahul.expandapp.model.Transaction
import java.io.InputStream

class Repository(private val context: Context) {

    fun getAllTransactions(): List<Transaction> {
        val gson = Gson()
        val inputStream: InputStream = context.resources.openRawResource(R.raw.transaction_data)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val jsonObject = gson.fromJson(jsonString, Map::class.java)

        val sortArray = jsonObject["sort"]

        val sortJsonString = gson.toJson(sortArray)

        val listType = object : TypeToken<List<Transaction>>() {}.type

        return gson.fromJson(sortJsonString, listType)
    }
}