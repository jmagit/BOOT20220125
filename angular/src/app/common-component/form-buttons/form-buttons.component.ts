import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-form-buttons',
  templateUrl: './form-buttons.component.html',
  styleUrls: ['./form-buttons.component.css']
})
export class FormButtonsComponent {
  @Input('send-disabled') sendDisabled: boolean | null = false;
  @Input('send-text') sendText: string = 'enviar';
  @Input('cancel-text') cancelText: string = 'volver';
  @Input('delete-text') deleteText: string = 'borrar';
  @Output() send: EventEmitter<any> = new EventEmitter<any>();
  @Output() cancel: EventEmitter<any> = new EventEmitter<any>();
  @Output() delete: EventEmitter<any> = new EventEmitter<any>();

  get hasSend(): boolean { return this.send.observed; }
  get hasCancel(): boolean { return this.cancel.observed; }
  get hasDelete(): boolean { return this.delete.observed; }
}

