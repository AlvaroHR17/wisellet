import { Transaction } from "@/types/transaction";
import { useEffect, useState } from "react";

interface Props {
    incomes: Transaction[];
    fixedExpenses: Transaction[];
    variableExpenses: Transaction[];
}

export function Balance ({incomes, fixedExpenses, variableExpenses}: Props) {

    const [balance, setBalance] = useState(0);
    
    useEffect(() => {
        const totalIncomes = incomes.map(income => income.amount).reduce((acumulator, currentValue) => acumulator + currentValue, 0);
        const totalFixedExpenses = fixedExpenses.map(fixedExpense => fixedExpense.amount).reduce((acumulator, currentValue) => acumulator + currentValue, 0);
        const totalVariableExpenses = variableExpenses.map(variableExpense => variableExpense.amount).reduce((acumulator, currentValue) => acumulator + currentValue, 0);
        setBalance(totalIncomes - (totalFixedExpenses + totalVariableExpenses));
    }, [incomes, fixedExpenses, variableExpenses]);
    
    return (
        <h2 className="text-end text-3xl font-semibold mt-5">
            <p>
                Balance: <span 
                className={balance > 0 ? 
                    'text-success-400' 
                    : balance < 0 ? 
                    'text-danger-400' : ''}>
                {balance}
                </span>
            </p>
        </h2>
    )
}