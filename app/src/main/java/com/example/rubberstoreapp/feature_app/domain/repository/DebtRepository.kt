package com.example.rubberstoreapp.feature_app.domain.repository

import com.example.rubberstoreapp.feature_app.data.local.debt.Debt
import com.example.rubberstoreapp.feature_app.data.local.room_types.DateAmount
import com.example.rubberstoreapp.feature_app.data.local.room_types.NameAmount
import kotlinx.coroutines.flow.Flow
import java.util.*

typealias Debts = List<Debt>

typealias DebtDateValues = List<DateAmount>

typealias CustomerNameDebt = List<NameAmount>

interface DebtRepository {

    fun allDebts(): Flow<Debts>

    fun getAllTimeDebtDateValues(): Flow<DebtDateValues>

    fun getAllTimeCustomerNameDebt(): Flow<CustomerNameDebt>

    fun getDurationalDebtDateValues(firstDate: Date, lastDate: Date): Flow<DebtDateValues>

    fun getDurationalCustomerNameDebt(firstDate: Date, lastDate: Date): Flow<CustomerNameDebt>

    suspend fun addDebt(debt: Debt)

    suspend fun updateDebt(debt: Debt)

    suspend fun deleteDebt(debt: Debt)

    suspend fun removeDebt(debtId: Int)

    suspend fun getDebt(debtId: Int): Debt

    suspend fun getDebtOfCustomer(customerName: String): Double?

    suspend fun getSumOfAllDebts(): Double?

    suspend fun getSumOfDebtsBetweenDates(firstDate: Date, lastDate: Date): Double?
}

