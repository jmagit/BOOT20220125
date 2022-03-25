import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FormularioVMService {
  private elemento: any = {}
  private modo: 'add' | 'edit' = 'add'

  constructor() { }

  get Elemento(): any { return this.elemento; }

  add() {
    this.elemento = {}
    this.modo = 'add'
  }

  edit(id: any) {
    this.elemento = { id: id, nombre: 'Pepito', apellidos: 'Grillo', email: 'pepito@grillo', nif: '12345678z', edad: 99 }
    this.modo = 'edit'
  }

  cancel() {

  }

  send() {
    alert( (this.modo === 'add' ? 'POST ' : 'PUT ') + JSON.stringify(this.elemento))
  }
}
