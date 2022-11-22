import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { API_CONFIG } from '../shared/api.config';
import { JwtHelperService } from '@auth0/angular-jwt';
import { TranslateService } from '@ngx-translate/core';
import { Buffer } from 'buffer';

export const TOKEN_NAME: string = 'jwt_token';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private router: Router,
    private jwtHelper: JwtHelperService,
    private translateService: TranslateService
  ) {}

  redirectUrl: string;

  public login(user: string, password: string): Observable<any> {
    let _innerObserver: any;
    const dataObservable = new Observable(
      (observer) => (_innerObserver = observer)
    );

    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      Authorization:
        'Basic ' +
        Buffer.from(`${environment.clientName}:${environment.clientSecret}`, 'utf8').toString('base64'),
    });

    const urlGrantTypeParams = new URLSearchParams();
    urlGrantTypeParams.append('grant_type', 'password');
    urlGrantTypeParams.append('username', user);
    urlGrantTypeParams.append('password', password);
    const body = urlGrantTypeParams.toString();

    const url = API_CONFIG.login;
    this.http.post(url, body, { headers }).subscribe(
      (response: any) => {
        this.setSession(response);
        _innerObserver.next(response.access_token);
      },
      (err) => {
        _innerObserver.error(err);
      }
    );

    return dataObservable;
  }

  private setSession(authResult) {
    localStorage.setItem('access_token', authResult.access_token);
  }

  logout() {
    localStorage.removeItem('access_token');
  }

  public isLoggedIn() {
    return localStorage.getItem('access_token') !== null;
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  getToken() {
    return localStorage.getItem('access_token');
  }

  getUserName() {
    return this.jwtHelper.decodeToken(this.getToken()).user_name;
  }

  getRoles() {
    return this.jwtHelper.decodeToken(this.getToken()).authorities;
  }

  redirectLoginSessionExpiration() {
    this.logout();
    this.router.navigateByUrl('/login');
    localStorage.setItem('close_session', '1');
    localStorage.setItem('expired_session_message', 'true');
    localStorage.setItem(
      'close_session_language',
      this.translateService.currentLang
    );
    setTimeout(() => {
      window.location.reload();
    }, 100);
  }

  /*
   * Según el o los roles(secciones) que estén asociadas a un perfil, se redirecciona a una url diferente cuando se loguee.
   * En este caso, si el perfil tiene acceso a la sección CONTACTS, se redirige a main(hace lo mismo que si no existiese este método en este caso),
   * pero en el caso de que queramos que rediriga a otra url diferente, en this.redirectUrl se le da el valor que queramos
   */
  redirectMain(){
    if(this.getRoles().includes("CONTACTS")){
      this.redirectUrl="/main"
    }
  }
}
