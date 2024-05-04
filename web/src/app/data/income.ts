import { Lease } from "./lease";

export interface Income {
    id:number,
    dueOn:Date,
    paidOn:Date,
    status:string,
    amount:number,
    lateFee:number,
    paid:number,
    balance:number,
    createdAt:Date,
    updatedAt:Date,
    lease:Lease
}
