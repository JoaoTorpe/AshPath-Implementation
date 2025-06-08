import { inject } from "@angular/core"
import { LoginService } from "../services/login.service"

export const isLogged = () => {
    const loginSvc = inject(LoginService);

    if(loginSvc.userSig()) return true;

    return false;
}