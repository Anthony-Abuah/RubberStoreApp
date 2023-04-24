package com.example.rubberstoreapp.feature_app.data.local.savings

import androidx.room.*
import com.example.rubberstoreapp.core.util.Constants.SAVING_TABLE
import com.example.rubberstoreapp.feature_app.domain.repository.Savings
import kotlinx.coroutines.flow.Flow
import java.util.Date


@Dao
interface SavingDao {

    @Query("SELECT * FROM $SAVING_TABLE ORDER BY savingDate DESC ")
    fun allSavings(): Flow<Savings>

    @Query("SELECT * FROM $SAVING_TABLE WHERE savingDate BETWEEN :firstDate AND :lastDate ORDER BY savingDate ASC")
    fun getDurationalSavings(firstDate: Date, lastDate: Date): Flow<Savings>

    @Query("DELETE FROM $SAVING_TABLE WHERE savingId LIKE :id")
    suspend fun removeSaving(id: Int)

    @Query("SELECT * FROM $SAVING_TABLE WHERE savingId LIKE :id")
    suspend fun getSaving(id: Int): Saving

    @Query("SELECT SUM(savingAmount) FROM $SAVING_TABLE ")
    suspend fun totalSumOfAllSavings(): Double?

    @Query("SELECT SUM(savingAmount) FROM $SAVING_TABLE WHERE savingDate BETWEEN :firstDate AND  :lastDate")
    suspend fun sumOfSavingsBetweenDates(firstDate: Date, lastDate: Date): Double?

    @Query("SELECT MAX(savingAmount) FROM $SAVING_TABLE WHERE savingDate BETWEEN :firstDate AND  :lastDate")
    suspend fun getMaximumSaving(firstDate: Date, lastDate: Date): Double?

    @Query("SELECT MAX(savingAmount) FROM $SAVING_TABLE")
    suspend fun getMaximumSaving(): Double?

    @Query("SELECT MIN(savingAmount) FROM $SAVING_TABLE WHERE savingDate BETWEEN :firstDate AND  :lastDate")
    suspend fun getMinimumSaving(firstDate: Date, lastDate: Date): Double?

    @Query("SELECT MIN(savingAmount) FROM $SAVING_TABLE")
    suspend fun getMinimumSaving(): Double?

    @Query("SELECT AVG(savingAmount) FROM $SAVING_TABLE WHERE savingAmount > 0.0 AND savingDate BETWEEN :firstDate AND  :lastDate")
    suspend fun getAverageSaving(firstDate: Date, lastDate: Date): Double?

    @Query("SELECT AVG(savingAmount) FROM $SAVING_TABLE WHERE savingAmount > 0.0")
    suspend fun getAverageSaving(): Double?

    @Query("SELECT COUNT(savingAmount) FROM $SAVING_TABLE WHERE savingAmount > 0.0 AND savingDate BETWEEN :firstDate AND  :lastDate")
    suspend fun getNumberOfSavings(firstDate: Date, lastDate: Date): Int?

    @Query("SELECT COUNT(savingAmount) FROM $SAVING_TABLE WHERE savingAmount > 0.0")
    suspend fun getNumberOfSavings(): Int?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSaving(saving: Saving)

    @Update
    suspend fun updateSaving(saving: Saving)

    @Delete
    suspend fun deleteSaving(saving: Saving)

}