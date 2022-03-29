import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { fakeAsync, inject, TestBed, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Observable, of, throwError } from 'rxjs';
import { LoggerService } from 'src/lib/my-core';
import { RESTDAOService } from '../base-code/RESTDAOService';
import { NavigationService, NotificationService } from '../common-services';

import { BlogDAOService, BlogViewModelService } from './servicios.service';

export class DAOServiceMock<T, K> extends RESTDAOService<T, number> {
  constructor(http: HttpClient, public listado: Array<T>) {
    super(http, '')
  }
  override query(): Observable<Array<T>> {
    return of(this.listado);
  }
  override get(id: number): Observable<T> {
    if(id > this.listado.length) return throwError("404 not found")
    return of(this.listado[id - 1]);
  }
  override add(item: T): Observable<T> {
    this.listado.push(item)
    return of(item);
  }
  override change(id: number, item: T): Observable<T> {
    if(id > this.listado.length) return throwError("404 not found")
    this.listado[id - 1] = item;
    return of(item);
  }
  override remove(id: number): Observable<T> {
    if(id > this.listado.length) return throwError("404 not found")
    let item = this.listado[id - 1];
    this.listado.splice(id - 1, 1)
    return of(item);
  }
}

describe('BlogDAOService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [ BlogDAOService ],
    });
  });

  it('query', inject([BlogDAOService, HttpTestingController], (dao: BlogDAOService, httpMock: HttpTestingController) => {
    dao.query().subscribe(
      data => {
        expect(data.length).toEqual(4);
      },
      data => { fail(); }
    );
    // const req = httpMock.expectOne('http://localhost:4321/blog');
    const req = httpMock.expectOne('/api/blog');
    expect(req.request.method).toEqual('GET');
    req.flush([
      {"id":1,"tratamiento":"Sra.","nombre":"Marline","apellidos":"Lockton Jerrans","telefono":"846 054 444","email":"mjerrans0@de.vu","sexo":"M","nacimiento":"1973-07-09","avatar":"https://randomuser.me/api/portraits/women/1.jpg","conflictivo":true},
      {"id":2,"tratamiento":"Sr.","nombre":"Beale","apellidos":"Knibb Koppe","telefono":"093 804 977","email":"bkoppe0@apache.org","sexo":"H","nacimiento":"1995-11-22","avatar":"https://randomuser.me/api/portraits/men/1.jpg","conflictivo":false},
      {"id":3,"tratamiento":"Srta.","nombre":"Gwenora","apellidos":"Forrestor Fitzackerley","telefono":"853 134 343","email":"gfitzackerley1@opensource.org","sexo":"M","nacimiento":"1968-06-12","avatar":"https://randomuser.me/api/portraits/women/2.jpg","conflictivo":false},
      {"id":4,"tratamiento":"Sr.","nombre":"Umberto","apellidos":"Langforth Spenclay","telefono":"855 032 596","email":"uspenclay1@mlb.com","sexo":"H","nacimiento":"2000-05-15","avatar":"https://randomuser.me/api/portraits/men/2.jpg","conflictivo":false}
    ]);
    httpMock.verify();
  }));

  it('change', inject([BlogDAOService, HttpTestingController], (dao: BlogDAOService, httpMock: HttpTestingController) => {
    let item = {id:1, nombre:"Pepito",apellido:"Grillo"};
    dao.change(1, item).subscribe(() => { });
    // const req = httpMock.expectOne('http://localhost:4321/blog');
    const req = httpMock.expectOne('/api/blog/1');
    expect(req.request.method).toEqual('PUT');
    expect(req.request.body.id).toEqual(1);
    expect(req.request.body.nombre).toEqual('Pepito');
    httpMock.verify();
  }));

});

describe('BlogViewModelService', () => {
  let service: BlogViewModelService;
  let dao: BlogDAOService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [NotificationService, LoggerService,
        {
          provide: BlogDAOService, useFactory: (http: HttpClient) => new DAOServiceMock<any, number>(http, [
            {"id":1,"tratamiento":"Sra.","nombre":"Marline","apellidos":"Lockton Jerrans","telefono":"846 054 444","email":"mjerrans0@de.vu","sexo":"M","nacimiento":"1973-07-09","avatar":"https://randomuser.me/api/portraits/women/1.jpg","conflictivo":true},
            {"id":2,"tratamiento":"Sr.","nombre":"Beale","apellidos":"Knibb Koppe","telefono":"093 804 977","email":"bkoppe0@apache.org","sexo":"H","nacimiento":"1995-11-22","avatar":"https://randomuser.me/api/portraits/men/1.jpg","conflictivo":false},
            {"id":3,"tratamiento":"Srta.","nombre":"Gwenora","apellidos":"Forrestor Fitzackerley","telefono":"853 134 343","email":"gfitzackerley1@opensource.org","sexo":"M","nacimiento":"1968-06-12","avatar":"https://randomuser.me/api/portraits/women/2.jpg","conflictivo":false},
            {"id":4,"tratamiento":"Sr.","nombre":"Umberto","apellidos":"Langforth Spenclay","telefono":"855 032 596","email":"uspenclay1@mlb.com","sexo":"H","nacimiento":"2000-05-15","avatar":"https://randomuser.me/api/portraits/men/2.jpg","conflictivo":false}
          ]), deps: [HttpClient]
        }
      ],
    });
    service = TestBed.inject(BlogViewModelService);
    dao = TestBed.inject(BlogDAOService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('DAOServiceMock Query', (done: DoneFn) => {
    dao.query().subscribe(
      data => {
        expect(data.length).toBe(4);
        done();
      },
      () => fail()
    );
  });

  it('list', fakeAsync(() => {
    service.list()
    tick()
    expect(service.Listado.length).toBe(4)
    expect(service.Modo).toBe('list')
  }))

  it('edit OK', fakeAsync(() => {
    service.edit(3)
    tick()
    expect(service.Elemento.id).toBe(3)
    expect(service.Modo).toBe('edit')
  }))

  it('edit KO', fakeAsync(() => {
    let notify = TestBed.inject(NotificationService);
    spyOn(notify, 'add')

    service.edit(7)
    tick()

    expect(notify.add).toHaveBeenCalled()
  }))

  it('view', fakeAsync(() => {
    service.view(2)
    tick()
    expect(service.Elemento.id).toBe(2)
    expect(service.Modo).toBe('view')
  }))

  it('delete: accept confirm', fakeAsync(() => {
    spyOn(window, 'confirm').and.returnValue(true)
    service.delete(4)
    tick()
    dao.query().subscribe(data => expect(data.length).toBe(3))
    tick()
    expect(service.Listado.length).toBe(3)
    expect(service.Modo).toBe('list')
  }))

  it('delete: reject confirm', fakeAsync(() => {
    spyOn(window, 'confirm').and.returnValue(false)
    service.delete(4)
    dao.query().subscribe(data => expect(data.length).toBe(3))
    tick()
  }))

  it('add', () => {
    service.add()
    expect(service.Elemento).toEqual({})
    expect(service.Modo).toBe('add')
  })

  it('cancel', fakeAsync(() => {
    let navigation = TestBed.inject(NavigationService);
    spyOn(navigation, 'back')
    service.edit(2)
    tick()
    expect(service.Elemento).not.toEqual({})
    service.cancel()
    expect(service.Elemento).toEqual({})
    expect(navigation.back).toHaveBeenCalled()
  }))

  it('send: add', fakeAsync(() => {
    const NOMBRE = 'XXXXXX'
    spyOn(service, 'cancel')
    service.add()
    tick()
    service.Elemento.id = 5
    service.Elemento.nombre = NOMBRE
    service.send()
    tick()
    dao.query().subscribe(data => expect(data.length).toBe(5))
    dao.get(5).subscribe(data => expect(data.nombre).toBe(NOMBRE))
    tick()
    expect(service.cancel).toHaveBeenCalled()
  }))

  it('send: edit', fakeAsync(() => {
    const NOMBRE = 'XXXXXX'
    spyOn(service, 'cancel')
    service.edit(2)
    tick()
    service.Elemento.nombre = NOMBRE
    service.send()
    tick()
    dao.get(2).subscribe(data => expect(data.nombre).toBe(NOMBRE))
    tick()
    expect(service.cancel).toHaveBeenCalled()
  }))

});
