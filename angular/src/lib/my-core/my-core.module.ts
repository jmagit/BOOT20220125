import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SizerComponent } from './components/sizer.component';
import { PIPES_CADENAS } from './pipes/cadenas.pipe';
import { PIPES_NUMERICOS } from './pipes/numericos.pipe';



@NgModule({
  declarations: [
    SizerComponent, PIPES_CADENAS, PIPES_NUMERICOS,
  ],
  exports: [
    SizerComponent, PIPES_CADENAS, PIPES_NUMERICOS,
  ],
  imports: [
    CommonModule
  ]
})
export class MyCoreModule { }
