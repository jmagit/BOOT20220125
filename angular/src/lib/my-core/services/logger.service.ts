import { Inject, Injectable, InjectionToken, Optional } from '@angular/core';

export const ERROR_LEVEL = new InjectionToken<string>('ERROR_LEVEL')

@Injectable()
export class LoggerService {
  private level: number = 99;

  constructor(@Optional() @Inject(ERROR_LEVEL) error_level?: number) {
    if(error_level || error_level === 0) {
      this.level = error_level;
    }
  }

  public error(message: string): void {
    if (this.level > 0) {
      console.error(message)
    }
  }

  public warn(message: string): void {
    if (this.level > 1) {
      console.warn(message)
    }
  }

  public info(message: string): void {
    if (this.level > 2) {
      if (console.info) {
        console.info(message)
      } else {
        console.log(message)
      }
    }
  }

  public log(message: string): void {
    if (this.level > 3) {
      console.log(message)
    }
  }
}
