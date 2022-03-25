import { Directive, ElementRef } from '@angular/core';
import { ValidatorFn, AbstractControl, NG_VALIDATORS, Validator, ValidationErrors } from '@angular/forms';

export function NIFValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
      if (!control.value) { return null; }
      const err = { nif: { invalidFormat: true, invalidChar: true, message: 'NIF invalido' } };
      if (/^\d{1,8}\w$/.test(control.value)) {
          const letterValue = control.value.substr(control.value.length - 1);
          const numberValue = control.value.substr(0, control.value.length - 1);
          err.nif.invalidFormat = false;
          return letterValue.toUpperCase() === 'TRWAGMYFPDXBNJZSQVHLCKE'.charAt(numberValue % 23) ? null : err;
      } else { return err; }
  };
}
@Directive({
  selector: '[nif][formControlName],[nif][formControl],[nif][ngModel]',
  providers: [{ provide: NG_VALIDATORS, useExisting: NIFValidatorDirective, multi: true }]
})
export class NIFValidatorDirective implements Validator {
  validate(control: AbstractControl): ValidationErrors | null {
      return NIFValidator()(control);
  }
}

export function UppercaseValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
      if (!control.value) { return null; }
      return control.value === control.value.toUpperCase() ? null : { uppercase: 'Debe estar en mayúsculas' }
  };
}
@Directive({
  selector: '[uppercase][formControlName],[uppercase][formControl],[uppercase][ngModel]',
  providers: [{ provide: NG_VALIDATORS, useExisting: UppercaseValidatorDirective, multi: true }]
})
export class UppercaseValidatorDirective implements Validator {
  validate(control: AbstractControl): ValidationErrors | null {
      return UppercaseValidator()(control);
  }
}

@Directive({
  selector: '[type][formControlName],[type][formControl],[type][ngModel]',
  providers: [
      { provide: NG_VALIDATORS, useExisting: TypeValidatorDirective, multi: true }
  ]
})
export class TypeValidatorDirective implements Validator {
  constructor(private elem: ElementRef) { }
  validate(control: AbstractControl): ValidationErrors | null {
      const valor = control.value;
      if (valor) {
        const dom = this.elem.nativeElement;
        if (dom.validity) { // dom.checkValidity();
          return dom.validity.typeMismatch ? { 'type': dom.validationMessage } : null;
        }
      }
      return null;
  }
}

export const MIS_VALIDADORES = [ NIFValidatorDirective, UppercaseValidatorDirective, TypeValidatorDirective, ]
