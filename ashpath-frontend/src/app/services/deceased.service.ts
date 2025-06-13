import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DeceasedResponse } from '../utils/models';

@Injectable({
  providedIn: 'root',
})
export class DeceasedService {
  private readonly baseUrl = '/deceased';  // Pode ser ajustado para a URL completa

  constructor(private http: HttpClient) {}

  // Busca todos os registros
  findAll(): Observable<DeceasedResponse[]> {
    return this.http.get<DeceasedResponse[]>(`${this.baseUrl}/findAll`);
  }

  // Busca por ID
  findById(id: number): Observable<DeceasedResponse> {
    return this.http.get<DeceasedResponse>(`${this.baseUrl}/find/${id}`);
  }

  // Busca certificado em PDF
  getCertificatePdf(id: number): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/certificate/${id}`, {
      responseType: 'blob',
    });
  }
}
