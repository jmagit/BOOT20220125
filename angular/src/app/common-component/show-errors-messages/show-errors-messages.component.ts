import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-show-errors-messages',
  templateUrl: './show-errors-messages.component.html',
  styleUrls: ['./show-errors-messages.component.css']
})
export class ShowErrorsMessagesComponent implements OnChanges  {
  @Input() errors: any;

  mensaje = '';
  hidden = false;

  constructor() { }

  ngOnChanges(changes: SimpleChanges): void {
    if (!this.errors) {
      this.hidden = true;
      return;
    }
    let msg = '';
    for (let err in this.errors) {
      switch (err) {
        case 'required':
          msg += 'Es obligatorio. ';
          break;
        case 'minlength':
          msg += `Como mínimo debe tener ${this.errors[err].requiredLength} caracteres. `;
          break;
        case 'maxlength':
          msg += `Como máximo debe tener ${this.errors[err].requiredLength} caracteres. `;
          break;
        case 'pattern':
        case 'email':
          msg += 'El formato no es correcto. ';
          break;
        case 'min':
          msg += `El valor debe ser mayor o igual a ${this.errors[err].min}. `;
          break;
        case 'max':
          msg += `El valor debe ser inferior o igual a ${this.errors[err].max}. `;
          break;
        default:
          if (typeof this.errors[err] === 'string')
            msg += `${this.errors[err]}${this.errors[err].endsWith('.')?'':'.'} `;
          else if (typeof this.errors[err]?.message === 'string')
            msg += `${this.errors[err].message}${this.errors[err].message.endsWith('.')?'':'.'} `;
          break;
      }
    }
    this.mensaje = msg.trim();
    this.hidden = this.mensaje === '';
  }

}
