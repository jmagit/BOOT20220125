import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'toComaDecimal'
})
export class ToComaDecimalPipe implements PipeTransform {
  transform(value: any, args?: any): any {
    if (typeof (value) === 'number') {
      value = value.toString();
    }
    if (typeof (value) === 'string') {
      return value.replace(/\./g, ',');
    }
    return value;
  }
}

export const PIPES_NUMERICOS = [ ToComaDecimalPipe, ]
