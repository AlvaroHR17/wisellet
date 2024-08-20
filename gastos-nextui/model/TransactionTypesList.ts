import { TransactionType } from "./Transaction";

export type TransactionTypesList = {
    incomeTypes: TransactionType[];
    fixedExpenseTypes: TransactionType[];
    variableExpenseTypes: TransactionType[];
}