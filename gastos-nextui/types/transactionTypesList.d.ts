import { TransactionType } from "./transaction";

export type TransactionTypesList = {
    incomeTypes: TransactionType[];
    fixedExpenseTypes: TransactionType[];
    variableExpenseTypes: TransactionType[];
}