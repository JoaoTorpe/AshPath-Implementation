import { Component, OnInit, Signal } from '@angular/core';
import { SuccessfulLoginResponse } from '../../utils/models';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-site-header',
  imports: [RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './site-header.component.html',
  styleUrl: './site-header.component.scss'
})
export class SiteHeaderComponent implements OnInit {
  userSig!: Signal<SuccessfulLoginResponse | null>;
  showLogoutMenu = false;

  constructor(private loginService: AuthService) { }

  ngOnInit() {
    this.userSig = this.loginService.userSig;
  }

  toggleSignOutMenu() {
    this.showLogoutMenu = !this.showLogoutMenu;
  }

  logout() {
    this.loginService.logout();
  }
}
