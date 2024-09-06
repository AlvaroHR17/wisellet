import { type TransactionsList } from "@/types/transactionsList";
import { type TransactionTypesList } from "@/types/transactionTypesList";
import { TransactionTypes } from "@/types/enums/TransactionTypes";
import axios from "axios";
import Api from "@/constants/Api";

export class AxiosTransactions {

  // GET ALL TRANSACTIONS
  static async getTransactions() {
    const API_URL = `${Api.URL}:${Api.PORT}`;
    const path = '/v2/showTransactions';

    try {
      const response = await axios.get(`${API_URL}${path}`, {
        withCredentials: true,
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        }
      })
      return response.data as TransactionsList;

    } catch (error) {
      console.error('There was a problem creating the transaction: ', error);
    }
  }

  // GET TRANSACTION TYPES
  static async getTransactionTypes() {
    const API_URL = `${Api.URL}:${Api.PORT}`;
    const path = '/v2/listTransactionTypes';

    try {
      const response = await axios.get(`${API_URL}${path}`, {
        withCredentials: true,
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        }
      });
      return response.data as TransactionTypesList;

    } catch (error) {
      console.error('There was a problem creating the transaction: ', error);
    }
  }

  // CREATE A NEW TRANSACTION
  static async createTransaction(name: string, amount: number, transactionType: TransactionTypes) {
    const API_URL = `${Api.URL}:${Api.PORT}`;
    let path = "";
    switch(transactionType) {
      case TransactionTypes.INCOME:
        path = '/v2/createIncome';
        break;
      case TransactionTypes.FIXED_EXPENSE:
        path = '/v2/createFixedExpense';
        break;
      case TransactionTypes.VARIABLE_EXPENSE:
        path = '/v2/createVariableExpense';
        break;
    }
    
    const data = {
      name: name,
      amount: amount
    };

    try {
      await axios.post(`${API_URL}${path}`, data, {
        withCredentials: true,
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        }
      });
      console.log(transactionType + ' created successfully');
    } catch (error) {
      console.error('There was a problem creating the transaction: ', error);
    }
  };

  // DELETE TRANSACTION
  static async deleteTransaction(id: number) {
    const API_URL = `${Api.URL}:${Api.PORT}`;
    const path = `/v2/deleteTransaction/${id}`;
    
    try {
      await axios.delete(`${API_URL}${path}`, {
        withCredentials: true,
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        }
      });
      console.log('Transaction deleted successfully');
    } catch(error) {
      console.error('There was a problem deleting a transaction: ', error)
    }
  }

  // UPDATE A TRANSACTION
  static async updateTransaction(id:number, name:string, amount:number) {
    const API_URL = `${Api.URL}:${Api.PORT}`;
    const path = '/v2/updateTransaction';
    
    const data = {
      id: id,
      name: name,
      amount: amount
    };

    try {
      await axios.put(`${API_URL}${path}`, data, {
        withCredentials: true,
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        }
      });
      console.log('Transaction updated successfully');
    } catch(error) {
      console.error('There was a problem updating a transaction: ', error)
    }
  }

}