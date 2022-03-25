import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { HttpClient, HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpContextToken } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

export const AUTH_REQUIRED = new HttpContextToken<boolean>(() => false);

@Injectable({providedIn: 'root'})
export class AuthService {
  private isAuth = false;
  private authToken: string = '';
  private name = '';

  constructor() {
    if (localStorage && localStorage['AuthService']) {
      const rslt = JSON.parse(localStorage['AuthService']);
      this.isAuth = rslt.isAuth;
      this.authToken = rslt.authToken;
      this.name = rslt.name;
    }
  }

  get AuthorizationHeader() { return this.authToken;  }
  get isAutenticated() { return this.isAuth; }
  get Name() { return this.name; }

  login(authToken: string, name: string) {
    this.isAuth = true;
    this.authToken = authToken;
    this.name = name;
    if (localStorage) {
      localStorage['AuthService'] = JSON.stringify({ isAuth: this.isAuth, authToken, name });
    }
  }
  logout() {
    this.isAuth = false;
    this.authToken = '';
    this.name = '';
    if (localStorage) {
      localStorage.removeItem('AuthService');
    }
  }
}

class LoginResponse {
  success = false;
  token: string = '';
  name: string = '';
}

@Injectable({providedIn: 'root'})
export class LoginService {
  constructor(private http: HttpClient, private auth: AuthService) { }
  get isAutenticated() { return this.auth.isAutenticated;  }
  get Name() { return this.auth.Name;  }

  login(usr: string, pwd: string) {
    return new Observable(observable =>
      this.http.post<LoginResponse>(environment.securityApiURL + 'login', { name: usr, password: pwd })
        .subscribe({
          next: data => {
            if (data.success === true) {
              this.auth.login(data.token ?? '', data.name ?? '');
            }
            observable.next(this.auth.isAutenticated);
          },
          error: err => observable.error(err)
       })
    );
  }
  logout() {
    this.auth.logout();
  }
}

// { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true, },
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!(req.context.get(AUTH_REQUIRED) || req.withCredentials) || !this.auth.isAutenticated) {
      return next.handle(req);
    }
    const authReq = req.clone(
      { headers: req.headers.set('Authorization', this.auth.AuthorizationHeader) }
    );
    return next.handle(authReq);
  }
}

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.authService.isAutenticated;
  }
}

export class Role {
  role: string = '';
}
export class User {
  idUsuario: string = '';
  password: string = '';
  nombre: string = '';
  roles: Array<Role> = [];

}

@Injectable({providedIn: 'root'})
export class RegisterUserDAO  {
  private baseUrl = environment.securityApiURL + 'register ';
  private options = { withCredentials: true };

  constructor(private http: HttpClient) { }

  add(item: User)  {
    return this.http.post(this.baseUrl, item);
  }

  get() {
    return this.http.get<User>(this.baseUrl, this.options);
  }
  change(item: User) {
    return this.http.put(this.baseUrl, item, this.options);
  }
}
