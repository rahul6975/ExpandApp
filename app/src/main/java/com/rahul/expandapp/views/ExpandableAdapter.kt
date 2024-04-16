package com.rahul.expandapp.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahul.expandapp.R
import com.rahul.expandapp.model.Transaction

class ExpandableAdapter(
    private val transactions: List<Transaction>,
) : RecyclerView.Adapter<ExpandableAdapter.TransactionViewHolder>() {

    private val expandedStates = mutableMapOf<Int, Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction, position)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val midText: TextView = itemView.findViewById(R.id.mid_text)
        private val tidText: TextView = itemView.findViewById(R.id.tid_text)
        private val amtText: TextView = itemView.findViewById(R.id.amount_text)
        private val nrrText: TextView = itemView.findViewById(R.id.narration_text)
        private val icon: ImageView = itemView.findViewById(R.id.arrow_icon)

        init {
            itemView.setOnClickListener {
                val transaction = transactions[adapterPosition]
                val isExpanded = expandedStates[transaction.mid] ?: false
                expandedStates[transaction.mid] = !isExpanded
                notifyDataSetChanged()
            }
        }

        fun bind(transaction: Transaction, position: Int) {
            when (position) {
                1 -> {
                    midText.text = ""
                }
                else -> {
                    midText.text = "Mid ${transaction.mid}"
                }
            }
            tidText.text = "Tid: ${transaction.tid}"
            amtText.text = "Amount: ${transaction.amount}"
            nrrText.text = "Narration: ${transaction.narration}"

            val isExpanded = expandedStates[transaction.mid] ?: false
            tidText.visibility = if (isExpanded) View.VISIBLE else View.GONE
            amtText.visibility = if (isExpanded) View.VISIBLE else View.GONE
            nrrText.visibility = if (isExpanded) View.VISIBLE else View.GONE
            if (isExpanded) {
                icon.setImageResource(R.drawable.arrow_up)

            } else {
                icon.setImageResource(R.drawable.arrow_down)

            }
        }
    }
}