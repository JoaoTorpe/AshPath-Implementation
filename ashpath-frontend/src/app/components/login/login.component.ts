import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { LoginRequest } from '../../utils/models';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['login.component.scss'],
  standalone: true,
  imports: [ReactiveFormsModule]
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
      email: ['necrotomista1@ashpath.com', Validators.required],
      password: ['senha123', Validators.required],
    });
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
          this.errorMessage = 'Usuário ou senha inválidos.';
        } else {
          this.errorMessage = 'Erro ao tentar login. Tente novamente.';
        }
      },
    });;
  }
}
