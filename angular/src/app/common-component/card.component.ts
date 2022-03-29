import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-card',
  template: `
    <div class="card">
        <H1>{{ header }}</H1>
        <ng-content></ng-content>
    </div>
  `
})

export class CardComponent implements OnInit {
  @Input() header: string = 'this is header';

  constructor() { }

  ngOnInit() { }
}
