import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/common-services';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent {

  constructor(private vm: NotificationService) { }

  public get VM() { return this.vm; }

}
