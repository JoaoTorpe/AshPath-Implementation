import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GraveResponse } from '../utils/models';

@Injectable({
  providedIn: 'root',
})
export class GraveService {
  private readonly baseUrl = '/grave';

  constructor(private http: HttpClient) {}

  findAll(): Observable<GraveResponse[]> {
    return this.http.get<GraveResponse[]>(this.baseUrl);
  }
}
