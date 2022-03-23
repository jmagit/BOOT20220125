import { TestBed } from '@angular/core/testing';
import { LoggerService } from 'src/lib/my-core';
import { NotificationType } from '.';

import { NotificationService } from './notification.service';

describe('NotificationService', () => {
  const message = 'Notificación al usuario'
  let service: NotificationService;
  let log: LoggerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ LoggerService ],
    });
    service = TestBed.inject(NotificationService);
    log = TestBed.inject(LoggerService);
    spyOn(log, 'error');
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  it('add message: error', (done: DoneFn) => {
    service.Notificacion.subscribe(
      data => { expect(data.Message).toBe(message); done(); },
      () => fail()
    );
    service.add(message)
    expect(service.HayNotificaciones).toBeTruthy();
    expect(service.Listado.length).toBe(1);
    expect(service.Listado[0].Id).toBe(1);
    expect(service.Listado[0].Message).toBe(message);
    expect(service.Listado[0].Type).toBe(NotificationType.error);
    expect(log.error).toHaveBeenCalled();
    expect(log.error).toHaveBeenCalledWith(`NOTIFICATION: ${message}`)
  });

  it('add message: warn', (done: DoneFn) => {
    service.Notificacion.subscribe(
      data => { expect(data.Message).toBe(message); done(); },
      () => fail()
    );
    service.add(message, NotificationType.warn)
    expect(service.HayNotificaciones).toBeTruthy();
    expect(service.Listado.length).toBe(1);
    expect(service.Listado[0].Id).toBe(1);
    expect(service.Listado[0].Message).toBe(message);
    expect(service.Listado[0].Type).toBe(NotificationType.warn);
    expect(log.error).not.toHaveBeenCalled();
  });

  it('remove message', () => {
    service.add(message)
    service.add(message)
    expect(service.HayNotificaciones).toBeTruthy();
    expect(service.Listado.length).toBe(2);
    service.remove(0)
    expect(service.Listado.length).toBe(1);
    expect(service.Listado[0].Id).toBe(2);
    service.remove(0)
    expect(service.HayNotificaciones).toBeFalsy();
  });

  it('clear messages', () => {
    service.add(message)
    service.add(message)
    expect(service.HayNotificaciones).toBeTruthy();
    expect(service.Listado.length).toBe(2);
    service.clear()
    expect(service.HayNotificaciones).toBeFalsy();
  });

});
