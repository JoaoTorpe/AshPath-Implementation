import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { AppRole } from '../../utils/models';
import { HttpErrorResponse } from '@angular/common/http';
import { CustomValidators } from '../../validators/custom-validators';

@Component({
  selector: 'app-register',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent {
  errorMessage: string | null = null;
  registerForm: FormGroup;
  formType = AppRole.VIEWER.toString();

  allAppRoles = Object.values(AppRole);

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService
  ) {
    this.registerForm = this.fb.group(
      {
        fullname: ['', [...CustomValidators.fullnameValidators]],
        email: ['', [...CustomValidators.emailValidators]],
        password: ['', [...CustomValidators.passwordValidators]],
        repeatPassword: ['', [...CustomValidators.passwordValidators]],
        specialization: ['', []],
      },
      {
        validators: [CustomValidators.passwordMatchValidator],
      }
    );
  }

  onFormTypeChange(event: Event): void {
    const selected = (event.target as HTMLSelectElement).value;
    this.formType = selected;

    if (this.formType === 'NECROTOMIST') {
      this.registerForm
        .get('specialization')
        ?.setValidators([
          Validators.required,
          Validators.maxLength(64),
          Validators.minLength(3),
        ]);
    } else {
      this.registerForm.get('specialization')?.clearValidators();
    }

    this.registerForm.get('specialization')?.updateValueAndValidity();
  }

  onSubmit(): void {
    if (this.registerForm.invalid) return;
    this.errorMessage = null;
    let request;

    switch (this.formType) {
      case 'ADMIN':
        request = this.authService.registerAdmin(this.registerForm.value);
        break;
      case 'NECROTOMIST':
        request = this.authService.registerNecrotomist(this.registerForm.value);
        break;
      default:
        request = this.authService.registerViewer(this.registerForm.value);
    }

    request.subscribe({
      next: (res) => this.onSuccess(),
      error: this.onError.bind(this),
    });
  }

  private onSuccess() {
    this.router.navigate(['/login']);
  }

  private onError(err: HttpErrorResponse) {
    console.log(err);
    this.errorMessage = 'Falha ao registrar. Tente novamente.';
  }

  navigateToLogin(): void {
    this.router.navigate(['/login']);
  }
}
