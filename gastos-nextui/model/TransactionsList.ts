import { Transaction } from "./Transaction";

export type TransactionsList = {
    incomes: Transaction[];
    fixedExpenses: Transaction[];
    variableExpenses: Transaction[];
}