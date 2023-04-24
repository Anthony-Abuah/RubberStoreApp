package com.example.rubberstoreapp.feature_app.data.repository

import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.data.local.customer.CustomerDao
import com.example.rubberstoreapp.feature_app.domain.repository.CustomerRepository
import com.example.rubberstoreapp.feature_app.domain.repository.Customers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerRepositoryImpl(
    private val customerDao: CustomerDao
) : CustomerRepository {
    override fun allCustomers(): Flow<Customers>  {
        return customerDao.allCustomers()
    }

    override suspend fun getCustomer(customerName: String): Customer? {
        return customerDao.getCustomer(customerName)
    }

    override suspend fun getCustomerByCustomerId(customerId: Int): Customer {
        return customerDao.getCustomerByCustomerId(customerId)
    }

    override suspend fun addCustomer(customer: Customer) {
        return customerDao.addCustomer(customer)
    }

    override suspend fun updateCustomer(
        customerId: Int,
        customerName: String,
        customerContact: String,
        customerLocation: String,
        customerInfo: String
    ) {
        return customerDao.updateCustomer(customerId, customerName, customerContact, customerLocation, customerInfo)
    }


    override suspend fun deleteCustomer(customer: Customer) {
        return customerDao.deleteCustomer(customer)
    }

    override suspend fun removeCustomer(customerId: Int) {
        return customerDao.removeCustomer(customerId)
    }

}