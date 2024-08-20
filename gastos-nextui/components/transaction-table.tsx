import { FormEvent, KeyboardEventHandler, useMemo, useState } from "react";
import { DeleteIcon } from "@/icons/DeleteIcon";
import { EditIcon } from "@/icons/EditIcon";
import { TransactionTypes } from "@/model/enums/TransactionTypes";
import { Autocomplete, AutocompleteItem, Button, Input, Modal, ModalBody, ModalContent, ModalFooter, ModalHeader, Table, TableBody, TableCell, TableColumn, TableHeader, TableRow, Tooltip, useDisclosure } from "@nextui-org/react";
import { Transaction, TransactionType } from "@/model/Transaction";
import { AxiosTransactions } from "@/axios/AxiosTransactions";

export interface TransactionTableProps {
    transactionType: TransactionTypes;
    list: Transaction[];
    typeList: TransactionType[];
    loadData: Function;
    setLoading: Function;
}
  
export function TransactionTable ({transactionType, list, typeList, loadData, setLoading} : TransactionTableProps) {

  const [formError, setFormError] = useState<String>("");
  const {isOpen, onOpen, onOpenChange} = useDisclosure();
  const [transactionToEdit, setTransactionToEdit] = useState<Transaction>();

  const topContent = useMemo(() => {
    return (
      <h1 className="text-2xl font-bold">{transactionType}</h1>
    );
  }, [])

  const createTransaction = async (event: FormEvent) => {
    event.preventDefault();
    setFormError("");
    const form = event.currentTarget as HTMLFormElement;
    const inputDescription = form.description as HTMLInputElement;
    const description = inputDescription.value as string;

    const inputAmount = form.amount as HTMLInputElement;
    const amount = +(inputAmount.value as string);

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

  const bottomContent = useMemo(() => {
    return (
      <div>
        <form className="flex gap-4" onSubmit={createTransaction}>
          <Autocomplete
            items={typeList ?? []}
            label="Name"
            className="w-7/12"
            allowsCustomValue
            name="description"
            isRequired>
            {(type) => <AutocompleteItem key={type.id}>{type.descripcion}</AutocompleteItem>}
          </Autocomplete>
          <Input label="Amount" className="w-4/12" type="number" min='0' name="amount" step="any" isRequired></Input>
          <Button type="submit" className="w-1/12 h-auto" color="success" variant="ghost">Save</Button>
        </form>
        <p className="text-small text-danger mt-3">{formError}</p>
      </div>
    );
  }, [typeList, formError])

  const handleOpenModal = (transaction: Transaction) => {
    setTransactionToEdit(transaction);
    onOpen();
  }

  const updateTransaction = async() => {
    setLoading(true);
    onOpenChange();
    const inputUpdateName = document.getElementById("inputUpdateName") as HTMLInputElement;
    const updateName = inputUpdateName.value as string;
    const inputUpdateAmount = document.getElementById("inputUpdateAmount") as HTMLInputElement;
    const updateAmount = +(inputUpdateAmount.value as string);

    await AxiosTransactions.updateTransaction(transactionToEdit!.id, updateName, updateAmount);
    loadData();
  }

  const handleEnter = (event: any) => {
    if((event as KeyboardEvent).key == "Enter") updateTransaction();
  }

  return (
    <>
      <Table 
          aria-label={transactionType} 
          topContent={topContent}
          topContentPlacement="outside" 
          bottomContent={(bottomContent)}>
        <TableHeader>
          {/*ALLOW SORTING*/}
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
      <Modal isOpen={isOpen} 
        onOpenChange={onOpenChange}
        placement="top-center"
        backdrop="blur">
          <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">Edit transaction</ModalHeader>
              <ModalBody className="flex-row">
                <Autocomplete
                  items={typeList ?? []}
                  label="Name"
                  className="w-7/12"
                  allowsCustomValue
                  name="description"
                  isRequired
                  defaultInputValue={transactionToEdit!.type.descripcion}
                  id="inputUpdateName"
                  onKeyDown={handleEnter}>
                  {(type) => <AutocompleteItem key={type.id}>{type.descripcion}</AutocompleteItem>}
                </Autocomplete>
                <Input id="inputUpdateAmount" label="Amount" 
                  className="w-4/12" 
                  type="number"
                  min='0'
                  name="amount"
                  step="any"
                  isRequired 
                  defaultValue={transactionToEdit!.amount.toString()}
                  onKeyDown={handleEnter}/>
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Close
                </Button>
                <Button color="primary" onPress={updateTransaction}>
                  Update
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>

      </Modal>
    </>
  )
}