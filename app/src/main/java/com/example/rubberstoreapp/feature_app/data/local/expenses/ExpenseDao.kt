package com.example.rubberstoreapp.feature_app.data.local.expenses

import androidx.room.*
import com.example.rubberstoreapp.core.util.Constants.EXPENSE_TABLE
import com.example.rubberstoreapp.feature_app.domain.repository.ExpenseDateValues
import com.example.rubberstoreapp.feature_app.domain.repository.ExpenseNameValues
import com.example.rubberstoreapp.feature_app.domain.repository.Expenses
import kotlinx.coroutines.flow.Flow
import java.util.*


@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExpense(expense: Expense)

    @Query("SELECT * FROM $EXPENSE_TABLE ORDER BY date DESC")
    fun allExpenses(): Flow<Expenses>

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT SUM(expenseAmount) FROM $EXPENSE_TABLE")
    suspend fun totalSumOfAllExpenses(): Double?

    @Query("SELECT SUM(expenseAmount) FROM $EXPENSE_TABLE WHERE date BETWEEN :firstDate AND :lastDate")
    suspend fun sumOfExpensesBetweenDates(firstDate: Date, lastDate: Date): Double?

    @Query("DELETE FROM $EXPENSE_TABLE WHERE expenseId LIKE :expenseId")
    suspend fun removeExpense(expenseId: Int)

    @Query("SELECT * FROM $EXPENSE_TABLE WHERE expenseId LIKE :expenseId")
    suspend fun getExpense(expenseId: Int): Expense

    @Query("SELECT expenseName AS name, SUM(expenseAmount) AS amount FROM $EXPENSE_TABLE GROUP BY expenseName")
    fun getAllTimeExpenseNameValues(): Flow<ExpenseNameValues>

    @Query("SELECT expenseName AS name, SUM(expenseAmount) AS amount FROM $EXPENSE_TABLE WHERE date BETWEEN :firstDate AND :lastDate GROUP BY expenseName")
    fun getDurationalExpenseNameValues(firstDate: Date, lastDate: Date): Flow<ExpenseNameValues>

    @Query("SELECT date AS date, SUM(expenseAmount) AS amount FROM $EXPENSE_TABLE GROUP BY date")
    fun getAllTimeExpenseDateValue(): Flow<ExpenseDateValues>

    @Query("SELECT date AS date, SUM(expenseAmount) AS amount FROM $EXPENSE_TABLE WHERE date BETWEEN :firstDate AND :lastDate GROUP BY date")
    fun getDurationalExpenseDateValue(firstDate: Date, lastDate: Date): Flow<ExpenseDateValues>

/*
    @Query("SELECT DateCollected AS date, SUM(EggQuantity) AS quantity FROM EggInventoryAddition WHERE DateCollected BETWEEN :firstDate AND :lastDate GROUP BY DateCollected ")
    suspend fun eggAdditionsByDate(firstDate: java.sql.Date, lastDate: java.sql.Date): List<DateQuantityInteger>
*/



}