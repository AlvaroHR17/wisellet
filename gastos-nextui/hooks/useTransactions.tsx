import { listTransactions } from "@/axios/AxiosTransactions";
import { Transaction } from "@/types/transaction";
import { useEffect, useState } from "react";
import { useDate } from "./useDate";
import { useLoading } from "@/app/providers";




export function useTransactions () {
    const [incomes, setIncomes] = useState<Transaction[]>([]);
    const [fixedExpenses, setFixedExpense] = useState<Transaction[]>([]);
    const [variableExpenses, setVariableExpense] = useState<Transaction[]>([]);
    const {date} = useDate();
    const {showLoading, hideLoading} = useLoading();

    const getTransactions = async() => {
        showLoading();
        const transactionList = await listTransactions(date.getMonth()+1, date.getFullYear());
        setIncomes(transactionList!.incomes);
        setFixedExpense(transactionList!.fixedExpenses);
        setVariableExpense(transactionList!.variableExpenses);
        hideLoading();
    }

    useEffect(() => {
        getTransactions();
    }, [date])

    return {incomes, fixedExpenses, variableExpenses, getTransactions}
}