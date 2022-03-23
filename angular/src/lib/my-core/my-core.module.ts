import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SizerComponent } from './components/sizer.component';



@NgModule({
  declarations: [
    SizerComponent,
  ],
  exports: [
    SizerComponent,
  ],
  imports: [
    CommonModule
  ]
})
export class MyCoreModule { }
