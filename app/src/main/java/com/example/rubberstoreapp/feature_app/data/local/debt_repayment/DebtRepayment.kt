package com.example.rubberstoreapp.feature_app.data.local.debt_repayment

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import java.util.Date


@Entity(tableName = "DebtRepayment")
data class DebtRepayment(
    @PrimaryKey(autoGenerate = true)
    val debtRepaymentId: Int,
    val date: Date,
    @Embedded
    val customer: Customer,
    val amount: Double

)
