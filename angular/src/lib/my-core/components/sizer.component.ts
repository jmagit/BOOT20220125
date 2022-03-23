import { Component, EventEmitter, Input, Output } from '@angular/core';
@Component({
  selector: 'my-sizer',
  template: `
  <div>
    <button (click)="dec()">-</button>
    <button (click)="inc()">+</button>
    <label [style.font-size.px]="size">FontSize: {{size}}px</label>
  </div>
  `
})
export class SizerComponent {
  @Input() size: number | string = 12;
  @Output() sizeChange = new EventEmitter<number>();
  dec(): void { this.resize(-1); }
  inc(): void { this.resize(+1); }
  resize(delta: number): void {
    this.size = Math.min(40, Math.max(8, +this.size + delta));
    this.sizeChange.emit(this.size);
  }
}
