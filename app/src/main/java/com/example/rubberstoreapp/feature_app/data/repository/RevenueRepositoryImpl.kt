package com.example.rubberstoreapp.feature_app.data.repository

import com.example.rubberstoreapp.feature_app.data.local.revenue.Revenue
import com.example.rubberstoreapp.feature_app.data.local.revenue.RevenueDao
import com.example.rubberstoreapp.feature_app.domain.repository.RevenueRepository
import com.example.rubberstoreapp.feature_app.domain.repository.Revenues
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class RevenueRepositoryImpl @Inject constructor(
    private val revenueDao: RevenueDao
): RevenueRepository{
    override fun allRevenues(): Flow<Revenues> {
        return revenueDao.allRevenues()
    }

    override fun getDurationalRevenues(firstDate: Date, lastDate: Date): Flow<Revenues> {
        return revenueDao.getDurationalRevenues(firstDate, lastDate)
    }

    override suspend fun addRevenue(revenue: Revenue) {
        return revenueDao.addRevenue(revenue)    }

    override suspend fun updateRevenue(revenue: Revenue) {
        return revenueDao.updateRevenue(revenue)    }

    override suspend fun deleteRevenue(revenue: Revenue) {
        return revenueDao.deleteRevenue(revenue)    }

    override suspend fun removeRevenue(revenueId: Int) {
        return revenueDao.removeRevenue(revenueId)    }

    override suspend fun getRevenue(revenueId: Int): Revenue {
        return revenueDao.getRevenue(revenueId)
    }

    override suspend fun getTotalSumOfAllRevenue(): Double? {
        return revenueDao.totalSumOfAllRevenue()
    }

    override suspend fun getSumOfRevenueBetweenDates(firstDate: Date, lastDate: Date): Double? {
        return revenueDao.sumOfRevenueBetweenDates(firstDate, lastDate)
    }

    override suspend fun getMaximumRevenue(): Double? {
        return revenueDao.getMaximumRevenue()
    }

    override suspend fun getMaximumRevenue(firstDate: Date, lastDate: Date): Double? {
        return revenueDao.getMaximumRevenue(firstDate, lastDate)
    }

    override suspend fun getMinimumRevenue(): Double? {
        return revenueDao.getMinimumRevenue()
    }

    override suspend fun getMinimumRevenue(firstDate: Date, lastDate: Date): Double? {
        return revenueDao.getMinimumRevenue(firstDate, lastDate)
    }

    override suspend fun getNumberOfRevenueDays(): Int? {
        return revenueDao.getNumberOfRevenueDays()
    }

    override suspend fun getNumberOfRevenueDays(firstDate: Date, lastDate: Date): Int? {
        return revenueDao.getNumberOfRevenueDays(firstDate, lastDate)
    }

    override suspend fun getAverageRevenue(): Double? {
        return revenueDao.getAverageRevenue()
    }

    override suspend fun getAverageRevenue(firstDate: Date, lastDate: Date): Double? {
        return revenueDao.getAverageRevenue(firstDate, lastDate)
    }

}