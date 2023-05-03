package com.example.rubberstoreapp.feature_app.di

import android.app.Application
import androidx.room.Room
import com.example.rubberstoreapp.feature_app.data.local.AppDatabase
import com.example.rubberstoreapp.feature_app.data.local.InventoryItemsConverter
import com.example.rubberstoreapp.feature_app.data.local.bank.BankDao
import com.example.rubberstoreapp.feature_app.data.local.customer.CustomerDao
import com.example.rubberstoreapp.feature_app.data.local.debt.DebtDao
import com.example.rubberstoreapp.feature_app.data.local.debt_repayment.DebtRepaymentDao
import com.example.rubberstoreapp.feature_app.data.local.expenses.ExpenseDao
import com.example.rubberstoreapp.feature_app.data.local.inventory.InventoryDao
import com.example.rubberstoreapp.feature_app.data.local.item.ItemDao
import com.example.rubberstoreapp.feature_app.data.local.revenue.RevenueDao
import com.example.rubberstoreapp.feature_app.data.local.savings.SavingDao
import com.example.rubberstoreapp.feature_app.data.local.supplier.SupplierDao
import com.example.rubberstoreapp.feature_app.data.repository.*
import com.example.rubberstoreapp.feature_app.data.util.GsonParser
import com.example.rubberstoreapp.feature_app.domain.repository.*
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase{
        return Room.databaseBuilder(app, AppDatabase::class.java, "appDatabase")
            .addTypeConverter(InventoryItemsConverter(GsonParser(Gson())))
            //.createFromAsset("database/prePopulatedDataDatabase.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideCustomerDao(database: AppDatabase): CustomerDao{
        return database.customerDao()
    }
    @Provides
    @Singleton
    fun provideDebtRepaymentDao(database: AppDatabase): DebtRepaymentDao{
        return database.debtRepaymentDao()
    }
    @Provides
    @Singleton
    fun provideDebtDao(database: AppDatabase): DebtDao{
        return database.debtDao()
    }
    @Provides
    @Singleton
    fun provideExpensesDao(database: AppDatabase): ExpenseDao{
        return database.expensesDao()
    }
    @Provides
    @Singleton
    fun provideInventoryDao(database: AppDatabase): InventoryDao{
        return database.inventoryDao()
    }
    @Provides
    @Singleton
    fun provideItemDao(database: AppDatabase): ItemDao{
        return database.itemDao()
    }
    @Provides
    @Singleton
    fun provideRevenueDao(database: AppDatabase): RevenueDao{
        return database.revenueDao()
    }
    @Provides
    @Singleton
    fun provideSupplierDao(database: AppDatabase): SupplierDao{
        return database.supplierDao()
    }

    @Provides
    @Singleton
    fun provideSavingDao(database: AppDatabase): SavingDao{
        return database.savingDao()
    }

    @Provides
    @Singleton
    fun provideBankDao(database: AppDatabase): BankDao{
        return database.bankDao()
    }

    @Provides
    @Singleton
    fun provideCustomerRepository(
        customerDao: CustomerDao
    ): CustomerRepository {
        return CustomerRepositoryImpl(customerDao)
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(
        expenseDao: ExpenseDao
    ): ExpenseRepository {
        return ExpenseRepositoryImpl(expenseDao)
    }

    @Provides
    @Singleton
    fun provideBankRepository(
        bankDao: BankDao
    ): BankRepository {
        return BankRepositoryImpl(bankDao)
    }

    @Provides
    @Singleton
    fun provideDebtRepository(
        debtDao: DebtDao
    ): DebtRepository {
        return DebtRepositoryImpl(debtDao)
    }

    @Provides
    @Singleton
    fun provideDebtRepaymentRepository(
        debtRepaymentDao: DebtRepaymentDao
    ): DebtRepaymentRepository {
        return DebtRepaymentRepositoryImpl(debtRepaymentDao)
    }

    @Provides
    @Singleton
    fun provideInventoryRepository(
        inventoryDao: InventoryDao
    ): InventoryRepository {
        return InventoryRepositoryImpl(inventoryDao)
    }

    @Provides
    @Singleton
    fun provideItemRepository(
        itemDao: ItemDao
    ): ItemRepository {
        return ItemRepositoryImpl(itemDao)
    }

    @Provides
    @Singleton
    fun provideSupplierRepository(
        supplierDao: SupplierDao
    ): SupplierRepository {
        return SupplierRepositoryImpl(supplierDao)
    }

    @Provides
    @Singleton
    fun provideRevenueRepository(
        revenueDao: RevenueDao
    ): RevenueRepository {
        return RevenueRepositoryImpl(revenueDao)
    }

    @Provides
    @Singleton
    fun provideSavingRepository(
        savingDao: SavingDao
    ): SavingRepository {
        return SavingRepositoryImpl(savingDao)
    }

}