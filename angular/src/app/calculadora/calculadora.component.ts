import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { LoggerService } from 'src/lib/my-core';
import { NotificationService } from '../common-services';

@Component({
  selector: 'calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.css']
})
export class CalculadoraComponent implements OnInit {
  public readonly Math = Math;

  private acumulado = 0;
  private operador = '+';
  private limpiar = true;
  private miPantalla = '0'
  private miResumen = '';

  constructor(private log: LoggerService, private notify: NotificationService) {
    this.inicia();
  }

  get Pantalla(): string { return this.miPantalla; }
  // set pantalla(value: string) {
  //   if (!Number.isNaN(parseFloat(value)) || value === '-') {
  //     this.miPantalla = value;
  //   }
  // }
  get Resumen(): string { return this.miResumen; }

  @Input() init: string | number = '0';
  @Output() updated: EventEmitter<any> = new EventEmitter();

  private separadorDecimal: string = '.';
  get SeparadorDecimal() { return this.separadorDecimal; }
  @Input() set SeparadorDecimal(value: string) {
    if (value !== this.separadorDecimal && (value === '.' || value === ',')) {
      this.separadorDecimal = value;
    } else {
      this.log.error('Separador decimal no reconocido.');
    }
  }

  inicia(): void {
    this.acumulado = 0;
    this.operador = '+';
    this.miPantalla = '0';
    this.miResumen = '';
    this.limpiar = true;
  }

  ponDigito(value: number | string): void {
    if (typeof (value) !== 'string')
      value = value.toString();
    if (value.length != 1 || value < '0' || value > '9') {
      this.log.error('No es un valor numérico.');
      return;
    }
    if (this.limpiar || this.miPantalla == '0') {
      this.miPantalla = value;
      this.limpiar = false;
    } else
      this.miPantalla += value;
  }

  ponOperando(value: number | string): void {
    if (typeof value === "number" || (!Number.isNaN(parseFloat(value)) && parseFloat(value).toString() == value)) {
      this.miPantalla = value.toString();
      this.limpiar = false;
    } else {
      this.log.error('No es un valor numérico.');
    }
  };

  ponComa(): void {
    if (this.limpiar) {
      this.miPantalla = '0.';
      this.limpiar = false;
    } else if (this.miPantalla.indexOf('.') === -1) {
      this.miPantalla += '.';
    } else {
      // this.notify.add('Ya está la coma', NotificationType.warn)
      this.log.warn('Ya está la coma');
    }
  }

  borrar(): void {
    if (this.limpiar || this.miPantalla.length == 1 || (this.miPantalla.length == 2 && this.miPantalla.startsWith('-'))) {
      this.miPantalla = '0';
      this.limpiar = true;
    } else
      this.miPantalla = this.miPantalla.slice(-1);
  }

  cambiaSigno(): void {
    this.miPantalla = (-this.miPantalla).toString();
    if (this.limpiar) {
      this.acumulado = -this.acumulado;
    }
  }

  calcula(value: string): void {
    if ('+-*/='.indexOf(value) == -1) {
      this.log.error(`Operación no soportada: ${value}`);
      return;
    }

    let operando = parseFloat(this.miPantalla);
    switch (this.operador) {
      case '+':
        this.acumulado += operando;
        break;
      case '-':
        this.acumulado -= operando;
        break;
      case '*':
        this.acumulado *= operando;
        break;
      case '/':
        this.acumulado /= operando;
        break;
    }
    // Con eval()
    // acumulado = eval (acumulado + operador + miPantalla);
    this.miResumen = value == '=' ? '' : (`${this.acumulado} ${value}`);
    // Number: double-precision IEEE 754 floating point.
    // 9.9 + 1.3, 0.1 + 0.2, 1.0 - 0.9
    this.miPantalla = parseFloat(this.acumulado.toPrecision(15)).toString();
    // this.miPantalla = this.acumulado.toString();
    this.updated.emit(this.acumulado);
    this.operador = value;
    this.limpiar = true;
  };

  ngOnInit(): void {
    if (this.init) {
      this.ponOperando(this.init);
    }
  }
  ngOnChanges(changes: SimpleChanges): void {
    // if (this.init) {
    //   this.ponOperando(this.init.toString());
    // }
  }

}

