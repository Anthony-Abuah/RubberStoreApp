package com.example.rubberstoreapp.feature_app.data.repository

import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import com.example.rubberstoreapp.feature_app.data.local.bank.BankDao
import com.example.rubberstoreapp.feature_app.domain.repository.BankRepository
import com.example.rubberstoreapp.feature_app.domain.repository.Banks
import kotlinx.coroutines.flow.Flow

class BankRepositoryImpl(
    private val bankDao: BankDao
) : BankRepository {
    override fun allBanks(): Flow<Banks>  {
        return bankDao.allBanks()
    }

    override suspend fun getBank(bankName: String): Bank {
        return bankDao.getBank(bankName)
    }

    override suspend fun getBankById(bankId: Int): Bank {
        return bankDao.getBankById(bankId)
    }

    override suspend fun addBank(bank: Bank) {
        return bankDao.addBank(bank)
    }

    override suspend fun updateBank(bank: Bank) {
        return bankDao.updateBank(bank)
    }

    override suspend fun deleteBank(bank: Bank) {
        return bankDao.deleteBank(bank)
    }

    override suspend fun removeBank(bankId: Int) {
        return bankDao.removeBank(bankId)
    }

}