import { TestBed } from '@angular/core/testing';

import { FormularioVMService } from './formulario-vm.service';

describe('FormularioVMService', () => {
  let service: FormularioVMService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormularioVMService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
