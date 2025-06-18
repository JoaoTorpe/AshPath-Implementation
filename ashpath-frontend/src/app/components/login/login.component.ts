import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { LoginRequest } from '../../utils/models';
import { CommonModule } from '@angular/common';
import { CustomValidators } from '../../validators/custom-validators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['login.component.scss'],
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule]
})
export class LoginComponent {
  errorMessage: string | null = null;
  loginForm: FormGroup;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
  ) {
    this.loginForm = this.fb.group({
      email: ['necrotomista1@ashpath.com', [...CustomValidators.emailValidators]],
      password: ['s3nh4@S', [...CustomValidators.passwordValidators]],
    });
  }

  navigateToRegister(): void {
    this.router.navigate(['/register']);
  }

  onSubmit(): void {
    if (this.loginForm.invalid) return;
    this.errorMessage = null;

    const request = this.loginForm.value as LoginRequest;

    this.authService.signIn(request).subscribe({
      next: (response) => {
        // console.log('Login bem-sucedido', response);
        this.router.navigate(['/home']); 
      },
      error: (err: HttpErrorResponse) => {
        if (err.status === 401) {
          this.errorMessage = 'Invalid email/password.';
        } 
        else if(err.status === 403) {
          this.errorMessage = 'Your account is pending approval.';
        }
        else {
          this.errorMessage = 'Failed to login. Please try again.';
        }
      },
    });
  }
}
