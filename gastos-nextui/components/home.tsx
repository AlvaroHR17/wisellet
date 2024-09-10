import { TransactionTypes } from "@/types/enums/TransactionTypes";
import { LoadingSpinner } from "./loading-spinner";
import { TransactionTable } from "./transaction-table";
import { DateSelector } from "./date-selector";
import { useTransactions } from "@/hooks/useTransactions";
import { useTransactionsTypes } from "@/hooks/useTransactionsTypes";
import { useLoading } from "@/app/providers";

export function Home () {

    const {incomes, fixedExpenses, variableExpenses, getTransactions} = useTransactions();
    const {incomeTypes, fixedExpenseTypes, variableExpenseTypes, getTransactionsTypes} = useTransactionsTypes();

    const {loading} = useLoading();

    const loadData = () => {
        getTransactions();
        getTransactionsTypes();
    }

    return (

        <section className="flex flex-col lg:flex-row items-start justify-center gap-10 py-8 md:py-10">
            <TransactionTable transactionType={TransactionTypes.INCOME} list={incomes} typeList={incomeTypes} loadData={loadData}></TransactionTable>
            <TransactionTable transactionType={TransactionTypes.FIXED_EXPENSE} list={fixedExpenses} typeList={fixedExpenseTypes} loadData={loadData}></TransactionTable>
            <TransactionTable transactionType={TransactionTypes.VARIABLE_EXPENSE} list={variableExpenses} typeList={variableExpenseTypes} loadData={loadData}></TransactionTable>
            { loading ? <LoadingSpinner /> : null }
        </section>
    )
}