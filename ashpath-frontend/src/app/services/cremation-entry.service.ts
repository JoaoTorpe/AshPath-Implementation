import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CremationEntryResponse } from '../utils/models';

@Injectable({
  providedIn: 'root',
})
export class CremationEntryService {
  private readonly baseUrl = '/cremationEntry';

  constructor(private http: HttpClient) {}

  findAll(): Observable<CremationEntryResponse[]> {
    return this.http.get<CremationEntryResponse[]>(`${this.baseUrl}/findAll`);
  }

  findById(id: number): Observable<CremationEntryResponse> {
    return this.http.get<CremationEntryResponse>(`${this.baseUrl}/find/${id}`);
  }

  executeCremation(deceasedId: number): Observable<any>
  {
    return this.http.post(`${this.baseUrl}/execute_cremation/${deceasedId}`, {});
  }
}
