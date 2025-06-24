import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DeceasedService } from '../../services/deceased.service';
import {DeceasedResponse, DeceasedStatus} from '../../utils/models';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';

@Component({
  selector: 'app-deceased',
  standalone: true,
  imports: [CommonModule, FormsModule, NgxMaskDirective, NgxMaskPipe],
  templateUrl: './deceased.component.html',
  styleUrls: ['./deceased.component.scss'],
  providers: [provideNgxMask()]
})
export class DeceasedComponent implements OnInit {
  private deceasedService = inject(DeceasedService);
  private sanitizer = inject(DomSanitizer);

  private allDeceaseds: DeceasedResponse[] = [];

  public filteredDeceaseds: DeceasedResponse[] = [];
  public pdfUrl: SafeResourceUrl | null = null;
  public showPdfModal = false;
  public showDetailsModal = false;
  public selectedDeceased: DeceasedResponse | null = null;


  // Filtros com ngModel
  public startDate: string = '';
  public endDate: string = '';
  public graveLocation: string = '';

  ngOnInit(): void {
    this.loadDeceaseds();
    // Aplica filtro vazio para carregar todos inicialmente
    this.applyFilters();
  }

  private loadDeceaseds(): void {
    // Dados de teste - remova depois de verificar
    this.allDeceaseds = [{
      id: 1,
      fullname: 'Teste',
      birthDate: '2000-01-01',
      deathDate: '2023-01-01',
      causeOfDeath: 'Teste',
      status: DeceasedStatus.GRAVED,
      graveLocation: 'Teste',
      fatherName: 'Pai Teste',    // Adicionado
      motherName: 'Mãe Teste',    // Adicionado
    },
      {
        id: 2,
        fullname: 'Teste',
        birthDate: '1978-01-01',
        deathDate: '2005-01-01',
        causeOfDeath: 'Teste',
        status: DeceasedStatus.GRAVED,
        graveLocation: 'Teste',
        fatherName: 'Pai Teste',    // Adicionado
        motherName: 'Mãe Teste',    // Adicionado
      },
      {
        id: 3,
        fullname: 'Teste',
        birthDate: '1998-01-01',
        deathDate: '2023-01-01',
        causeOfDeath: 'Teste',
        status: DeceasedStatus.GRAVED,
        graveLocation: 'Teste',
        fatherName: 'Pai Teste',    // Adicionado
        motherName: 'Mãe Teste',    // Adicionado
      },
      {
        id: 4,
        fullname: 'Teste',
        birthDate: '2000-01-01',
        deathDate: '2025-01-01',
        causeOfDeath: 'Teste',
        status: DeceasedStatus.GRAVED,
        graveLocation: 'Teste',
        fatherName: 'Pai Teste',    // Adicionado
        motherName: 'Mãe Teste',    // Adicionado
      }];
    this.filteredDeceaseds = [...this.allDeceaseds];
  }

/*  private loadDeceaseds(): void {
    this.deceasedService.findAll().subscribe({
      next: (data) => {
        console.log('Dados recebidos do backend:', data); // ← Adicione esta linha
        this.allDeceaseds = data;
        this.filteredDeceaseds = [...data];
      },
      error: (err) => {
        console.error('Erro completo:', err);
        console.error('Status:', err.status);
        console.error('Mensagem:', err.message);
        console.error('URL:', err.url);
        console.error('Corpo do erro:', err.error);
      }
    });
  }
*/
  public applyFilters(): void {
    // Se nenhum filtro estiver preenchido, mostra todos
    if (!this.startDate && !this.endDate && !this.graveLocation) {
      this.filteredDeceaseds = [...this.allDeceaseds];
      return;
    }

    // Converte as datas de filtro para objetos Date
    const filterStartDate = this.startDate ? this.parseDate(this.startDate) : null;
    const filterEndDate = this.endDate ? this.parseDate(this.endDate) : null;

    this.filteredDeceaseds = this.allDeceaseds.filter(deceased => {
      const deathDate = this.parseDate(deceased.deathDate);

      const matchesDate =
        (!filterStartDate || deathDate >= filterStartDate) &&
        (!filterEndDate || deathDate <= filterEndDate);

      const matchesLocation = this.graveLocation
        ? deceased.graveLocation?.toLowerCase().includes(this.graveLocation.toLowerCase())
        : true;

      return matchesDate && matchesLocation;
    });
  }

// Novo método para parse de datas
  private parseDate(dateString: string | null): Date | null {
    if (!dateString) return null;

    // Se a data está no formato dd/mm/aaaa (do input com máscara)
    if (dateString.includes('/')) {
      const [day, month, year] = dateString.split('/');
      return new Date(`${year}-${month}-${day}`);
    }

    // Se a data já está no formato aaaa-mm-dd (dos dados mockados)
    return new Date(dateString);
  }

  /*private convertToIsoDate(dateString: string): string {
    if (!dateString) return '';

    // Converte de dd/mm/aaaa para aaaa-mm-dd
    const parts = dateString.split('/');
    if (parts.length === 3) {
      return `${parts[2]}-${parts[1]}-${parts[0]}`;
    }
    return dateString; // Retorna original se não estiver no formato esperado
  }
  */

  public resetFilters(): void {
    this.startDate = '';
    this.endDate = '';
    this.graveLocation = '';
    this.filteredDeceaseds = [...this.allDeceaseds];
  }
  public viewDetails(deceased: DeceasedResponse): void {
    this.selectedDeceased = deceased;
    this.showDetailsModal = true;
  }

  public openCertificate(id: number): void {
    this.deceasedService.getCertificatePdf(id).subscribe({
      next: (blob) => {
        const url = URL.createObjectURL(blob);
        this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
        this.showPdfModal = true;
      },
      error: () => {
        this.pdfUrl = null;
        this.showPdfModal = false;
        console.error('Erro ao carregar certificado de óbito');
      },
    });
  }

  public closeModal(): void {
    this.showPdfModal = false;
    this.showDetailsModal = false;
    this.pdfUrl = null;
  }

  protected readonly DeceasedStatus = DeceasedStatus;
}
