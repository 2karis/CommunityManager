import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
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
  ngOnInit(){
    this.isLoggedIn = StorageService.isUserLoggedIn()
  }
  updateLogin():boolean{
    return StorageService.isUserLoggedIn()
  }
}
