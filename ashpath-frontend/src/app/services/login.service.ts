import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { map } from 'rxjs';
import { SuccessfulLoginResponse as SuccessfulLoginResponse, LoginRequest } from '../utils/models';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private _userSig = signal<null | SuccessfulLoginResponse>(null);
  userSig = this._userSig.asReadonly();

  constructor(private http: HttpClient) {
    const storedUser = sessionStorage.getItem('user');
    if (storedUser) {
      this._userSig.set(JSON.parse(storedUser));
    }
  }

  signIn(req: LoginRequest) {
    return this.http.post<SuccessfulLoginResponse>('/auth/login', req)
      .pipe(
        map((res) => {
          sessionStorage.setItem('user', JSON.stringify(res));
          this._userSig.set(res);
          return res;
        })
      );
  }

  logout() {
    sessionStorage.removeItem('user');
    this._userSig.set(null);
  }
}
