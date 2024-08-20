export type TransactionType = {
    id: number;
    descripcion: string;
}

export type Transaction = {
    id: number;
    amount: number;
    type: TransactionType;
}