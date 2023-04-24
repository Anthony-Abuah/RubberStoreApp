package com.example.rubberstoreapp.feature_app.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubberstoreapp.feature_app.data.local.revenue.Revenue
import com.example.rubberstoreapp.feature_app.domain.repository.RevenueRepository
import com.example.rubberstoreapp.feature_app.domain.repository.Revenues
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RevenueViewModel @Inject constructor(
    private val revenueRepository: RevenueRepository
): ViewModel() {

    var revenue by mutableStateOf(Revenue(0, Date(), 0.0))
        private set
    var openDialog by mutableStateOf(false)

    var deleteClicked by mutableStateOf(false)

    val allRevenues = revenueRepository.allRevenues()


    var durationalRevenues by mutableStateOf(emptyFlow<Revenues>())
        private set

    var totalSumOfAllRevenue by mutableStateOf(0.0)
        private set

    var sumOfRevenueBetweenDates by mutableStateOf(0.0)
        private set


    var allTimeAverageRevenue by mutableStateOf(0.0)
        private set

    var durationalAverageRevenue by mutableStateOf(0.0)
        private set

    var allTimeMaxRevenue by mutableStateOf(0.0)
        private set

    var durationalMaxRevenue by mutableStateOf(0.0)
        private set

    var allTimeMinRevenue by mutableStateOf(0.0)
        private set

    var durationalMinRevenue by mutableStateOf(0.0)
        private set

    var allTimeRevenueDays by mutableStateOf(0)
        private set

    var durationalRevenueDays by mutableStateOf(0)
        private set

    fun addRevenue(revenue: Revenue) = viewModelScope.launch {
        revenueRepository.addRevenue(revenue)
    }

    fun updateRevenue(revenue: Revenue) = viewModelScope.launch {
        revenueRepository.updateRevenue(revenue)
    }

    fun deleteRevenue(revenue: Revenue) = viewModelScope.launch {
        revenueRepository.deleteRevenue(revenue)
    }

    fun removeRevenue(revenueId: Int) = viewModelScope.launch {
        revenueRepository.removeRevenue(revenueId)
    }

    fun getTotalSumOfAllRevenue() = viewModelScope.launch {
        totalSumOfAllRevenue = revenueRepository.getTotalSumOfAllRevenue()?:0.0
    }

    fun getDurationalRevenues(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalRevenues = revenueRepository.getDurationalRevenues(firstDate, lastDate)
    }

    fun getSumOfRevenueBetweenDates(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        sumOfRevenueBetweenDates = revenueRepository.getSumOfRevenueBetweenDates(firstDate, lastDate)?:0.0
    }

    fun getRevenue(revenueId: Int) = viewModelScope.launch {
        revenue = revenueRepository.getRevenue(revenueId)
    }

    fun getAllTimeAverageRevenue() = viewModelScope.launch {
        allTimeAverageRevenue = revenueRepository.getAverageRevenue() ?: 0.0
    }


    fun getAllTimeMaxRevenue() = viewModelScope.launch {
        allTimeMaxRevenue = revenueRepository.getMaximumRevenue() ?: 0.0
    }


    fun getAllTimeMinRevenue() = viewModelScope.launch {
        allTimeMinRevenue = revenueRepository.getMinimumRevenue() ?: 0.0
    }


    fun getAllTimeRevenueDays() = viewModelScope.launch {
        allTimeRevenueDays = revenueRepository.getNumberOfRevenueDays() ?: 0
    }


    fun getDurationalAverageRevenue(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalAverageRevenue = revenueRepository.getAverageRevenue(firstDate, lastDate) ?: 0.0
    }


    fun getDurationalMaxRevenue(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalMaxRevenue = revenueRepository.getMaximumRevenue(firstDate, lastDate) ?: 0.0
    }


    fun getDurationalMinRevenue(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalMinRevenue = revenueRepository.getMinimumRevenue(firstDate, lastDate) ?: 0.0
    }


    fun getDurationalRevenueDays(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalRevenueDays = revenueRepository.getNumberOfRevenueDays(firstDate, lastDate) ?: 0
    }






    fun updateRevenueDate(date: Date) {
        revenue = revenue.copy(
            date = date
        )
    }
    fun updateRevenueAmount(revenueAmount: Double) {
        revenue = revenue.copy(
            amount = revenueAmount
        )
    }

    fun deleteClicked() {
        deleteClicked = true
    }

    fun cancelClicked() {
        deleteClicked = false
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}