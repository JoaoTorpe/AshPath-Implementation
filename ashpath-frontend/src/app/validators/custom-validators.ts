import {
  AbstractControl,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';

export class CustomValidators {
  static passwordValidators = [
    Validators.required,
    Validators.minLength(6),
    Validators.maxLength(12),
    Validators.pattern(/^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z]).+$/),
  ];
  static emailValidators = [
    Validators.required,
    Validators.maxLength(254),
    Validators.email,
  ];

  static fullnameValidators = [
    Validators.required,
    Validators.maxLength(64),
    Validators.minLength(3),
  ];

  static passwordMatchValidator: ValidatorFn = (
    control: AbstractControl
  ): ValidationErrors | null => {
    const password = control.get('password');
    const repeatPassword = control.get('repeatPassword');

    if (password && repeatPassword && password.value !== repeatPassword.value) {
      repeatPassword.setErrors({ ...repeatPassword.errors, mismatch: true });
      return { mismatch: true };
    }

    return null;
  };

  static isValidDate(control: AbstractControl): ValidationErrors | null {
    const dateStr = control.value;
    if (!dateStr || dateStr.length !== 10) {
      return null;
    }

    const parts = dateStr.split('/');
    const day = parseInt(parts[0], 10);
    const month = parseInt(parts[1], 10);
    const year = parseInt(parts[2], 10);

    if (isNaN(day) || isNaN(month) || isNaN(year)) {
      return { invalidDate: true };
    }

    const date = new Date(year, month - 1, day);

    if (
      date.getFullYear() !== year ||
      date.getMonth() !== month - 1 ||
      date.getDate() !== day
    ) {
      return { invalidDate: true };
    }

    return null;
  }

  static noFutureDate(control: AbstractControl): ValidationErrors | null {
    const dateStr = control.value;
    if (!dateStr || dateStr.length !== 10) {
      return null;
    }

    const parts = dateStr.split('/');
    const inputDate = new Date(+parts[2], +parts[1] - 1, +parts[0]);

    const today = new Date();
    today.setHours(0, 0, 0, 0);

    if (inputDate >= today) {
      return { noFutureDate: true };
    }

    return null;
  }

  static pastOrPresentDate(control: AbstractControl): ValidationErrors | null {
    const dateStr = control.value;
    if (!dateStr || dateStr.length !== 10) {
      return null;
    }

    const parts = dateStr.split('/');
    const inputDate = new Date(+parts[2], +parts[1] - 1, +parts[0]);
    const today = new Date();

    inputDate.setHours(0, 0, 0, 0);
    today.setHours(0, 0, 0, 0);

    if (inputDate > today) {
      return { pastOrPresentDate: true };
    }

    return null;
  }
}
