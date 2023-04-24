package com.example.rubberstoreapp.feature_app.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.domain.model.RegistrationInfo
import com.example.rubberstoreapp.feature_app.domain.repository.CustomerRepository
import com.example.rubberstoreapp.feature_app.presentation.view_model.states.CustomerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val customerRepository: CustomerRepository
): ViewModel() {


    var customer by mutableStateOf(Customer(0, NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE))
        private set

    var customerByCustomerId by mutableStateOf(Customer(0, NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE))
        private set

    var openDialog by mutableStateOf(false)

    var deleteClicked by mutableStateOf(false)

    val customers = customerRepository.allCustomers()

    fun getCustomer(customerName: String) = viewModelScope.launch {
        customer = customerRepository.getCustomer(customerName) ?: Customer(0, NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE)
    }

    fun getCustomerByCustomerId(customerId: Int) = viewModelScope.launch {
        customerByCustomerId = customerRepository.getCustomerByCustomerId(customerId)
    }


    fun addCustomer(customer: Customer) = viewModelScope.launch {
        customerRepository.addCustomer(customer)
    }

    fun updateCustomer(customerId: Int, customerName: String, customerContact: String, customerLocation: String, customerInfo: String) = viewModelScope.launch {
        customerRepository.updateCustomer(customerId, customerName, customerContact, customerLocation, customerInfo)
    }

    fun deleteCustomer(customer: Customer) = viewModelScope.launch {
        customerRepository.deleteCustomer(customer)
    }

    fun removeCustomer(customerId: Int) = viewModelScope.launch {
        customerRepository.removeCustomer(customerId)
    }

    fun updateCustomerName(name: String) {
        customerByCustomerId = customerByCustomerId.copy(
            customerName = name
        )
    }
    fun updateCustomerContact(contact: String) {
        customerByCustomerId = customerByCustomerId.copy(
            customerContact = contact
        )
    }
    fun updateCustomerLocation(location: String) {
        customerByCustomerId = customerByCustomerId.copy(
            customerLocation = location
        )
    }
    fun updateCustomerInfo(info: String) {
        customerByCustomerId = customerByCustomerId.copy(
            customerInfo = info
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