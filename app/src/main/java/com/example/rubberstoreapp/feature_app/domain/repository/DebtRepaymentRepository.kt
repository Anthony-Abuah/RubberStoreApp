package com.example.rubberstoreapp.feature_app.domain.repository

import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.data.local.debt_repayment.DebtRepayment
import com.example.rubberstoreapp.feature_app.data.local.room_types.DateAmount
import com.example.rubberstoreapp.feature_app.data.local.room_types.NameAmount
import kotlinx.coroutines.flow.Flow
import java.util.Date

typealias DebtRepayments = List<DebtRepayment>

typealias CustomerNameDebtRepayment = List<NameAmount>

typealias DebtRepaymentDateValue = List<DateAmount>

interface DebtRepaymentRepository {

    fun allDebtRepayments(): Flow<DebtRepayments>

    fun getAllTimeDebtRepaymentDateValues(): Flow<DebtRepaymentDateValue>

    fun getDurationalDebtRepaymentDateValues(firstDate: Date, lastDate: Date): Flow<DebtRepaymentDateValue>

    fun getAllTimeCustomerNameDebtRepayment(): Flow<CustomerNameDebtRepayment>

    fun getDurationalCustomerNameDebtRepayment(firstDate: Date, lastDate: Date): Flow<CustomerNameDebtRepayment>

    suspend fun addDebtRepayment(debtRepayment: DebtRepayment)

    suspend fun updateDebtRepayment(debtRepayment: DebtRepayment)

    suspend fun deleteDebtRepayment(debtRepayment: DebtRepayment)

    suspend fun removeDebtRepayment(debtRepaymentId: Int)

    suspend fun getDebtRepayment(debtRepaymentId: Int): DebtRepayment

    suspend fun getSumOfDebtRepaymentByCustomer(customerName: String): Double?

    suspend fun getTotalSumOfDebtRepayment(): Double?

    suspend fun getSumOfDebtRepaymentBetweenDates(firstDate: Date, lastDate: Date): Double?

}

