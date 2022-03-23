import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/common-services';

@Component({
  selector: 'app-notification-modal',
  templateUrl: './notification-modal.component.html',
  styleUrls: ['./notification-modal.component.css']
})
export class NotificationModalComponent {

  constructor(private vm: NotificationService) { }

  public get VM() { return this.vm; }

}
