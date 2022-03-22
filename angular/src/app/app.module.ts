import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { LoggerService, MyCoreModule } from 'src/lib/my-core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainModule } from './main';
import { SecurityModule } from './security';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule, FormsModule,
    AppRoutingModule, MainModule, SecurityModule, MyCoreModule,
  ],
  providers: [
    LoggerService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
