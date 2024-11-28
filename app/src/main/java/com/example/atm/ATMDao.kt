package com.example.atm

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ATMDao {
    @Query("SELECT amount FROM Amount LIMIT 1")
    fun getCurrentAmount(): LiveData<Int>

    @Query("SELECT * FROM `Transaction`")
    fun getTransactionHistory(): LiveData<List<Transaction>>

    @Query("UPDATE Amount SET amount = :amount")
    suspend fun updateAmount(amount: Int)

    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT amount FROM Amount LIMIT 1")
    suspend fun getCurrentAmountValue(): Int?
}
