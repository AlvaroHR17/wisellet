import { useLoading } from "@/app/providers";
import { listTransactionTypes } from "@/axios/AxiosTransactions";
import { TransactionType } from "@/types/transaction";
import { useState } from "react";



export function useTransactionsTypes () {
    const [incomeTypes, setIncomeTypes] = useState<TransactionType[]>([]);
    const [fixedExpenseTypes, setFixedExpenseTypes] = useState<TransactionType[]>([]);
    const [variableExpenseTypes, setVariableExpenseTypes] = useState<TransactionType[]>([]);
    const {showLoading, hideLoading} = useLoading();
    
    const getTransactionsTypes = async() => {
        showLoading();
        const transactionTypesList = await listTransactionTypes();
        setIncomeTypes(transactionTypesList!.incomeTypes);
        setFixedExpenseTypes(transactionTypesList!.fixedExpenseTypes);
        setVariableExpenseTypes(transactionTypesList!.variableExpenseTypes);
        hideLoading();
    }

    return {incomeTypes, fixedExpenseTypes, variableExpenseTypes, getTransactionsTypes}
}