import { Property } from "./property";

export interface Expense {
    id:number,
    description:string,
    amount:number,
    createdAt:Date,
    updatedAt:Date,
    property:Property
}
