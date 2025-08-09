import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';

import { DeceasedService } from '../../services/deceased.service';
import { CremationEntryService } from '../../services/cremation-entry.service';
import { GraveService } from '../../services/grave.service';

import {
  CreateDeceasedRequest,
  CremationEntryResponse,
  GraveResponse,
} from '../../utils/models';
import { CustomValidators } from '../../validators/custom-validators';

@Component({
  selector: 'app-create-deceased',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, NgxMaskDirective],
  templateUrl: './create-deceased.component.html',
  styleUrls: ['./create-deceased.component.scss'],
  providers: [provideNgxMask()],
})
export class CreateDeceasedComponent implements OnInit {
  deceasedForm: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;
  selectedFile: File | null = null;

  cremationEntries$!: Observable<CremationEntryResponse[]>;
  allGraves$!: Observable<GraveResponse[]>;

  constructor(
    private fb: FormBuilder,
    private deceasedService: DeceasedService,
    private cremationEntryService: CremationEntryService,
    private graveService: GraveService,
    private router: Router
  ) {
    this.deceasedForm = this.fb.group({
      fullname: ['', [...CustomValidators.fullnameValidators]],
      birthDate: [
        '',
        [
          Validators.required,
          CustomValidators.isValidDate,
          CustomValidators.noFutureDate,
        ],
      ],
      deathDate: [
        '',
        [
          Validators.required,
          CustomValidators.isValidDate,
          CustomValidators.pastOrPresentDate,
        ],
      ],
      causeOfDeath: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(128),
        ],
      ],
      fatherName: ['', [...CustomValidators.fullnameValidators]],
      motherName: ['', [...CustomValidators.fullnameValidators]],
      graveID: [null],
      cremationEntryID: [null],
      deathCertificate: [null, [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.loadDropdownData();
  }

  private loadDropdownData(): void {
    this.cremationEntries$ = this.cremationEntryService.findAll();
    this.allGraves$ = this.graveService.findAll();
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.selectedFile = file;
      this.deceasedForm.patchValue({ deathCertificate: file });
    } else {
      this.selectedFile = null;
      this.deceasedForm.patchValue({ deathCertificate: null });
    }
    this.deceasedForm.get('deathCertificate')?.updateValueAndValidity();
  }

  private formatDateForApi(dateString: string): string {
    if (!dateString || dateString.length !== 10) {
      return '';
    }
    const parts = dateString.split('/');
    return parts.length !== 3 ? '' : `${parts[2]}-${parts[1]}-${parts[0]}`;
  }

  onSubmit(): void {
    this.errorMessage = null;
    this.successMessage = null;

    if (this.deceasedForm.invalid) {
      this.deceasedForm.markAllAsTouched();
      return;
    }

    if (!this.selectedFile) {
      this.errorMessage = 'Por favor, selecione o atestado de óbito.';
      return;
    }

    const formValues = this.deceasedForm.value;
    const requestData: CreateDeceasedRequest = {
      fullname: formValues.fullname,
      birthDate: this.formatDateForApi(formValues.birthDate),
      deathDate: this.formatDateForApi(formValues.deathDate),
      causeOfDeath: formValues.causeOfDeath,
      fatherName: formValues.fatherName,
      motherName: formValues.motherName,
      graveID: formValues.graveID ? Number(formValues.graveID) : null,
      cremationEntryID: formValues.cremationEntryID
        ? Number(formValues.cremationEntryID)
        : null,
    };

    this.deceasedService
      .createDeceased(requestData, this.selectedFile)
      .subscribe({
        next: () => this.onSuccess(),
        error: (err) => this.onError(err),
      });
  }

  private onSuccess(): void {
    this.successMessage = 'Falecido registrado com sucesso!';
    this.deceasedForm.reset();
    this.loadDropdownData();
  }

  private onError(err: HttpErrorResponse): void {
    console.error(err);
    this.errorMessage =
      err.error?.message || err.statusText || 'Registro Falhou.';
  }
}
