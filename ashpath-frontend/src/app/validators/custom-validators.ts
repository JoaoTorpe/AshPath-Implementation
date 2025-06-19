import { AbstractControl, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';

export class CustomValidators {
    static passwordValidators = [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(12),
        Validators.pattern(/^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z]).+$/),
    ];
    static emailValidators = [
        Validators.required,
        Validators.maxLength(64),
        Validators.email,
    ];

    static fullnameValidators = [
        Validators.required, Validators.maxLength(64), Validators.minLength(3),
    ];

    static passwordMatchValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
        const password = control.get('password');
        const repeatPassword = control.get('repeatPassword');

        if (password && repeatPassword && password.value !== repeatPassword.value) {
            repeatPassword.setErrors({ ...repeatPassword.errors, mismatch: true });
            return { mismatch: true };
        }

        return null;
    }
}