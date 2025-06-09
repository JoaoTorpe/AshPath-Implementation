import { CommonModule } from '@angular/common';
import { Component, computed } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { AppRole, CreateAdminRequest, CreateNecrotomistRequest, LoginRequest } from '../../utils/models';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  userSig;
  isAdmin;
  errorMessage: string | null = null;
  formGroup: FormGroup;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: LoginService,
  ) {
    this.formGroup = this.fb.group({
      email: ['necrotomista1@ashpath.com', Validators.required],
      password: ['senha123', Validators.required],
    });
    this.userSig = authService.userSig;
    this.isAdmin = computed(() => this.userSig()
      ?.appRole
      .some((v) => v === AppRole.ADMIN)
    )
  }

  onSubmit(): void {
    if (this.formGroup.invalid) return;
    this.errorMessage = null;

    const request = this.isAdmin() 
      ? this.formGroup.value as CreateAdminRequest
      : this.formGroup.value as CreateNecrotomistRequest;

    this.authService.signIn(request).subscribe({
      next: (response) => {
        this.router.navigate(['/login']);
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
