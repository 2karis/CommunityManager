import { Routes } from '@angular/router';
import { ExpenseComponent } from './component/expense/expense.component';
import { IncomeComponent } from './component/income/income.component';
import { PropertyComponent } from './component/property/property.component';
import { LeaseComponent } from './component/lease/lease.component';
import { TaskComponent } from './component/task/task.component';
import { UserComponent } from './component/user/user.component';
import { HomeComponent } from './component/home/home.component';
import { AccountComponent } from './component/account/account.component';
import { NotfoundComponent } from './component/notfound/notfound.component';
import { SignupComponent } from './component/signup/signup.component';
import { SigninComponent } from './component/signin/signin.component';

export const routes: Routes = [
    {path:"expense", component: ExpenseComponent},
    {path:"income", component: IncomeComponent },
    {path:"property", component: PropertyComponent},
    {path:"lease", component: LeaseComponent},
    {path:"task", component: TaskComponent},
    {path:"user", component: UserComponent},
    {path:"home", component: HomeComponent},
    {path:"account/:id", component: AccountComponent},
    {path:"signup",component: SignupComponent},
    {path:"login",component: SigninComponent},
    {path:"**",component: NotfoundComponent},

];
