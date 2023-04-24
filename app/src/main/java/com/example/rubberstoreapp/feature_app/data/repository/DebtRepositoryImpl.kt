package com.example.rubberstoreapp.feature_app.data.repository

import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.data.local.debt.Debt
import com.example.rubberstoreapp.feature_app.data.local.debt.DebtDao
import com.example.rubberstoreapp.feature_app.domain.repository.CustomerNameDebt
import com.example.rubberstoreapp.feature_app.domain.repository.DebtDateValues
import com.example.rubberstoreapp.feature_app.domain.repository.DebtRepository
import com.example.rubberstoreapp.feature_app.domain.repository.Debts
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class DebtRepositoryImpl @Inject constructor(
    private val debtDao: DebtDao
): DebtRepository {
    override fun allDebts(): Flow<Debts> {
        return debtDao.allDebts()
    }

    override fun getAllTimeDebtDateValues(): Flow<DebtDateValues> {
        return debtDao.getAllTimeDebtDateValues()
    }

    override fun getAllTimeCustomerNameDebt(): Flow<CustomerNameDebt> {
        return debtDao.getAllTimeCustomerNameDebt()
    }

    override fun getDurationalDebtDateValues(
        firstDate: Date,
        lastDate: Date
    ): Flow<DebtDateValues> {
        return debtDao.getDurationalDebtDateValues(firstDate, lastDate)
    }

    override fun getDurationalCustomerNameDebt(
        firstDate: Date,
        lastDate: Date
    ): Flow<CustomerNameDebt> {
        return debtDao.getDurationalCustomerNameDebt(firstDate, lastDate)
    }

    override suspend fun addDebt(debt: Debt) {
        return debtDao.addDebt(debt)    }

    override suspend fun updateDebt(debt: Debt) {
        return debtDao.updateDebt(debt)    }

    override suspend fun deleteDebt(debt: Debt) {
        return debtDao.deleteDebt(debt)    }

    override suspend fun removeDebt(debtId: Int) {
        return debtDao.removeDebt(debtId)    }

    override suspend fun getDebt(debtId: Int): Debt {
        return debtDao.getDebt(debtId)
    }

    override suspend fun getDebtOfCustomer(customerName: String): Double? {
        return debtDao.getDebtOfCustomer(customerName)
    }

    override suspend fun getSumOfAllDebts(): Double? {
        return debtDao.getSumOfAllDebts()
    }

    override suspend fun getSumOfDebtsBetweenDates(firstDate: Date, lastDate: Date): Double? {
        return debtDao.getSumOfDebtsBetweenDates(firstDate, lastDate)
    }


}