package com.example.rubberstoreapp.feature_app.data.local.customer

import androidx.room.*
import com.example.rubberstoreapp.core.util.Constants.CUSTOMER_TABLE
import com.example.rubberstoreapp.feature_app.domain.repository.Customers
import kotlinx.coroutines.flow.Flow
import java.sql.Date


@Dao
interface CustomerDao {

    @Query("SELECT * FROM $CUSTOMER_TABLE ORDER BY customerId DESC ")
    fun allCustomers(): Flow<Customers>

    @Query("SELECT * FROM $CUSTOMER_TABLE WHERE customerName LIKE :customerName")
    suspend fun getCustomer(customerName: String): Customer?

    @Query("SELECT * FROM $CUSTOMER_TABLE WHERE customerId LIKE :customerId")
    suspend fun getCustomerByCustomerId(customerId: Int): Customer

    @Query("DELETE FROM $CUSTOMER_TABLE WHERE customerId LIKE :id")
    suspend fun removeCustomer(id: Int)

    @Query("UPDATE $CUSTOMER_TABLE SET customerName=:customerName, customerContact=:customerContact, customerLocation=:customerLocation, customerInfo=:customerInfo WHERE customerId LIKE :customerId")
    suspend fun updateCustomer(customerId: Int, customerName: String, customerContact: String, customerLocation: String, customerInfo: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)

}