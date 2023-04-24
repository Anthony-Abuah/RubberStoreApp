package com.example.rubberstoreapp.feature_app.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.expenses.Expense
import com.example.rubberstoreapp.feature_app.domain.repository.ExpenseDateValues
import com.example.rubberstoreapp.feature_app.domain.repository.ExpenseNameValues
import com.example.rubberstoreapp.feature_app.domain.repository.ExpenseRepository
import com.example.rubberstoreapp.feature_app.presentation.view_model.states.ExpenseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
): ViewModel() {
    var expense by mutableStateOf(Expense(0, Date(), NO_VALUE, 0.0))
        private set
    var openDialog by mutableStateOf(false)

    var deleteClicked by mutableStateOf(false)

    val allExpenses = expenseRepository.allExpenses()


    var allTimeExpenseNameValues by mutableStateOf(emptyFlow<ExpenseNameValues>())
        private set

    var durationalExpenseNameValues by mutableStateOf(emptyFlow<ExpenseNameValues>())
        private set

    var allTimeExpenseDateValue by mutableStateOf(emptyFlow<ExpenseDateValues>())
        private set

    var durationalExpenseDateValue by mutableStateOf(emptyFlow<ExpenseDateValues>())
        private set


    var totalSumOfAllExpenses by mutableStateOf(0.0)
        private set

    var sumOfExpensesBetweenDates by mutableStateOf(0.0)
        private set

    fun addExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.addExpense(expense)
    }

    fun updateExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.updateExpense(expense)
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.deleteExpense(expense)
    }

    fun removeExpense(expenseId: Int) = viewModelScope.launch {
        expenseRepository.removeExpense(expenseId)
    }

    fun getTotalSumOfAllExpenses() = viewModelScope.launch {
        totalSumOfAllExpenses = expenseRepository.getTotalSumOfAllExpenses() ?:0.0
    }

    fun getSumOfExpensesBetweenDates(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        sumOfExpensesBetweenDates = expenseRepository.getSumOfExpensesBetweenDates(firstDate, lastDate) ?:0.0
    }

    fun getExpense(expenseId: Int) = viewModelScope.launch {
        expense = expenseRepository.getExpense(expenseId)
    }

    fun getAllTimeExpenseDateValue() = viewModelScope.launch {
        allTimeExpenseDateValue = expenseRepository.getAllTimeExpenseDateValue()
    }

    fun getDurationalExpenseDateValue(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalExpenseDateValue = expenseRepository.getDurationalExpenseDateValue(firstDate, lastDate)
    }

    fun getAllTimeExpenseNameValues() = viewModelScope.launch {
        allTimeExpenseNameValues = expenseRepository.getAllTimeExpenseNameValues()
    }

    fun getDurationalExpenseNameValues(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalExpenseNameValues = expenseRepository.getDurationalExpenseNameValues(firstDate, lastDate)
    }

    fun updateExpenseName(expenseName: String) {
        expense = expense.copy(
            expenseName = expenseName
        )
    }

    fun updateExpenseDate(date: Date) {
        expense = expense.copy(
            date = date
        )
    }
    fun updateExpenseAmount(expenseAmount: Double) {
        expense = expense.copy(
            expenseAmount = expenseAmount
        )
    }

    fun deleteClicked() {
        deleteClicked = true
    }

    fun cancelClicked() {
        deleteClicked = false
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}