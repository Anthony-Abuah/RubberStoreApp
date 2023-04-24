package com.example.rubberstoreapp.feature_app.domain.repository

import com.example.rubberstoreapp.feature_app.data.local.supplier.Supplier
import kotlinx.coroutines.flow.Flow

typealias Suppliers = List<Supplier>

interface SupplierRepository {

    fun allSuppliers(): Flow<Suppliers>

    suspend fun addSupplier(supplier: Supplier)

    suspend fun getSupplier(supplierId: Int): Supplier

    suspend fun updateSupplier(supplier: Supplier)

    suspend fun deleteSupplier(supplier: Supplier)

    suspend fun removeSupplier(supplierId: Int)
}

