import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CremationEntryService } from '../../services/cremation-entry.service';
import { CremationEntryResponse, DeceasedStatus } from '../../utils/models';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cremation-entry',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cremation-entry.component.html',
  styleUrl: './cremation-entry.component.scss',
})
export class CremationEntryComponent implements OnInit {
  private cremationService = inject(CremationEntryService);

  private allEntries: CremationEntryResponse[] = [];

  public startDate: string = '';
  public endDate: string = '';
  public selectedStatus: DeceasedStatus | 'ALL' = 'ALL';
  public DeceasedStatusEnum = DeceasedStatus;

  public get filteredEntries(): CremationEntryResponse[] {
    let entries = this.allEntries;

    if (this.startDate) {
      const start = new Date(this.startDate);
      start.setUTCHours(0, 0, 0, 0);
      entries = entries.filter(
        (entry) => new Date(entry.creationDate) >= start
      );
    }
    if (this.endDate) {
      const end = new Date(this.endDate);
      end.setUTCHours(23, 59, 59, 999);
      entries = entries.filter((entry) => new Date(entry.creationDate) <= end);
    }
    if (this.selectedStatus === 'ALL') {
      return entries;
    }

    return entries.map((entry) => {
      const filteredEntry = { ...entry };
      if (entry.deceaseds) {
        filteredEntry.deceaseds = entry.deceaseds.filter(
          (deceased) => deceased.status === this.selectedStatus
        );
      }
      return filteredEntry;
    });
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
