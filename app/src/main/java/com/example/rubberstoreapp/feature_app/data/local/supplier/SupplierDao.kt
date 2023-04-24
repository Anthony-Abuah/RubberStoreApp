package com.example.rubberstoreapp.feature_app.data.local.supplier

import androidx.room.*
import com.example.rubberstoreapp.core.util.Constants.SUPPLIER_TABLE
import com.example.rubberstoreapp.feature_app.domain.repository.Suppliers
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {

    @Query("SELECT * FROM $SUPPLIER_TABLE ORDER BY supplierId DESC ")
    fun allSuppliers(): Flow<Suppliers>

    @Query("DELETE FROM $SUPPLIER_TABLE WHERE supplierId LIKE :id")
    suspend fun removeSupplier(id: Int)

    @Query("SELECT * FROM $SUPPLIER_TABLE WHERE supplierId LIKE :supplierId")
    suspend fun getSupplier(supplierId: Int): Supplier


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSupplier(supplier: Supplier)

    @Update
    suspend fun updateSupplier(supplier: Supplier)

    @Delete
    suspend fun deleteSupplier(supplier: Supplier)



}