package com.example.atm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.atm.R

class Fragment3 : Fragment() {

    private lateinit var depositListTextView: TextView
    private lateinit var withdrawListTextView: TextView
    private val atmViewModel: ATMViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        depositListTextView = view.findViewById(R.id.deposit_list)
        withdrawListTextView = view.findViewById(R.id.withdraw_list)

        // Observe transaction history from the ViewModel
        atmViewModel.transactionHistory.observe(viewLifecycleOwner) { transactions ->
            val depositTransactions = transactions.filter { it.description.contains("입금") }
            val withdrawTransactions = transactions.filter { it.description.contains("출금") }

            depositListTextView.text = depositTransactions.joinToString("\n") { it.description }
            withdrawListTextView.text = withdrawTransactions.joinToString("\n") { it.description }
        }
    }
}