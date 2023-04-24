package com.example.rubberstoreapp.feature_app.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.data.local.debt.Debt
import com.example.rubberstoreapp.feature_app.domain.repository.CustomerNameDebt
import com.example.rubberstoreapp.feature_app.domain.repository.DebtDateValues
import com.example.rubberstoreapp.feature_app.domain.repository.DebtRepository
import com.example.rubberstoreapp.feature_app.presentation.view_model.states.CustomerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DebtViewModel @Inject constructor(
    private val debtRepository: DebtRepository
): ViewModel() {

    var debt by mutableStateOf(Debt(0, Date(), Customer(0, NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE), 0.0))
        private set

    var customerName by mutableStateOf(CustomerState())
        private set

    var openDialog by mutableStateOf(false)

    var deleteClicked by mutableStateOf(false)

    var totalSumOfAllDebts by mutableStateOf(0.0)
        private set


    var allTimeDebtDateValues by mutableStateOf(emptyFlow<DebtDateValues>())
        private set

    var durationalDebtDateValues by mutableStateOf(emptyFlow<DebtDateValues>())
        private set

    var allTimeCustomerNameDebt by mutableStateOf(emptyFlow<CustomerNameDebt>())
        private set

    var durationalCustomerNameDebt by mutableStateOf(emptyFlow<CustomerNameDebt>())
        private set



    var totalDebtOfCustomer by mutableStateOf(0.0)
    private set

    var totalSumOfDebtsBetweenDates by mutableStateOf(0.0)
    private set

    val allDebts = debtRepository.allDebts()


    fun addDebt(debt: Debt) = viewModelScope.launch {
        debtRepository.addDebt(debt)
    }

    fun getSumOfAllDebts() = viewModelScope.launch {
        totalSumOfAllDebts = debtRepository.getSumOfAllDebts() ?:0.0
    }

    fun getTotalDebtOfCustomer(customerName: String) = viewModelScope.launch {
        totalDebtOfCustomer = debtRepository.getDebtOfCustomer(customerName) ?:0.0
    }

    fun getTotalSumOfDebtsBetweenDates(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        totalSumOfDebtsBetweenDates = debtRepository.getSumOfDebtsBetweenDates(firstDate, lastDate) ?:0.0
    }

    fun getAllTimeDebtDateValues() = viewModelScope.launch {
        allTimeDebtDateValues = debtRepository.getAllTimeDebtDateValues()
    }

    fun getAllTimeCustomerNameDebt() = viewModelScope.launch {
        allTimeCustomerNameDebt = debtRepository.getAllTimeCustomerNameDebt()
    }

    fun getDurationalDebtDateValues(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalDebtDateValues = debtRepository.getDurationalDebtDateValues(firstDate, lastDate)
    }

    fun getDurationalCustomerNameDebt(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalCustomerNameDebt = debtRepository.getDurationalCustomerNameDebt(firstDate, lastDate)
    }

    fun updateDebt(debt: Debt) = viewModelScope.launch {
        debtRepository.updateDebt(debt)
    }

    fun deleteDebt(debt: Debt) = viewModelScope.launch {
        debtRepository.deleteDebt(debt)
    }

    fun removeDebt(debtId: Int) = viewModelScope.launch {
        debtRepository.removeDebt(debtId)
    }

    fun getDebt(debtId: Int) = viewModelScope.launch {
        debt = debtRepository.getDebt(debtId)
        customerName = customerName.copy(
            customerName = debt.customer.customerName
        )
    }

    fun updateDebtDate(date: Date) {
        debt = debt.copy(
            date = date
        )
    }
    fun updateDebtCustomer(customer: Customer) {
        debt = debt.copy(
            customer = customer
        )
    }

    fun updateCustomerName(name: String) {
        customerName = customerName.copy(
            customerName = name
        )
    }

    fun updateDebtAmount(debtAmount: Double) {
        debt = debt.copy(
            amount = debtAmount
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