import { Expense } from "./expense";
import { Lease } from "./lease";

export interface Property {
    id:number,
    address:string,
    unit:string,
    createdAt:Date,
    updatedAt:Date,
    lease:Lease[],
    expense:Expense[]
}
