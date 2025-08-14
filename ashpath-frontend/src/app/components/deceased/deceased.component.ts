import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DeceasedService } from '../../services/deceased.service';
import { DeceasedResponse, DeceasedStatus } from '../../utils/models';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';
import { ActivatedRoute } from '@angular/router';
import { CremationEntryService } from '../../services/cremation-entry.service';

@Component({
  selector: 'app-deceased',
  standalone: true,
  imports: [CommonModule, FormsModule, NgxMaskDirective],
  templateUrl: './deceased.component.html',
  styleUrls: ['./deceased.component.scss'],
  providers: [provideNgxMask()],
})
export class DeceasedComponent implements OnInit {
  private deceasedService = inject(DeceasedService);
  private sanitizer = inject(DomSanitizer);
  protected readonly DeceasedStatus = DeceasedStatus;
  private route = inject(ActivatedRoute);

  public startDate: string = '';
  public endDate: string = '';
  public graveLocation: string = '';

  private allDeceaseds: DeceasedResponse[] = [];
  public filteredDeceaseds: DeceasedResponse[] = [];

  public showPdfModal = false;
  public showDetailsModal = false;
  public selectedDeceased: DeceasedResponse | null = null;

  public pdfUrl: SafeResourceUrl | null = null;
  public pdfBlob: Blob | null = null;
  public blobUrl: string | null = null;

  private cremationEntryService = inject(CremationEntryService);

  ngOnInit(): void {
    const locationParam = this.route.snapshot.paramMap.get('location');
    if (locationParam) {
      this.graveLocation = locationParam;
    }
    this.loadDeceaseds();
  }

  private setPdfBlob(blob: Blob) {
    this.cleanupBlobUrl();

    this.pdfBlob = blob;
    this.blobUrl = URL.createObjectURL(blob);
    this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.blobUrl);
  }

  public applyFilters(): void {
    this.filteredDeceaseds = Array.from(this.allDeceaseds);

    const filterStartDate = this.startDate ? this.parseDate(this.startDate) : null;
    const filterEndDate = this.endDate ? this.parseDate(this.endDate) : null;
    const location = this.graveLocation;

    if (filterStartDate !== null)
      this.filteredDeceaseds = this.applyStartDateDeath(filterStartDate, this.filteredDeceaseds);
    
    if (filterEndDate !== null)
      this.filteredDeceaseds = this.applyEndDateDeath(filterEndDate, this.filteredDeceaseds);

    if (location !== undefined && location !== null && location !== '')
      this.filteredDeceaseds = this.filterByGraveLocation(location, this.filteredDeceaseds);
  }

  private applyStartDateDeath(startDate: Date, deceaseds: DeceasedResponse[]): DeceasedResponse[]
  {
    let filteredDeceaseds: DeceasedResponse[] = deceaseds.filter(deceased => {
      const deathDate = new Date(deceased.deathDate);
      return deathDate >= startDate;
    });

    return filteredDeceaseds;
  }

  private applyEndDateDeath(endDate: Date, deceaseds: DeceasedResponse[]): DeceasedResponse[]
  {
    let filteredDeceaseds: DeceasedResponse[] = deceaseds.filter(deceased => {
      const deathDate = new Date(deceased.deathDate);
      return deathDate <= endDate;
    });

    return filteredDeceaseds;
  }

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
        this.setPdfBlob(blob);
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

  public executeCremation(deceasedId: number | undefined): void
  {
    if (deceasedId)
    {
      console.log(`Informed 'deceasedId': ${deceasedId}`);

      this.cremationEntryService.executeCremation(deceasedId).subscribe({
        next: () => {},
        error: (err) => {}
      });
      this.closeModal();

      window.location.reload();
    }
  }

  private loadDeceaseds(): void {
    this.deceasedService.findAll().subscribe({
      next: (data) => {
        this.allDeceaseds = Array.from(data);
        this.applyFilters();
      },
      error: (err) => {
        console.error('Falha ao carregar os dados dos falecidos:', err);
      },
    });
  }

  private filterByGraveLocation(
    graveLocation: string,
    deceaseds: DeceasedResponse[]
  ): DeceasedResponse[] {
    let gravedDeceaseds = deceaseds.filter(
      (deceased) => deceased.status === DeceasedStatus.GRAVED
    );
    let gravedDeceasedsByLocation = gravedDeceaseds.filter((deceased) =>
      deceased.graveLocation
        ?.toLowerCase()
        .includes(graveLocation.toLowerCase())
    );

    return gravedDeceasedsByLocation;
  }

  private parseDate(dateString: string | null): Date | null {
    if (!dateString || dateString.length !== 8) return null;

    dateString = dateString.replaceAll('/', '');

    const day = parseInt(dateString.slice(0, 2), 10);
    const month = parseInt(dateString.slice(2, 4), 10) - 1;
    const year = parseInt(dateString.slice(4), 10);

    if (isNaN(day) || isNaN(month) || isNaN(year)) return null;

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

  private cleanupBlobUrl() {
    if (this.blobUrl) {
      URL.revokeObjectURL(this.blobUrl);
      this.blobUrl = null;
      this.pdfUrl = null;
    }
  }

  ngOnDestroy() {
    this.cleanupBlobUrl();
  }
}
