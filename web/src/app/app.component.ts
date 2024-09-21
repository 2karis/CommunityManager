import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';
import { StorageService } from './service/storage.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule,RouterOutlet,NavbarComponent,SidebarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'web';
  isLoggedIn!:boolean;
  constructor(private router :Router){}
  ngOnInit(){
    this.isLoggedIn = StorageService.isUserLoggedIn();
    if(!this.isLoggedIn){
      this.router.navigate(['/login']);
    }
  }
  updateLogin():boolean{
    return StorageService.isUserLoggedIn()
  }

}
