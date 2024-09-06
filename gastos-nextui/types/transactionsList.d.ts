import { Transaction } from "./transaction";

export type TransactionsList = {
    incomes: Transaction[];
    fixedExpenses: Transaction[];
    variableExpenses: Transaction[];
}