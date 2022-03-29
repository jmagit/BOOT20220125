import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SizerComponent } from './components/sizer.component';
import { PIPES_CADENAS } from './pipes/cadenas.pipe';
import { PIPES_NUMERICOS } from './pipes/numericos.pipe';
import { MIS_VALIDADORES } from './directives/validadores/mis-validaciones.directive';
import { DIRECTIVAS_ATRIBUTO } from './directives/atributos.directive';
import { UnlessDirective } from './directives/estructurales.directive';



@NgModule({
  declarations: [
    SizerComponent, PIPES_CADENAS, PIPES_NUMERICOS, MIS_VALIDADORES, DIRECTIVAS_ATRIBUTO,
    UnlessDirective,
  ],
  exports: [
    SizerComponent, PIPES_CADENAS, PIPES_NUMERICOS, MIS_VALIDADORES, DIRECTIVAS_ATRIBUTO,
    UnlessDirective,
  ],
  imports: [
    CommonModule
  ]
})
export class MyCoreModule { }
