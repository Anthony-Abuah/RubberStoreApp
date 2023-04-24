package com.example.rubberstoreapp.feature_app.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.supplier.Supplier
import com.example.rubberstoreapp.feature_app.domain.repository.SupplierRepository
import com.example.rubberstoreapp.feature_app.presentation.view_model.states.SupplierState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupplierViewModel @Inject constructor(
    private val supplierRepository: SupplierRepository
): ViewModel() {
    var supplier by mutableStateOf(Supplier(0, NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE))
        private set

    var openDialog by mutableStateOf(false)

    var deleteClicked by mutableStateOf(false)

    val suppliers = supplierRepository.allSuppliers()



    fun addSupplier(supplier: Supplier) = viewModelScope.launch {
        supplierRepository.addSupplier(supplier)
    }

    fun updateSupplier(supplier: Supplier) = viewModelScope.launch {
        supplierRepository.updateSupplier(supplier)
    }

    fun deleteSupplier(supplier: Supplier) = viewModelScope.launch {
        supplierRepository.deleteSupplier(supplier)
    }

    fun removeSupplier(supplierId: Int) = viewModelScope.launch {
        supplierRepository.removeSupplier(supplierId)
    }

    fun getSupplier(supplierId: Int) = viewModelScope.launch {
        supplier = supplierRepository.getSupplier(supplierId)
    }

    fun updateSupplierName(supplierName: String) {
        supplier = supplier.copy(
            supplierName = supplierName
        )
    }

    fun updateSupplierContact(supplierContact: String) {
        supplier = supplier.copy(
            supplierContact = supplierContact
        )
    }
    fun updateSupplierLocation(supplierLocation: String) {
        supplier = supplier.copy(
            supplierLocation = supplierLocation
        )
    }
    fun updateSupplierInfo(supplierInfo: String) {
        supplier = supplier.copy(
            supplierInfo = supplierInfo
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