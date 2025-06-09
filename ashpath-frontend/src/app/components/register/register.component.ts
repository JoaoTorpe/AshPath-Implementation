import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { AppRole } from '../../utils/models';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  errorMessage: string | null = null;
  adminFormGroup: FormGroup;
  necrotomistFormGroup: FormGroup;
  formType = '';

  userSig;
  allAppRoles = Object.values(AppRole);

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
  ) {
    this.adminFormGroup = this.fb.group({
      fullname: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required],
    });

    this.necrotomistFormGroup = this.fb.group({
      fullname: ['', Validators.required],
      specialization: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required],
    });

    this.userSig = authService.userSig;
  }

  onFormTypeChange(event: Event): void {
    const selected = (event.target as HTMLSelectElement).value;
    this.formType = selected;
  }

  onSubmitNecro(): void {
    if (this.necrotomistFormGroup.invalid) return;
    this.errorMessage = null;

    this.authService.registerNecrotomist(this.necrotomistFormGroup.value)
      .subscribe({
        next: (res) => {
          this.router.navigate(['/login']);
        },
        error: this.error.bind(this),
      });
  }

  onSubmitAdmin(): void {
    if (this.adminFormGroup.invalid) return;
    this.errorMessage = null;

    this.authService.registerAdmin(this.adminFormGroup.value)
      .subscribe({
        next: (res) => {
          this.router.navigate(['/login']);
        },
        error: this.error.bind(this),
      });
  }

  private error(err: HttpErrorResponse) {
    console.log(err);
    this.errorMessage = 'Erro ao registrar. Tente novamente.';
  }
}
