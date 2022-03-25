import { Component, OnInit } from '@angular/core';
import { FormularioVMService } from './formulario-vm.service';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  constructor(public VM: FormularioVMService) { }

  ngOnInit(): void {
  }

}
