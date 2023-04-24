package com.example.rubberstoreapp.feature_app.domain.repository

import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import kotlinx.coroutines.flow.Flow

typealias Banks = List<Bank>

interface BankRepository {

    fun allBanks(): Flow<Banks>

    suspend fun getBank(bankName: String): Bank

    suspend fun getBankById(bankId: Int): Bank

    suspend fun addBank(bank: Bank)

    suspend fun updateBank(bank: Bank)

    suspend fun deleteBank(bank: Bank)

    suspend fun removeBank(bankId: Int)
}

