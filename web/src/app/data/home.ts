import { Income } from "./income";
import { Task } from "./task";

export interface Home{
    balance:number,
    dueOn:Date,
    incomes:Income[],
    tasks:Task[]
}