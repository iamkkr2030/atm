package com.example.atm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking

class ATMViewModel(application: Application) : AndroidViewModel(application) {
    private val atmRepository = ATMRepository(application)
    val currentAmount: MutableLiveData<Int> = atmRepository.currentAmount
    val transactionHistory: MutableLiveData<List<Transaction>> = atmRepository.transactionHistory

    fun deposit(amount: Int) {
        if (amount > 0) {
            atmRepository.addAmount(amount)
            atmRepository.addTransaction(Transaction(description = "$amount 원 입금"))
        }
    }

    fun withdraw(amount: Int): Boolean {
        if (amount > 0 && currentAmount.value != null && currentAmount.value!! >= amount) {
            atmRepository.addAmount(-amount)
            atmRepository.addTransaction(Transaction(description = "$amount 원 출금"))

            return true
        }
        return false
    }

    fun getCurrentAmountValue(): Int {
        return currentAmount.value ?: 0
    }
}