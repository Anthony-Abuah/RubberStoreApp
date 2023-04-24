package com.example.rubberstoreapp.feature_app.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import com.example.rubberstoreapp.feature_app.domain.repository.BankRepository
import com.example.rubberstoreapp.feature_app.presentation.view_model.states.BankState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankViewModel @Inject constructor(
    private val bankRepository: BankRepository
): ViewModel() {

    var bank by mutableStateOf(Bank(0,NO_VALUE,NO_VALUE,NO_VALUE))
        private set

    var bankById by mutableStateOf(Bank(0,NO_VALUE,NO_VALUE,NO_VALUE))
        private set

    var openDialog by mutableStateOf(false)

    var deleteClicked by mutableStateOf(false)

    val allBanks = bankRepository.allBanks()


    fun addBank(bank: Bank) = viewModelScope.launch {
        bankRepository.addBank(bank)
    }

    fun getBank(bankName: String) = viewModelScope.launch {
        bank = bankRepository.getBank(bankName)
    }

    fun getBankById(bankId: Int) = viewModelScope.launch {
        bankById = bankRepository.getBankById(bankId)
    }

    fun updateBank(bank: Bank) = viewModelScope.launch {
        bankRepository.updateBank(bank)
    }

    fun deleteBank(bank: Bank) = viewModelScope.launch {
        bankRepository.deleteBank(bank)
    }

    fun removeBank(bankId: Int) = viewModelScope.launch {
        bankRepository.removeBank(bankId)
    }

    fun updateBankName(name: String) {
        bankById = bankById.copy(
            bankName = name
        )
    }

    fun updateBankContact(contact: String) {
        bankById = bankById.copy(
            bankContact = contact
        )
    }

    fun updateBankLocation(location: String) {
        bankById = bankById.copy(
            bankLocation = location
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