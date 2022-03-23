import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationComponent } from './notification/notification.component';
import { NotificationModalComponent } from './notification-modal/notification-modal.component';
import { AjaxWaitComponent } from './ajax-wait';
import { SecurityModule } from '../security';
import { RouterModule } from '@angular/router';
import { CommonServicesModule } from '../common-services';
import { HomeComponent } from './home/home.component';


@NgModule({
  declarations: [
    NotificationComponent, NotificationModalComponent, AjaxWaitComponent, HomeComponent,
  ],
  exports: [
    NotificationComponent, NotificationModalComponent, AjaxWaitComponent, HomeComponent,
  ],
  imports: [
    CommonModule, CommonServicesModule, SecurityModule, RouterModule.forChild([]),
  ]
})
export class MainModule {
  constructor( @Optional() @SkipSelf() parentModule: MainModule) {
    if (parentModule) {
      const msg = `MainModule has already been loaded.
        Import MainModule once, only, in the root AppModule.`;
      throw new Error(msg);
    }
  }
}
