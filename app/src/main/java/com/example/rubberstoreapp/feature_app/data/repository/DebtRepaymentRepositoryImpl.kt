package com.example.rubberstoreapp.feature_app.data.repository

import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.data.local.debt_repayment.DebtRepayment
import com.example.rubberstoreapp.feature_app.data.local.debt_repayment.DebtRepaymentDao
import com.example.rubberstoreapp.feature_app.domain.repository.CustomerNameDebtRepayment
import com.example.rubberstoreapp.feature_app.domain.repository.DebtRepaymentDateValue
import com.example.rubberstoreapp.feature_app.domain.repository.DebtRepaymentRepository
import com.example.rubberstoreapp.feature_app.domain.repository.DebtRepayments
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class DebtRepaymentRepositoryImpl @Inject constructor(
    private val debtRepaymentDao: DebtRepaymentDao
): DebtRepaymentRepository {
    override fun allDebtRepayments(): Flow<DebtRepayments> {
        return debtRepaymentDao.allDebtRepayments()
    }

    override fun getAllTimeDebtRepaymentDateValues(): Flow<DebtRepaymentDateValue> {
        return debtRepaymentDao.getAllTimeDebtRepaymentDateValues()
    }

    override fun getDurationalDebtRepaymentDateValues(
        firstDate: Date,
        lastDate: Date
    ): Flow<DebtRepaymentDateValue> {
       return debtRepaymentDao.getDurationalDebtRepaymentDateValues(firstDate, lastDate)
    }

    override fun getAllTimeCustomerNameDebtRepayment(): Flow<CustomerNameDebtRepayment> {
        return debtRepaymentDao.getAllTimeCustomerNameDebtRepayment()
    }

    override fun getDurationalCustomerNameDebtRepayment(
        firstDate: Date,
        lastDate: Date
    ): Flow<CustomerNameDebtRepayment> {
        return debtRepaymentDao.getDurationalCustomerNameDebtRepayment(firstDate, lastDate)
    }

    override suspend fun addDebtRepayment(debtRepayment: DebtRepayment) {
        return debtRepaymentDao.addDebtRepayment(debtRepayment)    }

    override suspend fun updateDebtRepayment(debtRepayment: DebtRepayment) {
        return debtRepaymentDao.updateDebtRepayment(debtRepayment)    }

    override suspend fun deleteDebtRepayment(debtRepayment: DebtRepayment) {
        return debtRepaymentDao.deleteDebtRepayment(debtRepayment)    }

    override suspend fun removeDebtRepayment(debtRepaymentId: Int) {
        return debtRepaymentDao.removeDebtRepayment(debtRepaymentId)    }

    override suspend fun getDebtRepayment(debtRepaymentId: Int): DebtRepayment {
        return debtRepaymentDao.getDebtRepayment(debtRepaymentId)
    }

    override suspend fun getSumOfDebtRepaymentByCustomer(customerName: String): Double? {
        return debtRepaymentDao.getSumOfDebtRepaymentByCustomer(customerName)
    }

    override suspend fun getTotalSumOfDebtRepayment(): Double? {
        return debtRepaymentDao.getTotalSumOfDebtRepayment()
    }

    override suspend fun getSumOfDebtRepaymentBetweenDates(
        firstDate: Date,
        lastDate: Date
    ): Double? {
        return debtRepaymentDao.getSumOfDebtRepaymentBetweenDates(firstDate, lastDate)
    }


}