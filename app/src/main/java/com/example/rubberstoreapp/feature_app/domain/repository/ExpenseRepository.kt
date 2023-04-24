package com.example.rubberstoreapp.feature_app.domain.repository

import com.example.rubberstoreapp.feature_app.data.local.room_types.DateAmount
import com.example.rubberstoreapp.feature_app.data.local.expenses.Expense
import com.example.rubberstoreapp.feature_app.data.local.room_types.NameAmount
import kotlinx.coroutines.flow.Flow
import java.util.Date

typealias Expenses = List<Expense>
typealias ExpenseDateValues = List<DateAmount>
typealias ExpenseNameValues = List<NameAmount>

interface ExpenseRepository {

    fun allExpenses(): Flow<Expenses>

    fun getAllTimeExpenseNameValues(): Flow<ExpenseNameValues>

    fun getDurationalExpenseNameValues(firstDate: Date, lastDate: Date): Flow<ExpenseNameValues>

    fun getAllTimeExpenseDateValue(): Flow<ExpenseDateValues>

    fun getDurationalExpenseDateValue(firstDate: Date, lastDate: Date): Flow<ExpenseDateValues>

    suspend fun addExpense(expense: Expense)

    suspend fun updateExpense(expense: Expense)

    suspend fun deleteExpense(expense: Expense)

    suspend fun removeExpense(expenseId: Int)

    suspend fun getExpense(expenseId: Int): Expense

    suspend fun getTotalSumOfAllExpenses(): Double?

    suspend fun getSumOfExpensesBetweenDates(firstDate: Date, lastDate: Date): Double?

}

