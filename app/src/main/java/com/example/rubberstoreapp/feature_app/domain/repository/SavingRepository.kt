package com.example.rubberstoreapp.feature_app.domain.repository

import com.example.rubberstoreapp.feature_app.data.local.savings.Saving
import kotlinx.coroutines.flow.Flow
import java.util.Date

typealias Savings = List<Saving>

interface SavingRepository {

    fun allSavings(): Flow<Savings>

    fun getDurationalSavings(firstDate: Date, lastDate: Date): Flow<Savings>

    suspend fun addSaving(saving: Saving)

    suspend fun updateSaving(saving: Saving)

    suspend fun deleteSaving(saving: Saving)

    suspend fun removeSaving(savingId: Int)

    suspend fun getSaving(savingId: Int): Saving

    suspend fun getTotalSumOfAllSavings(): Double?

    suspend fun getSumOfSavingsBetweenDates(firstDate: Date, lastDate: Date): Double?

    suspend fun getNumberOfSavings(): Int?

    suspend fun getAverageSaving(): Double?

    suspend fun getMinimumSaving(): Double?

    suspend fun getMaximumSaving(): Double?

    suspend fun getNumberOfSavings(firstDate: Date, lastDate: Date): Int?

    suspend fun getAverageSaving(firstDate: Date, lastDate: Date): Double?

    suspend fun getMinimumSaving(firstDate: Date, lastDate: Date): Double?

    suspend fun getMaximumSaving(firstDate: Date, lastDate: Date): Double?


}

