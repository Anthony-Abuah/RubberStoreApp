package com.example.rubberstoreapp.feature_app.data.repository

import com.example.rubberstoreapp.feature_app.data.local.savings.Saving
import com.example.rubberstoreapp.feature_app.data.local.savings.SavingDao
import com.example.rubberstoreapp.feature_app.domain.repository.SavingRepository
import com.example.rubberstoreapp.feature_app.domain.repository.Savings
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class SavingRepositoryImpl @Inject constructor(
    private val savingDao: SavingDao
): SavingRepository {
    override fun allSavings(): Flow<Savings> {
        return savingDao.allSavings()
    }

    override fun getDurationalSavings(firstDate: Date, lastDate: Date): Flow<Savings> {
        return savingDao.getDurationalSavings(firstDate, lastDate)
    }

    override suspend fun addSaving(saving: Saving) {
        return savingDao.addSaving(saving)    }

    override suspend fun updateSaving(saving: Saving) {
        return savingDao.updateSaving(saving)    }

    override suspend fun deleteSaving(saving: Saving) {
        return savingDao.deleteSaving(saving)    }

    override suspend fun removeSaving(savingId: Int) {
        return savingDao.removeSaving(savingId)    }

    override suspend fun getSaving(savingId: Int): Saving {
        return savingDao.getSaving(savingId)
    }

    override suspend fun getTotalSumOfAllSavings(): Double? {
        return savingDao.totalSumOfAllSavings()
    }

    override suspend fun getSumOfSavingsBetweenDates(firstDate: Date, lastDate: Date): Double? {
        return savingDao.sumOfSavingsBetweenDates(firstDate, lastDate)
    }

    override suspend fun getNumberOfSavings(): Int? {
        return savingDao.getNumberOfSavings()
    }

    override suspend fun getNumberOfSavings(firstDate: Date, lastDate: Date): Int? {
        return savingDao.getNumberOfSavings(firstDate, lastDate)
    }

    override suspend fun getAverageSaving(): Double? {
        return savingDao.getAverageSaving()
    }

    override suspend fun getAverageSaving(firstDate: Date, lastDate: Date): Double? {
        return savingDao.getAverageSaving(firstDate, lastDate)
    }

    override suspend fun getMinimumSaving(): Double? {
        return savingDao.getMinimumSaving()
    }

    override suspend fun getMinimumSaving(firstDate: Date, lastDate: Date): Double? {
        return savingDao.getMinimumSaving(firstDate, lastDate)
    }

    override suspend fun getMaximumSaving(): Double? {
        return savingDao.getMaximumSaving()
    }

    override suspend fun getMaximumSaving(firstDate: Date, lastDate: Date): Double? {
        return savingDao.getMaximumSaving(firstDate, lastDate)
    }

}