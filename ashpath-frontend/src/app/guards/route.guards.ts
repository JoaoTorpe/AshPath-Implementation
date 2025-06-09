import { inject } from "@angular/core"
import { AuthService } from "../services/auth.service"

export const isLogged = () => {
    const loginSvc = inject(AuthService);

    if(loginSvc.userSig()) return true;

    return false;
}