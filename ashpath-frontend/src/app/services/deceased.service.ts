import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { CreateDeceasedRequest, DeceasedResponse } from '../utils/models';

@Injectable({
  providedIn: 'root',
})
export class DeceasedService {
  private readonly baseUrl = '/deceased'; // Pode ser ajustado para a URL completa

  constructor(private http: HttpClient) {}

  findAll(): Observable<DeceasedResponse[]> {
    return this.http.get<DeceasedResponse[]>(`${this.baseUrl}/findAll`).pipe(tap(console.log));
  }

  findById(id: number): Observable<DeceasedResponse> {
    return this.http.get<DeceasedResponse>(`${this.baseUrl}/${id}`);
  }

  // Busca certificado em PDF
  getCertificatePdf(id: number): Observable<Blob> {
    const headers = new HttpHeaders({ Accept: 'application/pdf' });

    return this.http.get(`${this.baseUrl}/${id}/deathCertificate`, {
      headers: headers,
      responseType: 'blob',
    });
  }
  
  createDeceased(
    deceasedData: CreateDeceasedRequest,
    deathCertificateFile: File
  ): Observable<any> {
    const formData = new FormData();

    const deceasedDataBlob = new Blob([JSON.stringify(deceasedData)], {
      type: 'application/json',
    });
    formData.append('deceasedData', deceasedDataBlob);

    formData.append('deceasedDeathCertificate', deathCertificateFile);

    return this.http.post(this.baseUrl, formData);
  }
}
