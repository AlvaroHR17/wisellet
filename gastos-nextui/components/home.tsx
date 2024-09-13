import { TransactionTypes } from "@/types/enums/TransactionTypes";
import { LoadingSpinner } from "./loading-spinner";
import { TransactionTable } from "./transaction-table";
import { useTransactions } from "@/hooks/useTransactions";
import { useTransactionsTypes } from "@/hooks/useTransactionsTypes";
import { useLoading } from "@/app/providers";
import { Balance } from "./balance";
import { Card, CardBody } from "@nextui-org/card";
import { MonthSelector } from "./month-selector";

export function Home () {

    const {incomes, fixedExpenses, variableExpenses, getTransactions} = useTransactions();
    const {incomeTypes, fixedExpenseTypes, variableExpenseTypes, getTransactionsTypes} = useTransactionsTypes();

    const {loading} = useLoading();

    const loadData = () => {
        getTransactions();
        getTransactionsTypes();
    }

    return (
        <section>
            <MonthSelector/>
            <Card className="mt-4 p-2">
                <CardBody className="flex flex-col lg:flex-row items-start justify-center gap-10 py-8 md:py-10">
                    <TransactionTable transactionType={TransactionTypes.INCOME} list={incomes} typeList={incomeTypes} loadData={loadData}/>
                    <TransactionTable transactionType={TransactionTypes.FIXED_EXPENSE} list={fixedExpenses} typeList={fixedExpenseTypes} loadData={loadData}/>
                    <TransactionTable transactionType={TransactionTypes.VARIABLE_EXPENSE} list={variableExpenses} typeList={variableExpenseTypes} loadData={loadData}/>
                </CardBody>
            </Card>
            <Balance incomes={incomes} fixedExpenses={fixedExpenses} variableExpenses={variableExpenses}/>
            { loading ? <LoadingSpinner /> : null }
        </section>
    )
}