import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { isLogged } from './guards/route.guards';
import { CremationEntryComponent } from './components/cremation-entry/cremation-entry.component';
import { DeceasedComponent } from './components/deceased/deceased.component';

const guards = { canActivate: [isLogged] };

export const routes: Routes = [
  { path: 'home', component: HomeComponent, ...guards },
  { path: 'register', component: RegisterComponent, ...guards },
  { path: 'cremation-entry', component: CremationEntryComponent, ...guards },
  { path: 'deceased', component: DeceasedComponent, ...guards },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: '/login' },
];
