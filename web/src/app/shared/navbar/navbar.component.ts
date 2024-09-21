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
    @Input("isSignedIn") isLoggedIn!: boolean;
    userId !: number;
    constructor(private router : Router){}
    ngOnInit(){
  
      this.isLoggedIn = StorageService.isUserLoggedIn();
      if(this.isLoggedIn){
        this.userId = 1;
      }
      
    }
  logout(){
    StorageService.logout();
    this.isLoggedIn = StorageService.isUserLoggedIn();
    this.router.navigate(["/login"]);
  }
}
