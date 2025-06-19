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
  isAdmin!: Signal<boolean>;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.userSig = this.authService.userSig;
    this.isAdmin = this.authService.isAdmin;
  }

  logout() {
    this.authService.logout();
  }
}
