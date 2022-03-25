import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { NotificationService } from 'src/app/common-services';
import { LoginService } from '../security.service';
import { AuthService } from '..';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { LoggerService } from 'src/lib/my-core';
import { FormsModule } from '@angular/forms';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      providers: [ LoginService, AuthService, NotificationService, LoggerService, ],
      imports: [ HttpClientTestingModule, RouterTestingModule, FormsModule ],
      schemas: [ NO_ERRORS_SCHEMA ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
