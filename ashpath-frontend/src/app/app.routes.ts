import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { isLogged } from './guards/route.guards';

const guards = { canActivate: [isLogged] }

export const routes: Routes = [
    { path: 'home', component: HomeComponent, ...guards },
    { path: 'register', component: RegisterComponent, },
    { path: "login", component: LoginComponent, },
    { path: "**", redirectTo: "/login" },
];
