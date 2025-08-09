import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { UserResponse as GeneralUserResponse } from '../../utils/models';
import { NgClass } from '@angular/common';
import {
  catchError,
  distinctUntilChanged,
  Observable,
  of,
  Subscription,
  switchMap,
  timer,
} from 'rxjs';

@Component({
  selector: 'app-user-approval-page',
  imports: [NgClass],
  templateUrl: './user-approval-page.component.html',
  styleUrl: './user-approval-page.component.scss',
})
export class UserApprovalPageComponent implements OnInit, OnDestroy {
  usersPendingApproval: GeneralUserResponse[] = [];
  snackbarMessage: string = '';
  snackbarType: string = '';
  showSnackbar: boolean = false;

  constructor(private authService: AuthService) {}

  pollingSubscription!: Subscription;
  ngOnInit(): void {
    this.pollingSubscription = timer(0, 2000)
      .pipe(
        switchMap(() =>
          this.authService.getUsersPendingApproval().pipe(
            catchError((err) => {
              console.error(
                'Erro ao buscar usuários com aprovação pendente:',
                err
              );
              // Return empty array or previous value to keep polling alive
              return of(this.usersPendingApproval || []);
            })
          )
        ),
        distinctUntilChanged(
          (prev, curr) => JSON.stringify(prev) === JSON.stringify(curr)
        )
      )
      .subscribe((users) => {
        this.usersPendingApproval = users;
      });
  }

  ngOnDestroy(): void {
    this.pollingSubscription?.unsubscribe();
  }

  approve(user: GeneralUserResponse): void {
    this.authService.approveUser(user.id).subscribe({
      next: () => {
        this.usersPendingApproval = this.usersPendingApproval.filter(
          (u) => u.id !== user.id
        );
        this.showCustomSnackbar(
          `Usuário ${user.email} foi aprovado!`,
          'success'
        );
      },
      error: (err) => {
        console.error('Error approving user:', err);
        this.showCustomSnackbar(
          'Falha ao aprovar o usuário. Tente novamente.',
          'error'
        );
      },
    });
  }

  reject(user: GeneralUserResponse): void {
    this.authService.rejectUser(user.id).subscribe({
      next: () => {
        this.usersPendingApproval = this.usersPendingApproval.filter(
          (u) => u.id !== user.id
        );
        this.showCustomSnackbar(`Usuário ${user.email} foi rejeitado.`, 'info');
      },
      error: (err) => {
        console.error('Error rejecting user:', err);
        this.showCustomSnackbar(
          'Falha ao rejeitar o usuário. Tente novamente.',
          'error'
        );
      },
    });
  }

  private timeoutId: any;
  hideAnimation: boolean = true;

  showCustomSnackbar(message: string, type: string): void {
    this.snackbarMessage = message;
    this.snackbarType = type;
    this.hideAnimation = false;
    this.showSnackbar = true;

    if (this.timeoutId) {
      clearTimeout(this.timeoutId);
    }

    this.timeoutId = setTimeout(() => {
      this.hideSnackbar();
    }, 5000);
  }

  hideSnackbar(): void {
    if (this.timeoutId) {
      clearTimeout(this.timeoutId);
    }

    this.hideAnimation = true;

    setTimeout(() => {
      this.showSnackbar = false;
      this.hideAnimation = false;
    }, 400); // Match animation duration
  }
}
