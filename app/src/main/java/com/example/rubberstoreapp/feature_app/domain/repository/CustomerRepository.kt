package com.example.rubberstoreapp.feature_app.domain.repository

import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import kotlinx.coroutines.flow.Flow

typealias Customers = List<Customer>

interface CustomerRepository {

    fun allCustomers(): Flow<Customers>

    suspend fun getCustomer(customerName: String): Customer?

    suspend fun getCustomerByCustomerId(customerId: Int): Customer

    suspend fun addCustomer(customer: Customer)

    suspend fun updateCustomer(customerId: Int, customerName: String, customerContact: String, customerLocation: String, customerInfo: String)

    suspend fun deleteCustomer(customer: Customer)

    suspend fun removeCustomer(customerId: Int)
}

