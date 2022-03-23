import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SizerComponent } from './components/sizer.component';
import { PIPES_CADENAS } from './pipes/cadenas.pipe';



@NgModule({
  declarations: [
    SizerComponent, PIPES_CADENAS,
  ],
  exports: [
    SizerComponent, PIPES_CADENAS,
  ],
  imports: [
    CommonModule
  ]
})
export class MyCoreModule { }
