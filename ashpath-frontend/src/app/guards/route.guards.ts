import { inject } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "../services/auth.service";

export const isLogged = (): boolean => {
  const authSvc = inject(AuthService);
  const router = inject(Router);

  if (authSvc.userSig()) {
    return true;
  }

  router.navigate(['/login']);
  return false;
};

export const isAdmin = (): boolean => {
  const authSvc = inject(AuthService);
  // const router = inject(Router);

  // if (authSvc.isAdmin()) {
  //   return true;
  // }

  return authSvc.isAdmin();
}