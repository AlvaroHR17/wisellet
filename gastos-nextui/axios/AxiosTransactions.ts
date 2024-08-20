import { TransactionTypesList } from "@/model/TransactionTypesList";
import { TransactionsList } from "@/model/TransactionsList";
import { TransactionTypes } from "@/model/enums/TransactionTypes";
import axios from "axios";

export class AxiosTransactions {
  static async getTransactions() {
    const url = 'http://localhost:8092/showTransactions';
    try {
      const response = await axios.get(url);
      return response.data as TransactionsList;

    } catch (error: any) {
      if (error.name === "AbortError") {
        console.log("Fetch aborted");
      } else {
        console.error("There was an error finding the transactions: ", error);
      }
    }
  }

  static async getTransactionTypes() {
    const url = 'http://localhost:8092/listTransactionTypes';
    try {
      const response = await axios.get(url);
      return response.data as TransactionTypesList;

    } catch (error: any) {
      if (error.name === "AbortError") {
        console.log("Fetch aborted");
      } else {
        console.error("There was an error finding the transaction types: ", error);
      }
    }
  }

  static async createTransaction(name: string, amount: number, transactionType: TransactionTypes) {
    let url = "";
    switch(transactionType) {
      case TransactionTypes.INCOME:
        url = 'http://localhost:8092/createIncome';
        break;
      case TransactionTypes.FIXED_EXPENSE:
        url = 'http://localhost:8092/createFixedExpense';
        break;
      case TransactionTypes.VARIABLE_EXPENSE:
        url = 'http://localhost:8092/createVariableExpense';
        break;
    }
    
    const data = {
      name: name,
      amount: amount
    };

    try {
      await axios.post(url, data);
      console.log(transactionType + ' created successfully');
    } catch (error) {
      console.error('There was a problem creating the transaction: ', error);
    }
  };

  static async deleteTransaction(id: number) {
    const url = 'http://localhost:8092/deleteTransaction/' + id;
    
    try {
      await axios.delete(url);
      console.log('Transaction deleted successfully');
    } catch(error) {
      console.error('There was a problem deleting a transaction: ', error)
    }
  }

  static async updateTransaction(id:number, name:string, amount:number) {
    const url = 'http://localhost:8092/updateTransaction';
    
    const data = {
      id: id,
      name: name,
      amount: amount
    };

    try {
      await axios.put(url, data);
      console.log('Transaction updated successfully');
    } catch(error) {
      console.error('There was a problem updating a transaction: ', error)
    }
  }

}