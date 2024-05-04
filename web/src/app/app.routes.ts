import { Routes } from '@angular/router';
import { ExpenseComponent } from './component/expense/expense.component';
import { IncomeComponent } from './component/income/income.component';
import { PropertyComponent } from './component/property/property.component';
import { LeaseComponent } from './component/lease/lease.component';
import { TaskComponent } from './component/task/task.component';
import { UserComponent } from './component/user/user.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { AccountComponent } from './component/account/account.component';

export const routes: Routes = [
    {path:"expense", component: ExpenseComponent},
    {path:"income", component: IncomeComponent },
    {path:"property", component: PropertyComponent},
    {path:"lease", component: LeaseComponent},
    {path:"task", component: TaskComponent},
    {path:"user", component: UserComponent},
    {path:"dashboard", component: DashboardComponent},
    {path:"account", component: AccountComponent}
];
