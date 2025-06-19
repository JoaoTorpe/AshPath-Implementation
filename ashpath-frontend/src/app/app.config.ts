import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), 
    provideRouter(routes), 
    provideHttpClient(), 
    /**
     * With HashLocationStrategy, everything after the # is ignored by the server 
     * — only Angular sees it. So even if the user reloads a URL, the server sees 
     * just /, and Angular takes care of routing after the app boots.
    */
    { provide: LocationStrategy, useClass: HashLocationStrategy },
  ],
};
