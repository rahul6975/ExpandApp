package com.rahul.expandapp.viewModel

import androidx.lifecycle.ViewModel
import com.rahul.expandapp.model.Transaction

class TransactionsViewModel : ViewModel() {

    var uniqueList = listOf<Transaction>()

    fun setTransactionList(newList: List<Transaction>) {
        val groupedTransactions = newList.groupBy { it.mid }.mapValues { (_, transactions) ->
            val distinctTids = transactions.flatMap { listOf(it.tid) }
                .distinct()
            distinctTids.map { tid ->
                val firstTransactionWithTid = transactions.first { it.tid == tid }
                Transaction(
                    firstTransactionWithTid.mid,
                    tid,
                    firstTransactionWithTid.amount,
                    firstTransactionWithTid.narration
                )
            }
        }
        uniqueList = groupedTransactions.values.flatten()
    }
}