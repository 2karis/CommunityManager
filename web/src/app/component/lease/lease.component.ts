import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Lease } from '../../data/lease';
import { LeaseService } from '../../service/lease.service';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-lease',
  standalone: true,
  imports: [CommonModule,RouterOutlet,FormsModule],
  templateUrl: './lease.component.html',
  styleUrl: './lease.component.css'
})
export class LeaseComponent {
  public leases : Lease[];

  public lease : Lease;

  constructor(private leaseService : LeaseService){
    this.leases = [];
    this.lease = <Lease>{};
  }
  ngOnInit(): void {
    this.getLeases();
  }

  public getLeases(): void{
    this.leaseService.getLeases().subscribe(
      (response: Lease[])=>{
        this.leases=response;
        console.log(this.leases);
      },
      (error :HttpErrorResponse)=>{
        alert(error);
      }
    )
  }

  public onOpenModal(currLease? : Lease): void{
    this.lease = <Lease>currLease;
  }

  public onCreateLease(createForm : NgForm): void{
    this.leaseService.createLease(createForm.value).subscribe({
      error: (e) => {
        alert(e);
        console.log(e);
      },
      complete: () => {
        this.getLeases();
        createForm.reset();
        document.getElementById("createClose")?.click()
      } 
    })
  }

  public onUpdateLease(updateForm : NgForm): void{
    this.leaseService.updateLease(<Lease>updateForm.value).subscribe({
      error: (error) => {
        alert(error);
      },
      complete: () => {
        this.getLeases();
        updateForm.reset();
        document.getElementById("updateClose")?.click()
      } 
    })
  }

  public onDeleteLease(id:number): void{
    this.leaseService.deleteLease(id).subscribe({
      error: (error) => {
        alert(error);
        console.log(error);
      },
      complete: () => {
        this.getLeases();
        document.getElementById("deleteClose")?.click()
      } 
    })
  }

  public searchLease(key : string){
    const results : Lease[] = [];
    for(const lease of this.leases){
      if(lease.status.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(lease);
      }
    }
    this.leases = results;
    if(results.length===0 || !key){
      this.getLeases();
    }
  }
}
