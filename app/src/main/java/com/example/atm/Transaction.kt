package com.example.atm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Amount(@PrimaryKey val id: Int = 1, val amount: Int)

@Entity
data class Transaction(@PrimaryKey(autoGenerate = true) val id: Int = 0, val description: String)

