package com.example.rubberstoreapp.feature_app.data.local.debt_repayment

import androidx.room.*
import com.example.rubberstoreapp.core.util.Constants.DEBT_REPAYMENT_TABLE
import com.example.rubberstoreapp.feature_app.domain.repository.CustomerNameDebtRepayment
import com.example.rubberstoreapp.feature_app.domain.repository.DebtDateValues
import com.example.rubberstoreapp.feature_app.domain.repository.DebtRepaymentDateValue
import com.example.rubberstoreapp.feature_app.domain.repository.DebtRepayments
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface DebtRepaymentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDebtRepayment(debtRepayment: DebtRepayment)

    @Query("SELECT * FROM $DEBT_REPAYMENT_TABLE ORDER BY date DESC")
    fun allDebtRepayments(): Flow<DebtRepayments>

    @Update
    suspend fun updateDebtRepayment(debtRepayment: DebtRepayment)

    @Delete
    suspend fun deleteDebtRepayment(debtRepayment: DebtRepayment)

    @Query("DELETE FROM $DEBT_REPAYMENT_TABLE WHERE debtRepaymentId LIKE :debtRepaymentId")
    suspend fun removeDebtRepayment(debtRepaymentId: Int)

    @Query("SELECT * FROM $DEBT_REPAYMENT_TABLE WHERE debtRepaymentId LIKE :debtRepaymentId")
    suspend fun getDebtRepayment(debtRepaymentId: Int): DebtRepayment

    @Query("SELECT SUM(amount) FROM $DEBT_REPAYMENT_TABLE WHERE customerName LIKE :customerName")
    suspend fun getSumOfDebtRepaymentByCustomer(customerName: String): Double?

    @Query("SELECT SUM(amount) FROM $DEBT_REPAYMENT_TABLE")
    suspend fun getTotalSumOfDebtRepayment(): Double?

    @Query("SELECT SUM(amount) FROM $DEBT_REPAYMENT_TABLE WHERE date BETWEEN :firstDate AND :lastDate")
    suspend fun getSumOfDebtRepaymentBetweenDates(firstDate: Date, lastDate: Date): Double?

    @Query("SELECT date AS date, SUM(amount) AS amount FROM $DEBT_REPAYMENT_TABLE GROUP BY date ORDER BY date DESC")
    fun getAllTimeDebtRepaymentDateValues(): Flow<DebtRepaymentDateValue>

    @Query("SELECT date AS date, SUM(amount) AS amount FROM $DEBT_REPAYMENT_TABLE WHERE date BETWEEN :firstDate AND :lastDate GROUP BY date ORDER BY date DESC")
    fun getDurationalDebtRepaymentDateValues(firstDate: Date, lastDate: Date): Flow<DebtRepaymentDateValue>

    @Query("SELECT customerName AS name, SUM(amount) AS amount FROM $DEBT_REPAYMENT_TABLE GROUP BY customerName ORDER BY amount DESC")
    fun getAllTimeCustomerNameDebtRepayment(): Flow<CustomerNameDebtRepayment>

    @Query("SELECT customerName AS name, SUM(amount) AS amount FROM $DEBT_REPAYMENT_TABLE WHERE date BETWEEN :firstDate AND :lastDate GROUP BY customerName ORDER BY amount DESC")
    fun getDurationalCustomerNameDebtRepayment(firstDate: Date, lastDate: Date): Flow<CustomerNameDebtRepayment>

}