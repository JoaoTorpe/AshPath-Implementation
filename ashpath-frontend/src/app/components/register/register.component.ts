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
  viewerFormGroup: FormGroup;
  formType = '';

  userSig;
  allAppRoles = Object.values(AppRole);

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
  ) {
    this.userSig = authService.userSig;

    this.adminFormGroup = this.fb.group({
      fullname: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required],
      userId: [this.userSig()!!.loggedUserId],
    });

    this.necrotomistFormGroup = this.fb.group({
      fullname: ['', Validators.required],
      specialization: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required],
      userId: [this.userSig()!!.loggedUserId],
    });

    this.viewerFormGroup = this.fb.group({
      fullname: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required],
      userId: [this.userSig()!!.loggedUserId],
    });
  }

  onFormTypeChange(event: Event): void {
    const selected = (event.target as HTMLSelectElement).value;
    this.formType = selected;
  }

  onSubmitViewer(): void {
    if (this.viewerFormGroup.invalid) return;
    this.errorMessage = null;

    this.authService.registerViewer(this.viewerFormGroup.value)
      .subscribe({
        next: (res) => {
          this.onSuccess();
        },
        error: this.onError.bind(this),
      });
  }

  onSubmitNecro(): void {
    if (this.necrotomistFormGroup.invalid) return;
    this.errorMessage = null;

    this.authService.registerNecrotomist(this.necrotomistFormGroup.value)
      .subscribe({
        next: (res) => {
          this.onSuccess();
        },
        error: this.onError.bind(this),
      });
  }

  onSubmitAdmin(): void {
    if (this.adminFormGroup.invalid) return;
    this.errorMessage = null;

    this.authService.registerAdmin(this.adminFormGroup.value)
      .subscribe({
        next: (res) => {
          this.onSuccess();
        },
        error: this.onError.bind(this),
      });
  }

  private onSuccess() {
    this.router.navigate(['/home']);
  }

  private onError(err: HttpErrorResponse) {
    console.log(err);
    if (err.status === 403) {
      this.errorMessage = `You don't have permission to create user of type ${this.formType}.`;
    }
    else {
      this.errorMessage = 'Failed to register. Please try again.';
    }
  }
}
