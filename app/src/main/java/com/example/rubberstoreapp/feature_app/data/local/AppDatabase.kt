package com.example.rubberstoreapp.feature_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import com.example.rubberstoreapp.feature_app.data.local.bank.BankDao
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.data.local.customer.CustomerDao
import com.example.rubberstoreapp.feature_app.data.local.debt.Debt
import com.example.rubberstoreapp.feature_app.data.local.debt.DebtDao
import com.example.rubberstoreapp.feature_app.data.local.debt_repayment.DebtRepayment
import com.example.rubberstoreapp.feature_app.data.local.debt_repayment.DebtRepaymentDao
import com.example.rubberstoreapp.feature_app.data.local.expenses.Expense
import com.example.rubberstoreapp.feature_app.data.local.expenses.ExpenseDao
import com.example.rubberstoreapp.feature_app.data.local.inventory.Inventory
import com.example.rubberstoreapp.feature_app.data.local.inventory.InventoryDao
import com.example.rubberstoreapp.feature_app.data.local.item.Item
import com.example.rubberstoreapp.feature_app.data.local.item.ItemDao
import com.example.rubberstoreapp.feature_app.data.local.revenue.Revenue
import com.example.rubberstoreapp.feature_app.data.local.revenue.RevenueDao
import com.example.rubberstoreapp.feature_app.data.local.savings.Saving
import com.example.rubberstoreapp.feature_app.data.local.savings.SavingDao
import com.example.rubberstoreapp.feature_app.data.local.supplier.Supplier
import com.example.rubberstoreapp.feature_app.data.local.supplier.SupplierDao

@Database(entities = [
    Customer::class,
    Debt::class,
    DebtRepayment::class,
    Expense::class,
    Inventory::class,
    Item::class,
    Revenue::class,
    Supplier::class,
    Saving::class,
    Bank::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class, InventoryItemsConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun debtDao(): DebtDao
    abstract fun debtRepaymentDao(): DebtRepaymentDao
    abstract fun inventoryDao(): InventoryDao
    abstract fun expensesDao(): ExpenseDao
    abstract fun itemDao(): ItemDao
    abstract fun revenueDao(): RevenueDao
    abstract fun supplierDao(): SupplierDao
    abstract fun savingDao(): SavingDao
    abstract fun bankDao(): BankDao
}