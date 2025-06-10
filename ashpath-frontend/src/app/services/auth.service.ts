import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { map } from 'rxjs';
import { SuccessfulLoginResponse as SuccessfulLoginResponse, LoginRequest, CreateAdminRequest, AdminUserResponse, CreateNecrotomistRequest, CreateViewerRequest, ViewerUserResponse } from '../utils/models';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
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

  registerAdmin(req: CreateAdminRequest) {
    return this.http.post<AdminUserResponse>('/user/admin', req);
  }

  registerNecrotomist(req: CreateNecrotomistRequest) {
    return this.http.post<AdminUserResponse>('/user/necrotomist', req);
  }

  registerViewer(req: CreateViewerRequest) {
    return this.http.post<ViewerUserResponse>('/user/viewer', req);
  }
}
