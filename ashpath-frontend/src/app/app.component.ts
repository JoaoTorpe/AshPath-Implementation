import { Component } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { SiteHeaderComponent } from "./components/site-header/site-header.component";
import { AuthService } from './services/auth.service';
import { filter } from 'rxjs';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, SiteHeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'ashpath-frontend';
  userSig;
  showHeader: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {
    this.userSig = this.authService.userSig;
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        // Routes where header should be hidden
        const hiddenRoutes = ['/login', '/register', '/not-found',]; 
        this.showHeader = !hiddenRoutes.some(path => event.urlAfterRedirects.startsWith(path));
      });
  }
}
