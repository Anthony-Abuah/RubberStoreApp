package com.example.rubberstoreapp.feature_app.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.data.local.debt_repayment.DebtRepayment
import com.example.rubberstoreapp.feature_app.domain.repository.CustomerNameDebtRepayment
import com.example.rubberstoreapp.feature_app.domain.repository.DebtRepaymentDateValue
import com.example.rubberstoreapp.feature_app.domain.repository.DebtRepaymentRepository
import com.example.rubberstoreapp.feature_app.domain.repository.DebtRepayments
import com.example.rubberstoreapp.feature_app.presentation.view_model.states.CustomerState
import com.example.rubberstoreapp.feature_app.presentation.view_model.states.DebtRepaymentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DebtRepaymentViewModel @Inject constructor(
    private val debtRepaymentRepository: DebtRepaymentRepository
): ViewModel() {
    var debtRepayment by mutableStateOf(DebtRepayment(0,Date(), Customer(0, NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE), 0.0))
        private set


    var customerName by mutableStateOf(CustomerState())
        private set

    var openDialog by mutableStateOf(false)

    var deleteClicked by mutableStateOf(false)

    val allDebtRepayments = debtRepaymentRepository.allDebtRepayments()


    var allTimeDebtRepaymentDateValues by mutableStateOf(emptyFlow<DebtRepaymentDateValue>())
        private set

    var durationalDebtRepaymentDateValues by mutableStateOf(emptyFlow<DebtRepaymentDateValue>())
        private set

    var allTimeCustomerNameDebtRepayment by mutableStateOf(emptyFlow<CustomerNameDebtRepayment>())
        private set

    var durationalCustomerNameDebtRepayment by mutableStateOf(emptyFlow<CustomerNameDebtRepayment>())
        private set


    var sumOfDebtRepaymentByCustomer:Double? by mutableStateOf(0.0)
    private set

    //val sumOfDebtRepaymentByCustomer = _sumOfDebtRepaymentByCustomer

    var totalSumOfDebtRepayment by mutableStateOf(0.0)
    //val totalSumOfDebtRepayment = _totalSumOfDebtRepayment

    var sumOfDebtRepaymentBetweenDates by mutableStateOf(0.0)
        private set

    fun addDebtRepayment(debtRepayment: DebtRepayment) = viewModelScope.launch {
        debtRepaymentRepository.addDebtRepayment(debtRepayment)
    }

    fun updateDebtRepayment(debtRepayment: DebtRepayment) = viewModelScope.launch {
        debtRepaymentRepository.updateDebtRepayment(debtRepayment)
    }

    fun deleteDebtRepayment(debtRepayment: DebtRepayment) = viewModelScope.launch {
        debtRepaymentRepository.deleteDebtRepayment(debtRepayment)
    }

    fun getSumOfDebtRepaymentByCustomer(customerName: String) = viewModelScope.launch {
        sumOfDebtRepaymentByCustomer = debtRepaymentRepository.getSumOfDebtRepaymentByCustomer(customerName) ?:0.0
    }

    fun getTotalSumOfDebtRepayment() = viewModelScope.launch {
        totalSumOfDebtRepayment = debtRepaymentRepository.getTotalSumOfDebtRepayment() ?:0.0
    }

    fun getSumOfDebtRepaymentBetweenDates(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        sumOfDebtRepaymentBetweenDates = debtRepaymentRepository.getSumOfDebtRepaymentBetweenDates(firstDate, lastDate) ?:0.0
    }

    fun getAllTimeDebtRepaymentDateValues() = viewModelScope.launch {
        allTimeDebtRepaymentDateValues = debtRepaymentRepository.getAllTimeDebtRepaymentDateValues()
    }

    fun getAllTimeCustomerNameDebtRepayment() = viewModelScope.launch {
        allTimeCustomerNameDebtRepayment = debtRepaymentRepository.getAllTimeCustomerNameDebtRepayment()
    }

    fun getDurationalDebtRepaymentDateValues(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalDebtRepaymentDateValues = debtRepaymentRepository.getDurationalDebtRepaymentDateValues(firstDate, lastDate)
    }

    fun getDurationalCustomerNameDebtRepayment(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalCustomerNameDebtRepayment = debtRepaymentRepository.getDurationalCustomerNameDebtRepayment(firstDate, lastDate)
    }


    fun removeDebtRepayment(debtRepaymentId: Int) = viewModelScope.launch {
        debtRepaymentRepository.removeDebtRepayment(debtRepaymentId)
    }

    fun getDebtRepayment(debtRepaymentId: Int) = viewModelScope.launch {
        debtRepayment = debtRepaymentRepository.getDebtRepayment(debtRepaymentId)
        customerName = customerName.copy(
            customerName = debtRepayment.customer.customerName
        )
    }


    fun updateDebtRepaymentAmount(amount: Double) {
        debtRepayment = debtRepayment.copy(
            amount = amount
        )
    }

    fun updateDebtRepaymentDate(date: Date) {
        debtRepayment = debtRepayment.copy(
            date = date
        )
    }
    fun updateDebtRepaymentCustomer(customer: Customer) {
        debtRepayment = debtRepayment.copy(
            customer = customer
        )
    }


    fun updateCustomerName(name: String) {
        customerName = customerName.copy(
            customerName = name
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