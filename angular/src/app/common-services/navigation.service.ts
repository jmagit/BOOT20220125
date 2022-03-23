import { Router, NavigationEnd, ActivationStart } from '@angular/router';
import { LoggerService } from 'src/lib/my-core';

import { Injectable } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Injectable({ providedIn: 'root' })
export class NavigationService {
  private readonly MAX_CACHE = 5
  private history: Array<string> = [];
  constructor(private router: Router, private title: Title, private logger: LoggerService) {
    router.events.subscribe(e => {
      if (e instanceof ActivationStart) {
        let ev: ActivationStart = e as ActivationStart;
        if (ev.snapshot?.data?.['pageTitle']) {
          this.title.setTitle(ev.snapshot.data['pageTitle']);
        } else {
          this.title.setTitle('Curso de Angular');
        }
      }
      if (e instanceof NavigationEnd) {
        let ev: NavigationEnd = e as NavigationEnd;
        this.history.push(ev.url);
        if(this.history.length > this.MAX_CACHE) this.history.splice(0, 1)
        logger.log(`Navigate to ${e.url}`);
      }
    });
  }
  back(defecto: string = '/', delta: number = 1) {
    while (delta && this.history.length > 0) {
      this.history.pop();
      delta--;
    }
    const url = this.history.pop() ?? defecto;
    this.router.navigateByUrl(url);
    this.logger.log(`Back to ${url}`);
  }
}
