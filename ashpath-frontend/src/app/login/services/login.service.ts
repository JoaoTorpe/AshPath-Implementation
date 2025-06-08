import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { map } from 'rxjs';
import { IUser as IUser, LoginRequest } from '../../utils/models';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private _user = signal<null | IUser>(null);
  user = this._user.asReadonly();

  constructor(private http: HttpClient) { }

  signIn(req: LoginRequest) {
    return this.http.post<IUser>('/auth/login', req)
      .pipe(
        map((res) => {
          this._user.set(res);
          return res;
        })
      );
  }

  signOut() {
    this._user.set(null);
  }
}
