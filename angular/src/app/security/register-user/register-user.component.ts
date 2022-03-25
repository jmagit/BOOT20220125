import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { User, RegisterUserDAO, LoginService } from '../security.service';
import { Router } from '@angular/router';
import { NotificationService, NotificationType } from 'src/app/common-services';
import { LoggerService } from 'src/lib/my-core';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {
  public miForm: FormGroup = new FormGroup({});
  private model: User = new User();

  constructor(private dao: RegisterUserDAO, private notify: NotificationService,
    private out: LoggerService, private router: Router, private login: LoginService) { }

  passwordMatchValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => control?.get('passwordValue')?.value === control?.get('passwordConfirm')?.value
      ? null : { 'mismatch': 'Son distintos' };
  }

  ngOnInit() {
    // const fa = new FormArray([]);
    // this.model.roles.forEach(r => fa.push(
    //   new FormGroup({ role: new FormControl(r.role , Validators.required) })
    // ));
    this.miForm = new FormGroup({
      idUsuario: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(20), Validators.email]),
      nombre: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(20)]),
      password: new FormGroup({
        passwordValue: new FormControl('', [Validators.required, Validators.minLength(2)]),
        passwordConfirm: new FormControl('', Validators.minLength(2)),
      }, this.passwordMatchValidator()),
      roles: new FormArray([])
    });
    // for (const name in this.miForm.controls) {
    //   if (this.miForm.controls[name] instanceof FormControl) {
    //     this.miForm.controls[name].valueChanges.subscribe(
    //       data => { this.formatErrorMessage(this.miForm.controls[name] as FormControl); }
    //     );
    //     // this.formatErrorMessage(this.miForm.controls[name] as FormControl);
    //     this.miForm.controls[name].setValue(this.miForm.controls[name].value)
    //   }
    // }
  }
  public getErrorMessage(name: string): string {
    let cntr = this.miForm.get(name)
    let msg = '';
    if (cntr)
      for (let err in cntr.errors) {
        switch (err) {
          case 'required':
            msg += 'Es obligatorio. ';
            break;
          case 'minlength':
            msg += `Como mínimo debe tener ${cntr.errors[err].requiredLength} caracteres. `;
            break;
          case 'maxlength':
            msg += `Como máximo debe tener ${cntr.errors[err].requiredLength} caracteres. `;
            break;
          case 'pattern':
          case 'email':
            msg += 'El formato no es correcto. ';
            break;
          case 'min':
            msg += `El valor debe ser mayor o igual a ${cntr.errors[err].min}. `;
            break;
          case 'max':
            msg += `El valor debe ser inferior o igual a ${cntr.errors[err].max}. `;
            break;
          default:
            if (typeof cntr.errors[err] === 'string')
              msg += `${cntr.errors[err]}${cntr.errors[err].endsWith('.') ? '' : '.'} `;
            else if (typeof cntr.errors[err]?.message === 'string')
              msg += `${cntr.errors[err].message}${cntr.errors[err].message.endsWith('.') ? '' : '.'} `;
            break;
        }
      }
    return msg.trim();
  }
  private formatErrorMessage(cntr: FormControl): void {
    let msg = '';
    for (let err in cntr.errors) {
      switch (err) {
        case 'required':
          msg += 'Es obligatorio. ';
          break;
        case 'minlength':
          msg += `Como mínimo debe tener ${cntr.errors[err].requiredLength} caracteres. `;
          break;
        case 'maxlength':
          msg += `Como máximo debe tener ${cntr.errors[err].requiredLength} caracteres. `;
          break;
        case 'pattern':
        case 'email':
          msg += 'El formato no es correcto. ';
          break;
        case 'min':
          msg += `El valor debe ser mayor o igual a ${cntr.errors[err].min}. `;
          break;
        case 'max':
          msg += `El valor debe ser inferior o igual a ${cntr.errors[err].max}. `;
          break;
        default:
          if (typeof cntr.errors[err] === 'string')
            msg += `${cntr.errors[err]}${cntr.errors[err].endsWith('.') ? '' : '.'} `;
          else if (typeof cntr.errors[err]?.message === 'string')
            msg += `${cntr.errors[err].message}${cntr.errors[err].message.endsWith('.') ? '' : '.'} `;
          break;
      }
    }
    cntr.setErrors(Object.assign({}, cntr.errors, { 'customMsg': msg }));
  }
  addRole(): void {
    (this.miForm.get('roles') as FormArray).push(
      new FormGroup({ role: new FormControl('Usuarios', Validators.required) })
    );
  }
  deleteRole(ind: number): void {
    (this.miForm.get('roles') as FormArray).removeAt(ind);
  }
  send(): void {
    const data = this.miForm.value;
    this.model = ({
      idUsuario: data.idUsuario,
      password: data.password.passwordValue,
      nombre: data.nombre,
      roles: data.roles
    } as User);
    this.dao.add(this.model).subscribe(
      rslt => {
        this.login.login(data.idUsuario, data.password.passwordValue).subscribe(
          datos => {
            if (datos) {
              this.notify.add('Usuario registrado', NotificationType.log);
              this.router.navigateByUrl('/');
            } else {
              this.notify.add('Error en el registro.');
            }
          },
          err => { this.notify.add(err.message); }
        );
      },
      err => { this.notify.add(err.message); }
    );
  }
}
