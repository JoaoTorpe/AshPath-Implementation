import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { UserResponse as GeneralUserResponse } from '../../utils/models';

@Component({
  selector: 'app-user-approval-page',
  imports: [],
  templateUrl: './user-approval-page.component.html',
  styleUrl: './user-approval-page.component.scss'
})
export class UserApprovalPageComponent implements OnInit {
  usersPendingApproval: GeneralUserResponse[] = [];

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.getUsersPendingApproval().subscribe({
      next: (users) => {
        this.usersPendingApproval = users;
      },
      error: (err) => {
        console.error('Error fetching users pending approval:', err);
      }
    });
  }

  approve(user: GeneralUserResponse): void {
    // ...further logic using authService...
  }

  unapprove(user: GeneralUserResponse): void {
    // ...further logic using authService...
  }
}
