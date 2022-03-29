import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/security';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(public auth: AuthService) { }
}
