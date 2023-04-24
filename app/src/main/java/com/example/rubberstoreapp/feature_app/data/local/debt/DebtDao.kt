package com.example.rubberstoreapp.feature_app.data.local.debt

import androidx.room.*
import com.example.rubberstoreapp.core.util.Constants.DEBT_TABLE
import com.example.rubberstoreapp.feature_app.domain.repository.CustomerNameDebt
import com.example.rubberstoreapp.feature_app.domain.repository.DebtDateValues
import com.example.rubberstoreapp.feature_app.domain.repository.Debts
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface DebtDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDebt(debt: Debt)

    @Query("SELECT * FROM $DEBT_TABLE ORDER BY date DESC")
    fun allDebts(): Flow<Debts>

    @Update
    suspend fun updateDebt(debt: Debt)

    @Delete
    suspend fun deleteDebt(debt: Debt)

    @Query("DELETE FROM $DEBT_TABLE WHERE debtId LIKE :debtId")
    suspend fun removeDebt(debtId: Int)

    @Query("SELECT * FROM $DEBT_TABLE WHERE debtId LIKE :debtId")
    suspend fun getDebt(debtId: Int): Debt

    @Query("SELECT SUM(amount) FROM $DEBT_TABLE WHERE customerName LIKE :customerName")
    suspend fun getDebtOfCustomer(customerName: String): Double?

    @Query("SELECT SUM(amount) FROM $DEBT_TABLE ")
    suspend fun getSumOfAllDebts(): Double?

    @Query("SELECT SUM(amount) FROM $DEBT_TABLE WHERE date BETWEEN :firstDate AND :lastDate")
    suspend fun getSumOfDebtsBetweenDates(firstDate: Date, lastDate: Date): Double?

    @Query("SELECT date AS date, SUM(amount) AS amount FROM $DEBT_TABLE GROUP BY date ORDER BY date DESC")
    fun getAllTimeDebtDateValues(): Flow<DebtDateValues>

    @Query("SELECT date AS date, SUM(amount) AS amount FROM $DEBT_TABLE WHERE date BETWEEN :firstDate AND :lastDate GROUP BY date ORDER BY date DESC")
    fun getDurationalDebtDateValues(firstDate: Date, lastDate: Date): Flow<DebtDateValues>

    @Query("SELECT customerName AS name, SUM(amount) AS amount FROM $DEBT_TABLE GROUP BY customerName ORDER BY amount DESC")
    fun getAllTimeCustomerNameDebt(): Flow<CustomerNameDebt>

    @Query("SELECT customerName AS name, SUM(amount) AS amount FROM $DEBT_TABLE WHERE date BETWEEN :firstDate AND :lastDate GROUP BY date ORDER BY amount DESC")
    fun getDurationalCustomerNameDebt(firstDate: Date, lastDate: Date): Flow<CustomerNameDebt>


}