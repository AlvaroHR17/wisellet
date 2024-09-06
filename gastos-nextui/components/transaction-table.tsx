import { FormEvent, useMemo, useState } from "react";
import { DeleteIcon } from "@/icons/DeleteIcon";
import { EditIcon } from "@/icons/EditIcon";
import { TransactionTypes } from "@/types/enums/TransactionTypes";
import { Autocomplete, AutocompleteItem, Button, Input, Table, TableBody, TableCell, TableColumn, TableHeader, TableRow, Tooltip, useDisclosure } from "@nextui-org/react";
import { AxiosTransactions } from "@/axios/AxiosTransactions";
import { Transaction, TransactionType } from "@/types/transaction";
import { EditTransactionModal } from "./edit-transaction-modal";
import { getInput } from "@/utils/FormUtils";

export interface TransactionTableProps {
    transactionType: TransactionTypes;
    list: Transaction[];
    typeList: TransactionType[];
    loadData: (() => void);
    setLoading: ((newData: boolean) => void);
}

const DESCRIPTION_NAME = "description";
const AMOUNT_NAME = "amount"
  
export function TransactionTable ({transactionType, list, typeList, loadData, setLoading} : TransactionTableProps) {

  const [formError, setFormError] = useState("");
  const {isOpen, onOpen, onOpenChange} = useDisclosure();
  const [transactionToEdit, setTransactionToEdit] = useState<Transaction>();

  const topContent = useMemo(() => {
    return (
      <h1 className="text-2xl font-bold">{transactionType}</h1>
    );
  }, [])

  const createTransaction = async (event: FormEvent<HTMLFormElement>) => {
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
      await AxiosTransactions.createTransaction(description, amount, transactionType);
      loadData();
      form.reset();
    } else {
      setFormError("Please complete all required fields")
    }
  }

  const deleteTransaction = async(id: number) => {
    await AxiosTransactions.deleteTransaction(id);
    loadData();
  }

  const handleOpenModal = (transaction: Transaction) => {
    setTransactionToEdit(transaction);
    onOpen();
  }

  const bottomContent = useMemo(() => {
    return (
      <div>
        <form 
          className="flex gap-4" 
          onSubmit={createTransaction}
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
  }, [typeList, formError])

  return (
    <>
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
                    <span className="text-lg text-danger cursor-pointer active:opacity-50" onClick={() => deleteTransaction(item.id)}>
                      <DeleteIcon />
                    </span>
                  </Tooltip>
                </div>
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
      
      <EditTransactionModal 
        setLoading={setLoading} 
        onOpenChange={onOpenChange} 
        loadData={loadData}
        transactionToEdit={transactionToEdit!}
        isOpen={isOpen}
        typeList={typeList}/>
    </>
  )
}