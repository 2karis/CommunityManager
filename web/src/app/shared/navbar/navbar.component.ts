import { Component, Input } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive, Router } from '@angular/router';
import { StorageService } from '../../service/storage.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
    //isAdmin : boolean;
    @Input('isAuthenticated') isLoggedIn!: boolean;
    constructor(private router : Router){}
    ngOnInit(){
  
      // this.isAdmin = StorageService.isAdminUserLoggedIn();
      
    }
  logout(){
    StorageService.logout();
    this.router.navigate(["/login"]);
  }
}
