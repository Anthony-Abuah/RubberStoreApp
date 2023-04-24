package com.example.rubberstoreapp.feature_app.data.local.revenue

import androidx.room.*
import com.example.rubberstoreapp.core.util.Constants.REVENUE_TABLE
import com.example.rubberstoreapp.feature_app.domain.repository.Revenues
import kotlinx.coroutines.flow.Flow
import java.util.Date


@Dao
interface RevenueDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRevenue(revenue: Revenue)

    @Query("SELECT * FROM $REVENUE_TABLE ORDER BY date DESC")
    fun allRevenues(): Flow<Revenues>

    @Query("SELECT * FROM $REVENUE_TABLE WHERE date BETWEEN :firstDate AND :lastDate ORDER BY date ASC")
    fun getDurationalRevenues(firstDate: Date, lastDate: Date): Flow<Revenues>

    @Update
    suspend fun updateRevenue(revenue: Revenue)

    @Delete
    suspend fun deleteRevenue(revenue: Revenue)

    @Query("DELETE FROM $REVENUE_TABLE WHERE revenueId LIKE :revenueId")
    suspend fun removeRevenue(revenueId: Int)

    @Query("SELECT SUM(amount) FROM $REVENUE_TABLE ")
    suspend fun totalSumOfAllRevenue(): Double?

    @Query("SELECT SUM(amount) FROM $REVENUE_TABLE WHERE date BETWEEN :firstDate AND :lastDate")
    suspend fun sumOfRevenueBetweenDates(firstDate: Date, lastDate: Date): Double?

    @Query("SELECT * FROM $REVENUE_TABLE WHERE revenueId LIKE :revenueId")
    suspend fun getRevenue(revenueId: Int): Revenue

    @Query("SELECT MAX(amount) FROM $REVENUE_TABLE WHERE date BETWEEN :firstDate AND :lastDate")
    suspend fun getMaximumRevenue(firstDate: Date, lastDate: Date): Double?

    @Query("SELECT MAX(amount) FROM $REVENUE_TABLE")
    suspend fun getMaximumRevenue(): Double?

    @Query("SELECT MIN(amount) FROM $REVENUE_TABLE WHERE date BETWEEN :firstDate AND :lastDate")
    suspend fun getMinimumRevenue(firstDate: Date, lastDate: Date): Double?

    @Query("SELECT MIN(amount) FROM $REVENUE_TABLE")
    suspend fun getMinimumRevenue(): Double?

    @Query("SELECT COUNT(revenueId) FROM $REVENUE_TABLE WHERE amount > 0.0")
    suspend fun getNumberOfRevenueDays(): Int?

    @Query("SELECT COUNT(revenueId) FROM $REVENUE_TABLE WHERE amount > 0.0 AND date BETWEEN :firstDate AND :lastDate")
    suspend fun getNumberOfRevenueDays(firstDate: Date, lastDate: Date): Int?

    @Query("SELECT AVG(amount) FROM $REVENUE_TABLE WHERE amount > 0.0")
    suspend fun getAverageRevenue(): Double?

    @Query("SELECT AVG(amount) FROM $REVENUE_TABLE WHERE amount > 0.0 AND date BETWEEN :firstDate AND :lastDate")
    suspend fun getAverageRevenue(firstDate: Date, lastDate: Date): Double?

}