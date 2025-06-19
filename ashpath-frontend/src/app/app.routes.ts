import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { isAdmin, isLogged } from './guards/route.guards';
import { CremationEntryComponent } from './components/cremation-entry/cremation-entry.component';
import { DeceasedComponent } from './components/deceased/deceased.component';
import { NotFoundPageComponent } from './components/not-found-page/not-found-page.component';
import { UserApprovalPageComponent } from './components/user-approval-page/user-approval-page.component';

export const routes: Routes = [
  // Public Routes
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },

  // Protected Routes (requires isLogged)
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [isLogged],
  },
  {
    path: 'cremation-entry',
    component: CremationEntryComponent,
    canActivate: [isLogged],
  },
  {
    path: 'deceased',
    component: DeceasedComponent,
    canActivate: [isLogged],
  },

  // Admin Route (requires isLogged and isAdmin)
  {
    path: 'user-approval',
    component: UserApprovalPageComponent,
    canActivate: [isLogged, isAdmin],
  },

  // Error/Fallback Routes
  {
    path: 'not-found',
    component: NotFoundPageComponent,
  },
  {
    path: 'error',
    redirectTo: '/not-found',
  },
  {
    path: '**',
    redirectTo: '/not-found',
  },
];
