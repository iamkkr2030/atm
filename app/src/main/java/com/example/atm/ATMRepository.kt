package com.example.atm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.atm.ATMDatabase
import com.example.atm.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ATMRepository(context: Context) {
    private val db = ATMDatabase.getInstance(context)
    private val atmDao = db.atmDao()

    val currentAmount: MutableLiveData<Int> = MutableLiveData(0)
    val transactionHistory: MutableLiveData<List<Transaction>> = MutableLiveData(emptyList())

    fun addAmount(amount: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val newAmount = (currentAmount.value ?: 0) + amount
            atmDao.updateAmount(newAmount)
            currentAmount.postValue(newAmount)
        }
    }

    fun addTransaction(transaction: Transaction) {
        CoroutineScope(Dispatchers.IO).launch {
            atmDao.insertTransaction(transaction)
            transactionHistory.postValue(transactionHistory.value?.plus(transaction))
        }
    }

    fun getCurrentAmount(): Int = runBlocking {
        return@runBlocking atmDao.getCurrentAmountValue() ?: 0
    }
}