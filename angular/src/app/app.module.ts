import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { environment } from 'src/environments/environment';
import { ERROR_LEVEL, LoggerService, MyCoreModule } from 'src/lib/my-core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainModule } from './main';
import { SecurityModule } from './security';
import { DemosComponent } from './demos/demos.component';
import { CommonServicesModule } from './common-services';
import { DinamicoComponent } from './dinamico/dinamico.component';

@NgModule({
  declarations: [
    AppComponent,
    DemosComponent,
    DinamicoComponent
  ],
  imports: [
    BrowserModule, FormsModule,
    AppRoutingModule, MainModule, SecurityModule, MyCoreModule, CommonServicesModule,
  ],
  providers: [
    LoggerService,
    { provide: ERROR_LEVEL, useValue: environment.ERROR_LEVEL },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
