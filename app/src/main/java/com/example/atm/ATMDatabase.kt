package com.example.atm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.atm.ATMDao
import com.example.atm.Amount
import com.example.atm.Transaction

@Database(entities = [Amount::class, Transaction::class], version = 1)
abstract class ATMDatabase : RoomDatabase() {
    abstract fun atmDao(): ATMDao

    companion object {
        @Volatile
        private var INSTANCE: ATMDatabase? = null

        fun getInstance(context: Context): ATMDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ATMDatabase::class.java,
                    "atm_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}