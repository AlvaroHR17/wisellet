import { TransactionTypes } from "@/types/enums/TransactionTypes";
import { LoadingSpinner } from "./loading-spinner";
import { TransactionTable } from "./transaction-table";
import { AxiosTransactions } from "@/axios/AxiosTransactions";
import { Transaction, TransactionType } from "@/types/transaction";
import { useState, useEffect } from "react";

export function Home () {

    const [incomes, setIncomes] = useState<Transaction[]>([]);
    const [fixedExpenses, setFixedExpense] = useState<Transaction[]>([]);
    const [variableExpenses, setVariableExpense] = useState<Transaction[]>([]);

    const [incomeTypes, setIncomeTypes] = useState<TransactionType[]>([]);
    const [fixedExpenseTypes, setFixedExpenseTypes] = useState<TransactionType[]>([]);
    const [variableExpenseTypes, setVariableExpenseTypes] = useState<TransactionType[]>([]);
    const [isLoading, setIsLoading] = useState(false);
    
    const loadTransactions = async() => {
        const transactionList = await AxiosTransactions.getTransactions();
        setIncomes(transactionList!.incomes);
        setFixedExpense(transactionList!.fixedExpenses);
        setVariableExpense(transactionList!.variableExpenses);
    }

    const loadTransactionTypes = async() => {
        const transactionTypesList = await AxiosTransactions.getTransactionTypes();
        setIncomeTypes(transactionTypesList!.incomeTypes);
        setFixedExpenseTypes(transactionTypesList!.fixedExpenseTypes);
        setVariableExpenseTypes(transactionTypesList!.variableExpenseTypes);
    }

    const loadData = async () => {
        setIsLoading(true);
        await loadTransactions();
        await loadTransactionTypes();
        setIsLoading(false);
    }

    useEffect(() => {
        loadData();
    }, [])

    return (
        <section className="flex flex-col lg:flex-row items-start justify-center gap-10 py-8 md:py-10">
            <TransactionTable transactionType={TransactionTypes.INCOME} list={incomes!} typeList={incomeTypes!} loadData={loadData} setLoading={setIsLoading}></TransactionTable>
            <TransactionTable transactionType={TransactionTypes.FIXED_EXPENSE} list={fixedExpenses!} typeList={fixedExpenseTypes!} loadData={loadData} setLoading={setIsLoading}></TransactionTable>
            <TransactionTable transactionType={TransactionTypes.VARIABLE_EXPENSE} list={variableExpenses!} typeList={variableExpenseTypes!} loadData={loadData} setLoading={setIsLoading}></TransactionTable>
            { isLoading ? <LoadingSpinner /> : null }
        </section>
    )
}