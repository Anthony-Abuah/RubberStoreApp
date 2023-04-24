package com.example.rubberstoreapp.feature_app.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import com.example.rubberstoreapp.feature_app.data.local.savings.Saving
import com.example.rubberstoreapp.feature_app.domain.repository.SavingRepository
import com.example.rubberstoreapp.feature_app.domain.repository.Savings
import com.example.rubberstoreapp.feature_app.presentation.view_model.states.BankState
import com.example.rubberstoreapp.feature_app.presentation.view_model.states.SavingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SavingViewModel @Inject constructor(
    private val savingRepository: SavingRepository
): ViewModel() {

    var saving by mutableStateOf(Saving(0, Date(), Bank(0, NO_VALUE, NO_VALUE, NO_VALUE), 0.0, NO_VALUE))
        private set

    var bank by mutableStateOf(BankState())
        private set


    var durationalSavings by mutableStateOf(emptyFlow<Savings>())
        private set


    var allTimeAverageSavings by mutableStateOf(0.0)
        private set

    var allTimeMaximumSavings by mutableStateOf(0.0)
        private set

    var allTimeMinimumSavings by mutableStateOf(0.0)
        private set

    var allTimeNumberOfSavings by mutableStateOf(0)
        private set

    var durationalNumberOfSavings by mutableStateOf(0)
        private set

    var durationalAverageSavings by mutableStateOf(0.0)
        private set

    var durationalMaximumSavings by mutableStateOf(0.0)
        private set

    var durationalMinimumSavings by mutableStateOf(0.0)
        private set


    var totalSumOfAllSavings:Double? by mutableStateOf(0.0)
        private set

    var sumOfSavingsBetweenDates by mutableStateOf(0.0)
        private set

    var openDialog by mutableStateOf(false)

    var deleteClicked by mutableStateOf(false)

    val allSavings = savingRepository.allSavings()

    fun addSaving(saving: Saving) = viewModelScope.launch {
        savingRepository.addSaving(saving)
    }

    fun updateSaving(saving: Saving) = viewModelScope.launch {
        savingRepository.updateSaving(saving)
    }

    fun deleteSaving(saving: Saving) = viewModelScope.launch {
        savingRepository.deleteSaving(saving)
    }

    fun getTotalSumOfAllSavings() = viewModelScope.launch {
        totalSumOfAllSavings = savingRepository.getTotalSumOfAllSavings() ?: 0.0
    }

    fun getSumOfSavingsBetweenDates(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        sumOfSavingsBetweenDates = savingRepository.getSumOfSavingsBetweenDates(firstDate, lastDate)?:0.0
    }

    fun getDurationalSavings(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalSavings = savingRepository.getDurationalSavings(firstDate, lastDate)
    }

    fun getDurationalAverageSavings(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalAverageSavings = savingRepository.getAverageSaving(firstDate, lastDate)?:0.0
    }

    fun getDurationalMinimumSavings(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalMinimumSavings = savingRepository.getMinimumSaving(firstDate, lastDate)?:0.0
    }

    fun getDurationalMaximumSavings(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalMaximumSavings = savingRepository.getMaximumSaving(firstDate, lastDate)?:0.0
    }

    fun getDurationalNumberOfSavings(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalNumberOfSavings = savingRepository.getNumberOfSavings(firstDate, lastDate) ?:0
    }

    fun getAllTimeMaximumSavings() = viewModelScope.launch {
        allTimeMaximumSavings = savingRepository.getMaximumSaving()?:0.0
    }

    fun getAllTimeMinimumSavings() = viewModelScope.launch {
        allTimeMinimumSavings = savingRepository.getMinimumSaving()?:0.0
    }

    fun getAllTimeAverageSavings() = viewModelScope.launch {
        allTimeAverageSavings = savingRepository.getAverageSaving()?:0.0
    }

    fun getAllTimeNumberOfSavings() = viewModelScope.launch {
        allTimeNumberOfSavings = savingRepository.getNumberOfSavings() ?:0
    }







    fun removeSaving(savingId: Int) = viewModelScope.launch {
        savingRepository.removeSaving(savingId)
    }

    fun getSaving(savingId: Int) = viewModelScope.launch {
        saving = savingRepository.getSaving(savingId)
        bank = bank.copy(
            bankName = saving.bank.bankName
        )
    }

    fun updateSavingDate(date: Date) {
        saving = saving.copy(
            savingDate = date
        )
    }

    fun updateSavingAmount(savingAmount: Double) {
        saving = saving.copy(
            savingAmount = savingAmount
        )
    }
    fun updateSavingsBankName(name: String) {
        bank = bank.copy(
            bankName = name
        )
    }

    fun updateSavingBank(bank: Bank) {
        saving = saving.copy(
            bank = bank
        )
    }

    fun updateSavingSusuCollector(susuCollector: String) {
        saving = saving.copy(
            susuCollector = susuCollector
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