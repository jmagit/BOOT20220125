import { Component, DebugElement, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { ShowErrorsDirective, WindowConfirmDirective } from './atributos.directive';

@Component({
  template: `<button type="button" (myWinConfirm)="MyOutput($event)" [myWinConfirmMessage]="MyInput"  ></button>`
})
class WindowConfirmDirectiveHostComponent {
  MyInput: any = null;
  MyOutput: any = () => {};
}

describe('WindowConfirmDirective', () =>  {
  let component: WindowConfirmDirectiveHostComponent;
  let fixture: ComponentFixture<WindowConfirmDirectiveHostComponent>;
  let tag: DebugElement;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WindowConfirmDirectiveHostComponent, WindowConfirmDirective ],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WindowConfirmDirectiveHostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    tag = fixture.debugElement.query(By.css('button'));
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    expect(tag).toBeDefined();
  });

  it('click confirm', () => {
    let ok = false;
    let myWinConfirmMessage = 'Texto de confirmación'
    component.MyOutput = () => ok = true;
    component.MyInput = myWinConfirmMessage
    spyOn(window, 'confirm').and.returnValue(true)
    fixture.detectChanges();
    tag.triggerEventHandler('click', null);
    expect(window.confirm).toHaveBeenCalled()
    expect(window.confirm).toHaveBeenCalledWith(myWinConfirmMessage)
    expect(ok).toBeTruthy()
  });

  it('click cancel', () => {
    let ok = false;
    let myWinConfirmMessage = 'Texto de confirmación'
    component.MyOutput = () => ok = true;
    component.MyInput = myWinConfirmMessage
    spyOn(window, 'confirm').and.returnValue(false)
    fixture.detectChanges();
    tag.triggerEventHandler('click', null);
    expect(window.confirm).toHaveBeenCalled()
    expect(window.confirm).toHaveBeenCalledWith(myWinConfirmMessage)
    expect(ok).toBeFalse()
  });

  it('class pressed', () => {
    expect(tag.classes['pressed']).toBeFalsy()
    tag.triggerEventHandler('mousedown', null);
    fixture.detectChanges();
    expect(tag.classes['pressed']).toBeTruthy()
    tag.triggerEventHandler('mouseup', null);
    fixture.detectChanges();
    expect(tag.classes['pressed']).toBeFalsy()
  });

})

@Component({
  template: `<output class="invalid-feedback" [myShowErrors]="errors"></output>`
})
class ShowErrorsDirectiveHostComponent {
  errors: any = null;
}
describe('myShowErrors', () =>  {
  let component: ShowErrorsDirectiveHostComponent;
  let fixture: ComponentFixture<ShowErrorsDirectiveHostComponent>;
  let tag: DebugElement;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowErrorsDirectiveHostComponent, ShowErrorsDirective ],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowErrorsDirectiveHostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    tag = fixture.debugElement.query(By.css('output'));
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    expect(tag).toBeDefined();
  });

  it('no errors', () => {
    component.errors = null;
    fixture.detectChanges();
    expect(tag.nativeElement.textContent).toEqual('')
    expect(tag.nativeElement.hidden).toBeTrue()

  });

  it('required', () => {
    component.errors = { required: true };
    fixture.detectChanges();
    expect(tag.nativeElement.textContent).toEqual('Es obligatorio.')
    expect(tag.nativeElement.hidden).toBeFalse()
  });

  it('pattern + min', () => {
    component.errors = { pattern: true, min: { min: 10 } };
    fixture.detectChanges();
    expect(tag.nativeElement.textContent).toEqual('El formato no es correcto. El valor debe ser mayor o igual a 10.')
    expect(tag.nativeElement.hidden).toBeFalse()
  });
})
