import { Task } from "./task";

export interface User {
    id:number,
    firstName:string,
    lastName:string,
    email:string,
    password:string,
    role:string,
    phone:string,
    imageUrl:string,
    createdAt:Date,
    updatedAt:Date,
    tasks:Task[]
}
