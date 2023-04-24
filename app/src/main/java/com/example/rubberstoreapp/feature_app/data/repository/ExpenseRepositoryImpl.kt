package com.example.rubberstoreapp.feature_app.data.repository

import com.example.rubberstoreapp.feature_app.data.local.expenses.Expense
import com.example.rubberstoreapp.feature_app.data.local.expenses.ExpenseDao
import com.example.rubberstoreapp.feature_app.domain.repository.ExpenseDateValues
import com.example.rubberstoreapp.feature_app.domain.repository.ExpenseNameValues
import com.example.rubberstoreapp.feature_app.domain.repository.ExpenseRepository
import com.example.rubberstoreapp.feature_app.domain.repository.Expenses
import kotlinx.coroutines.flow.Flow
import java.util.*
import kotlin.math.exp

class ExpenseRepositoryImpl(
    private val expenseDao: ExpenseDao
) : ExpenseRepository {
    override fun allExpenses(): Flow<Expenses>  {
        return expenseDao.allExpenses()
    }

    override fun getAllTimeExpenseNameValues(): Flow<ExpenseNameValues> {
        return expenseDao.getAllTimeExpenseNameValues()
    }

    override fun getDurationalExpenseNameValues(
        firstDate: Date,
        lastDate: Date
    ): Flow<ExpenseNameValues> {
        return expenseDao.getDurationalExpenseNameValues(firstDate, lastDate)
    }

    override fun getAllTimeExpenseDateValue(): Flow<ExpenseDateValues> {
        return expenseDao.getAllTimeExpenseDateValue()
    }

    override fun getDurationalExpenseDateValue(
        firstDate: Date,
        lastDate: Date
    ): Flow<ExpenseDateValues> {
        return expenseDao.getDurationalExpenseDateValue(firstDate, lastDate)
    }


    override suspend fun addExpense(expense: Expense) {
        return expenseDao.addExpense(expense)
    }

    override suspend fun updateExpense(expense: Expense) {
        return expenseDao.updateExpense(expense)
    }

    override suspend fun deleteExpense(expense: Expense) {
        return expenseDao.deleteExpense(expense)
    }

    override suspend fun removeExpense(expenseId: Int) {
        return expenseDao.removeExpense(expenseId)
    }

    override suspend fun getExpense(expenseId: Int): Expense {
        return expenseDao.getExpense(expenseId)
    }

    override suspend fun getTotalSumOfAllExpenses(): Double? {
        return expenseDao.totalSumOfAllExpenses()
    }

    override suspend fun getSumOfExpensesBetweenDates(firstDate: Date, lastDate: Date): Double? {
        return expenseDao.sumOfExpensesBetweenDates(firstDate, lastDate)
    }

}