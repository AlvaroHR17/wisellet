export interface TransactionType {
    id: number
    descripcion: string
}

export interface Transaction {
    id: number
    amount: number
    type: TransactionType
}