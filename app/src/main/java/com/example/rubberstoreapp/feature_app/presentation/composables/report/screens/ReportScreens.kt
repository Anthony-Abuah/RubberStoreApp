package com.example.rubberstoreapp.feature_app.presentation.composables.report.screens

sealed class ReportScreens(val route: String){
    object RevenueReportScreen: ReportScreens("to_revenue_report_screen")
    object SavingsReportScreen: ReportScreens("to_savings_report_screen")
    object ExpensesReportScreen: ReportScreens("to_expenses_report_screen")
    object DebtorsReportScreen: ReportScreens("to_debtors_report_screen")




    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }

}