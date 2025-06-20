import { HttpClient } from '@angular/common/http';
import { computed, Injectable, signal } from '@angular/core';
import { map } from 'rxjs';
import { SuccessfulLoginResponse as SuccessfulLoginResponse, LoginRequest, CreateAdminRequest, AdminUserResponse, CreateNecrotomistRequest, CreateViewerRequest, ViewerUserResponse, AppRole, UserResponse, UserCredentials } from '../utils/models';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _userSig = signal<null | SuccessfulLoginResponse>(null);
  userSig = this._userSig.asReadonly();
  isAdmin = computed(() => {
    return this._userSig()?.appRoleSet?.map(v => v.name).includes(AppRole.ADMIN)
      || false;
  });

  constructor(private http: HttpClient) {
    const storedUser = sessionStorage.getItem('user');
    if (storedUser) {
      this._userSig.set(JSON.parse(storedUser));
    }
  }

  getUsersPendingApproval() {
    return this.http.get<UserResponse[]>('/user/pending-approval');
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

  approveUser(userId: number) {
    return this.http.post<UserCredentials>(`/user/approve/${userId}`, { id: this.userSig()?.loggedUserId });
  }

  rejectUser(userId: number) {
    return this.http.post<UserCredentials>(`/user/reject/${userId}`, { id: this.userSig()?.loggedUserId });
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
