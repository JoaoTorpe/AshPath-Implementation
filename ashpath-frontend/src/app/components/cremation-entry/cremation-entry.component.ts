import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { CremationEntryService } from '../../services/cremation-entry.service';
import { CremationEntryResponse } from '../../utils/models';

@Component({
  selector: 'app-cremation-entry',
  imports: [CommonModule],
  templateUrl: './cremation-entry.component.html',
  styleUrl: './cremation-entry.component.scss',
})
export class CremationEntryComponent implements OnInit {
  private cremationService = inject(CremationEntryService);

  public entries: CremationEntryResponse[] = [];

  ngOnInit(): void {
    this.loadCremationEntries();
  }

  private loadCremationEntries(): void {
    this.cremationService.findAll().subscribe({
      next: (data) => {
        this.entries = data;
        console.log('Dados carregados:', this.entries);
      },
      error: (err) => {
        console.error('Falha ao carregar cremation-entries', err);
      },
    });
  }
}
