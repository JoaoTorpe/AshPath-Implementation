import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { map } from 'rxjs';
import { IUser as IUser, LoginRequest } from '../utils/models';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private _userSig = signal<null | IUser>(null);
  userSig = this._userSig.asReadonly();

  constructor(private http: HttpClient) { }

  signIn(req: LoginRequest) {
    return this.http.post<IUser>('/auth/login', req)
      .pipe(
        map((res) => {
          this._userSig.set(res);
          return res;
        })
      );
  }

  logout() {
    this._userSig.set(null);
  }
}
