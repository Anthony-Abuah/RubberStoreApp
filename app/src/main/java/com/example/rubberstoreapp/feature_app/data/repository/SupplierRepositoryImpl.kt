package com.example.rubberstoreapp.feature_app.data.repository

import com.example.rubberstoreapp.feature_app.data.local.supplier.Supplier
import com.example.rubberstoreapp.feature_app.data.local.supplier.SupplierDao
import com.example.rubberstoreapp.feature_app.domain.repository.SupplierRepository
import com.example.rubberstoreapp.feature_app.domain.repository.Suppliers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SupplierRepositoryImpl @Inject constructor(
    private val supplierDao: SupplierDao
): SupplierRepository {
    override fun allSuppliers(): Flow<Suppliers> {
        return supplierDao.allSuppliers()
    }

    override suspend fun addSupplier(supplier: Supplier) {
        return supplierDao.addSupplier(supplier)    }

    override suspend fun getSupplier(supplierId: Int): Supplier {
        return supplierDao.getSupplier(supplierId)
    }

    override suspend fun updateSupplier(supplier: Supplier) {
        return supplierDao.updateSupplier(supplier)    }

    override suspend fun deleteSupplier(supplier: Supplier) {
        return supplierDao.deleteSupplier(supplier)    }

    override suspend fun removeSupplier(supplierId: Int) {
        return supplierDao.removeSupplier(supplierId)    }


}