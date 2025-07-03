import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CremationEntryService } from '../../services/cremation-entry.service';
import { CremationEntryResponse, DeceasedStatus } from '../../utils/models';
import { CommonModule } from '@angular/common';
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';

@Component({
  selector: 'app-cremation-entry',
  standalone: true,
  imports: [CommonModule, FormsModule, NgxMaskDirective],
  templateUrl: './cremation-entry.component.html',
  styleUrl: './cremation-entry.component.scss',
  providers: [provideNgxMask()],
})
export class CremationEntryComponent implements OnInit {
  private cremationService = inject(CremationEntryService);

  private allEntries: CremationEntryResponse[] = [];

  public startDate: string = '';
  public endDate: string = '';
  public selectedStatus: DeceasedStatus | 'ALL' = 'ALL';
  public DeceasedStatusEnum = DeceasedStatus;

  private parseDate(dateString: string): Date | null {
    if (!dateString || dateString.length !== 10) {
      return null;
    }
    const parts = dateString.split('/');
    if (parts.length !== 3) {
      return null;
    }
    const day = parseInt(parts[0], 10);
    const month = parseInt(parts[1], 10) - 1;
    const year = parseInt(parts[2], 10);

    if (isNaN(day) || isNaN(month) || isNaN(year)) {
      return null;
    }

    const date = new Date(year, month, day);
    if (
      date.getFullYear() !== year ||
      date.getMonth() !== month ||
      date.getDate() !== day
    ) {
      return null;
    }

    return date;
  }

  public get filteredEntries(): CremationEntryResponse[] {
    let entries = this.allEntries;

    const start = this.parseDate(this.startDate);
    if (start) {
      start.setUTCHours(0, 0, 0, 0);
      entries = entries.filter(
        (entry) => new Date(entry.creationDate) >= start
      );
    }

    const end = this.parseDate(this.endDate);
    if (end) {
      end.setUTCHours(23, 59, 59, 999);
      entries = entries.filter((entry) => new Date(entry.creationDate) <= end);
    }
    if (this.selectedStatus === 'ALL') {
      return entries;
    }

    return entries
      .map((entry) => {
        const filteredDeceaseds = entry.deceaseds?.filter(
          (deceased) => deceased.status === this.selectedStatus
        );
        return { ...entry, deceaseds: filteredDeceaseds };
      })
      .filter((entry) => entry.deceaseds && entry.deceaseds.length > 0);
  }

  ngOnInit(): void {
    this.loadCremationEntries();
  }

  private loadCremationEntries(): void {
    this.cremationService.findAll().subscribe({
      next: (data) => {
        this.allEntries = data;
        console.log('Dados carregados:', this.allEntries);
      },
      error: (err) => {
        console.error('Falha ao carregar as entradas de cremação:', err);
      },
    });
  }

  public applyFilter(status: DeceasedStatus | 'ALL'): void {
    this.selectedStatus = status;
  }
}
