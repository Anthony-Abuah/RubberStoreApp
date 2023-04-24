package com.example.rubberstoreapp.feature_app.data.local.bank

import androidx.room.*
import com.example.rubberstoreapp.core.util.Constants
import com.example.rubberstoreapp.core.util.Constants.BANK_TABLE
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.domain.repository.Banks
import kotlinx.coroutines.flow.Flow


@Dao
interface BankDao {

    @Query("SELECT * FROM $BANK_TABLE ORDER BY bankId DESC ")
    fun allBanks(): Flow<Banks>

    @Query("SELECT * FROM $BANK_TABLE WHERE bankName LIKE :bankName")
    suspend fun getBank(bankName: String): Bank

    @Query("SELECT * FROM $BANK_TABLE WHERE bankId LIKE :bankId")
    suspend fun getBankById(bankId: Int): Bank

    @Query("DELETE FROM $BANK_TABLE WHERE bankId LIKE :bankId")
    suspend fun removeBank(bankId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBank(bank: Bank)

    @Update
    suspend fun updateBank(bank: Bank)

    @Delete
    suspend fun deleteBank(bank: Bank)

}