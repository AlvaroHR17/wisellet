
import { updateTransaction } from "@/axios/AxiosTransactions";
import { Transaction, TransactionType } from "@/types/transaction";
import { getInput } from "@/utils/FormUtils";
import { Autocomplete, AutocompleteItem, Button, Input, Modal, ModalBody, ModalContent, ModalFooter, ModalHeader } from "@nextui-org/react";
import { FormEvent, useState } from "react";

interface Props {
    onOpenChange: (() => void)
    loadData: (() => void)
    transactionToEdit: Transaction
    isOpen: boolean
    typeList: TransactionType[]
}

export function EditTransactionModal ({onOpenChange, loadData, transactionToEdit, isOpen, typeList} : Props) {

    const [formError, setFormError] = useState("");

    const DESCRIPTION_NAME = "description";
    const AMOUNT_NAME = "amount"
    
    const handleSubmit = async(event: FormEvent<HTMLFormElement>) => {
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
            onOpenChange();
            await updateTransaction(transactionToEdit!.id, description, amount);
            loadData();
            form.reset();
        } else {
            setFormError("Please complete all required fields")
        }
    }


    return (
        <Modal isOpen={isOpen} 
        onOpenChange={onOpenChange}
        placement="top-center"
        backdrop="blur">
          <ModalContent>
          {() => (
            <>
              <ModalHeader className="flex flex-col gap-1">Edit transaction</ModalHeader>
              <form
                onSubmit={handleSubmit}
              >
                <ModalBody className="flex-row justify-between flex-wrap">
                    <Autocomplete
                      items={typeList ?? []}
                      label="Name"
                      className="w-7/12"
                      allowsCustomValue
                      name={DESCRIPTION_NAME}
                      isRequired
                      defaultInputValue={transactionToEdit!.type.descripcion}
                      id="inputUpdateName"
                      onKeyDown={(e) => {}}>
                      {(type) => <AutocompleteItem key={type.id}>{type.descripcion}</AutocompleteItem>}
                    </Autocomplete>
                    <Input id="inputUpdateAmount" label="Amount" 
                      className="w-4/12" 
                      type="number"
                      min='0'
                      name={AMOUNT_NAME}
                      step="any"
                      isRequired 
                      defaultValue={transactionToEdit!.amount.toString()}
                    />
                    {formError && <p className="text-small text-danger mt-1">{formError}</p>}
                </ModalBody>
                <ModalFooter>
                  <Button type="submit" color="success" variant="ghost" className="h-14">
                    Update
                  </Button>
                </ModalFooter>
              </form>
            </>
          )}
        </ModalContent>

      </Modal>
    )
}