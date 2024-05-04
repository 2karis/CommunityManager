import { Income } from "./income";
import { Property } from "./property";
import { User } from "./user";

export interface Lease {
    id:number,
    termFrom:Date,
    termTo:Date,
    rent:number,
    deposit:number,
    status:string,
    file:string,
    createdAt:Date,
    updatedAt:Date,
    property:Property,
    income:Income[],
    users:User[],
}
