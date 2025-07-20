import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DeceasedResponse } from '../utils/models';

@Injectable({
  providedIn: 'root',
})
export class DeceasedService
{
  private readonly baseUrl = '/deceased';  // Pode ser ajustado para a URL completa

  constructor(private http: HttpClient) {}

  findAll(): Observable<DeceasedResponse[]>
  {
    return this.http.get<DeceasedResponse[]>(`${this.baseUrl}/findAll`);
  }

  findById(id: number): Observable<DeceasedResponse>
  {
    return this.http.get<DeceasedResponse>(`${this.baseUrl}/${id}`);
  }

  // Busca certificado em PDF
  getCertificatePdf(id: number): Observable<Blob>
  {
    const headers = new HttpHeaders({ "Accept": "application/pdf" });

    return this.http.get(
      `${this.baseUrl}/${id}/deathCertificate`,
      {
        headers: headers,
        responseType: 'blob',
      }
    );
  }
}
