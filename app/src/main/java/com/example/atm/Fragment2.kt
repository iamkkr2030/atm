package com.example.atm

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.atm.R

class Fragment2 : Fragment(R.layout.activity_fragment2) {
    private val atmViewModel: ATMViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentAmountTextView = view.findViewById<TextView>(R.id.current_amount_text)
        val amountInputEditText = view.findViewById<EditText>(R.id.amount_input)
        val depositButton = view.findViewById<Button>(R.id.deposit_button)
        val withdrawButton = view.findViewById<Button>(R.id.withdraw_button)

        atmViewModel.currentAmount.observe(viewLifecycleOwner) { amount ->
            currentAmountTextView.text = "$amount 원"
        }

        depositButton.setOnClickListener {
            val amount = amountInputEditText.text.toString().toIntOrNull() ?: 0
            if (amount > 0) {
                atmViewModel.deposit(amount)
                amountInputEditText.text.clear()
            }
        }

        withdrawButton.setOnClickListener {
            val amount = amountInputEditText.text.toString().toIntOrNull() ?: 0
            if (amount > 0 && atmViewModel.currentAmount.value != null && atmViewModel.currentAmount.value!! >= amount) {
                atmViewModel.withdraw(amount)
                amountInputEditText.text.clear()
            } else {
                Toast.makeText(requireContext(), "출금이 불가능합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}