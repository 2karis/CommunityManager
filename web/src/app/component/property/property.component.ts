import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Property } from '../../data/property';
import { PropertyService } from '../../service/property.service';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-property',
  standalone: true,
  imports: [CommonModule,RouterOutlet,FormsModule],
  templateUrl: './property.component.html',
  styleUrl: './property.component.css'
})
export class PropertyComponent {
  public properties : Property[];

  public property : Property;

  constructor(private propertyService : PropertyService){
    this.properties = [];
    this.property = <Property>{};
  }
  ngOnInit(): void {
    
    this.getProperties();
  }

  public getProperties(): void{
    this.propertyService.getPropertys().subscribe(
      (response: Property[])=>{

        this.properties=response;
        console.log(this.properties);
      },
      (error :HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }

  public onOpenModal(currProperty? : Property): void{
    this.property = <Property>currProperty;
  }

  public onCreateProperty(createForm : NgForm): void{
    this.propertyService.createProperty(createForm.value).subscribe({
      error: (e) => {
        alert(e);
        console.log(e);
      },
      complete: () => {
        this.getProperties();
        createForm.reset();
        document.getElementById("createClose")?.click()
      } 
    })
  }

  public onUpdateProperty(updateForm : NgForm): void{
    this.propertyService.updateProperty(<Property>updateForm.value).subscribe({
      error: (error) => {
        alert(error);
      },
      complete: () => {
        this.getProperties();
        updateForm.reset();
        document.getElementById("updateClose")?.click()
      } 
    })
  }

  public onDeleteProperty(id:number): void{
    this.propertyService.deleteProperty(id).subscribe({
      error: (error) => {
        alert(error);
      },
      complete: () => {
        this.getProperties();
        document.getElementById("deleteClose")?.click()
      } 
    })
  }

  public searchProperty(key : string){
    const results : Property[] = [];
    for(const property of this.properties){
      if(property.address.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(property);
      }
    }
    this.properties = results;
    if(results.length===0 || !key){
      this.getProperties();
    }
  }
}
