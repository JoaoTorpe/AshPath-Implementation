import { Component, Signal } from '@angular/core';
import { LoginService } from '../login/services/login.service';
import { IUser } from '../utils/models';

@Component({
  selector: 'app-site-header',
  imports: [],
  templateUrl: './site-header.component.html',
  styleUrl: './site-header.component.scss'
})
export class SiteHeaderComponent {
  user!: Signal<IUser | null>;
  showSignOutMenu = false;

  constructor(private loginService: LoginService) { }

  ngOnInit() {
    this.user = this.loginService.user;
  }

  toggleSignOutMenu() {
    this.showSignOutMenu = !this.showSignOutMenu;
  }

  signOut() {
    this.loginService.signOut();
  }
}
