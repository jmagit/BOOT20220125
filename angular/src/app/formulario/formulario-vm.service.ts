import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FormularioVMService {
  private elemento: any = {}
  public listado: Array<any> = []
  private modo: 'add' | 'edit' = 'add'

  constructor(private http: HttpClient) { }

  get Elemento(): any { return this.elemento; }

  list() {
    this.http.get<Array<any>>(`http://localhost:4321/api/personas`).subscribe({
      next: data => {
        this.listado = data
      },
      error: err => console.log(err)
    })
  }
  add() {
    this.elemento = {}
    this.modo = 'add'
  }

  edit(id: any) {
    this.http.get(`http://localhost:4321/api/personas/${id}`).subscribe({
      next: data => {
        this.elemento = data
        this.modo = 'edit'
      },
      error: err => console.log(err)
    })
    // this.elemento = { id: id, nombre: 'Pepito', apellidos: 'Grillo', email: 'pepito@grillo', nif: '12345678z', edad: 99 }
    // this.modo = 'edit'
  }

  cancel() {

  }

  send() {
    // alert( (this.modo === 'add' ? 'POST ' : 'PUT ') + JSON.stringify(this.elemento))
    if(this.modo === 'add') {
      this.http.post(`http://localhost:4321/api/personas`, this.elemento).subscribe({
        next: data => {
          alert('OK')
        },
        error: err => console.log(err)
      })
    } else {
      this.http.put(`http://localhost:4321/api/personas/${this.elemento.id}`, this.elemento).subscribe({
        next: data => {
          alert('OK')
        },
        error: err => console.log(err)
      })
    }
  }
}
