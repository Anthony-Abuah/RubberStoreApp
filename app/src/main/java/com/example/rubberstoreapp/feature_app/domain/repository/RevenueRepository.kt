package com.example.rubberstoreapp.feature_app.domain.repository

import com.example.rubberstoreapp.feature_app.data.local.revenue.Revenue
import kotlinx.coroutines.flow.Flow
import java.util.Date

typealias Revenues = List<Revenue>

interface RevenueRepository {

    fun allRevenues(): Flow<Revenues>

    fun getDurationalRevenues(firstDate: Date, lastDate: Date): Flow<Revenues>

    suspend fun addRevenue(revenue: Revenue)

    suspend fun updateRevenue(revenue: Revenue)

    suspend fun deleteRevenue(revenue: Revenue)

    suspend fun removeRevenue(revenueId: Int)

    suspend fun getRevenue(revenueId: Int): Revenue

    suspend fun getTotalSumOfAllRevenue(): Double?

    suspend fun getSumOfRevenueBetweenDates(firstDate: Date, lastDate: Date): Double?


    suspend fun getMaximumRevenue(): Double?

    suspend fun getMaximumRevenue(firstDate: Date, lastDate: Date): Double?


    suspend fun getMinimumRevenue(): Double?

    suspend fun getMinimumRevenue(firstDate: Date, lastDate: Date): Double?


    suspend fun getNumberOfRevenueDays(): Int?

    suspend fun getNumberOfRevenueDays(firstDate: Date, lastDate: Date): Int?


    suspend fun getAverageRevenue(): Double?

    suspend fun getAverageRevenue(firstDate: Date, lastDate: Date): Double?


}

