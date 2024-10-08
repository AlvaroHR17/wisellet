import { FormEvent, useEffect, useMemo, useState } from "react";
import { DeleteIcon } from "@/icons/DeleteIcon";
import { EditIcon } from "@/icons/EditIcon";
import { TransactionTypes } from "@/types/enums/TransactionTypes";
import { Autocomplete, AutocompleteItem, Button, Input, Table, TableBody, TableCell, TableColumn, TableHeader, TableRow, Tooltip, useDisclosure } from "@nextui-org/react";
import { Transaction, TransactionType } from "@/types/transaction";
import { EditTransactionModal } from "./edit-transaction-modal";
import { getInput } from "@/utils/FormUtils";
import { useDate } from "@/hooks/useDate";
import { createTransaction, deleteTransaction } from "@/axios/AxiosTransactions";

export interface TransactionTableProps {
    transactionType: TransactionTypes;
    list: Transaction[];
    typeList: TransactionType[];
    loadData: (() => void)
}

const DESCRIPTION_NAME = "description";
const AMOUNT_NAME = "amount"
  
export function TransactionTable ({transactionType, list, typeList, loadData} : TransactionTableProps) {

  const {date} = useDate();
  const [formError, setFormError] = useState("");
  const {isOpen, onOpen, onOpenChange} = useDisclosure();
  const [transactionToEdit, setTransactionToEdit] = useState<Transaction>();
  const [total, setTotal] = useState(0);

  const topContent = useMemo(() => {
    return (
      <h1 className="text-2xl font-bold">{transactionType}</h1>
    );
  }, [])

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setFormError("");
    const form = event.currentTarget;
    const {elements} = form;
    const inputDescription = getInput(elements.namedItem(DESCRIPTION_NAME));
    if(!inputDescription) return;
    const description = inputDescription.value

    const inputAmount = getInput(elements.namedItem(AMOUNT_NAME));
    if(!inputAmount) return;
    const amount = +inputAmount.value

    if(description.trim() !== "" && amount > 0){
      await createTransaction(description, amount, transactionType, date.getMonth()+1, date.getFullYear());
      loadData();
      form.reset();
    } else {
      setFormError("Please complete all required fields")
    }
  }

  const handleDelete = async(id: number) => {
    await deleteTransaction(id);
    loadData();
  }

  const handleOpenModal = (transaction: Transaction) => {
    setTransactionToEdit(transaction);
    onOpen();
  }

  useEffect(() => {
    const transactionsSum = list.map(transaction => transaction.amount).reduce((acumulator, currentValue) => acumulator + currentValue, 0);
    setTotal(transactionsSum);
  }, [list])

  const bottomContent = useMemo(() => {
    return (
      <div>
        <form 
          className="flex gap-4" 
          onSubmit={handleSubmit}
        >
          <Autocomplete
            items={typeList ?? []}
            label="Name"
            className="w-7/12"
            allowsCustomValue
            name={DESCRIPTION_NAME}
            isRequired>
            {(type) => <AutocompleteItem key={type.id}>{type.descripcion}</AutocompleteItem>}
          </Autocomplete>
          <Input 
            label="Amount" 
            className="w-4/12" 
            type="number" 
            min='0'
            name={AMOUNT_NAME}
            step="any" 
            isRequired/>
          <Button type="submit" className="w-1/12 h-auto" color="success" variant="ghost">Save</Button>
        </form>
        {formError && <p className="text-small text-danger mt-3">{formError}</p>}
      </div>
    );
  }, [typeList, formError, date])

  return (
    <div className="flex flex-col items-end">
      <Table 
          aria-label={transactionType} 
          topContent={topContent}
          topContentPlacement="outside" 
          bottomContent={(bottomContent)}>
        <TableHeader>
          {/*TODO ALLOW SORTING*/}
          <TableColumn key="name" className="w-7/12">NAME</TableColumn>
          <TableColumn key="amount" className="w-4/12">AMOUNT</TableColumn>
          <TableColumn key="actions" className="w-1/12">ACTIONS</TableColumn>
        </TableHeader>
        <TableBody 
            emptyContent={"Empty list."} 
            items={list ?? []}>
          {(item) =>(
            <TableRow key={item.id}>
              <TableCell key="name">{item.type.descripcion}</TableCell>
              <TableCell key="amount">{item.amount}</TableCell>
              <TableCell key="actions">
                <div className="relative flex items-center gap-2">
                  <Tooltip content="Edit">
                    <span className="text-lg text-default-400 cursor-pointer active:opacity-50" onClick={() => handleOpenModal(item)}>
                      <EditIcon />
                    </span>
                  </Tooltip>
                  <Tooltip color="danger" content="Delete">
                    <span className="text-lg text-danger cursor-pointer active:opacity-50" onClick={() => handleDelete(item.id)}>
                      <DeleteIcon />
                    </span>
                  </Tooltip>
                </div>
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>

      <p className="mt-3">
        Total {transactionType}: <strong>{total}</strong>
      </p>
      
      <EditTransactionModal
        onOpenChange={onOpenChange} 
        loadData={loadData}
        transactionToEdit={transactionToEdit!}
        isOpen={isOpen}
        typeList={typeList}/>
    </div>
  )
}