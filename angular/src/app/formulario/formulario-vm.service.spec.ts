import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { LoggerService } from 'src/lib/my-core';
import { NavigationService, NotificationService } from '../common-services';

import { FormularioVMService } from './formulario-vm.service';

describe('FormularioVMService', () => {
  let service: FormularioVMService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ NavigationService, NotificationService, LoggerService ],
      imports: [ HttpClientTestingModule]
    });
    service = TestBed.inject(FormularioVMService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
