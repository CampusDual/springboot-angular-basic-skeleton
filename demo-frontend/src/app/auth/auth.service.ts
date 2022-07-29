import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { API_CONFIG } from '../shared/api.config';
// import * as moment from "moment";
import { RESTResponse } from '../model/rest/response';
import { JwtHelperService } from '@auth0/angular-jwt';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBarComponent } from '../components/mat-snack-bar/mat-snack-bar.component';

export const TOKEN_NAME: string = 'jwt_token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient, private router: Router, private jwtHelper: JwtHelperService,
    private snackBar: MatSnackBarComponent, private translateService: TranslateService) { }

  redirectUrl: string;

  public login(user: string, password: string): Observable<any> {

    let _innerObserver: any;
    const dataObservable = new Observable(
      observer => (_innerObserver = observer)
    );

    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
    });

    const urlGrantTypeParams = new URLSearchParams();
    urlGrantTypeParams.append('grant_type', 'password');
    urlGrantTypeParams.append('username', user);
    urlGrantTypeParams.append('password', password);
    const body = urlGrantTypeParams.toString();

    const url = API_CONFIG.login;
    this.canLogin(user).subscribe(responseCanLogin => {
      if (responseCanLogin.responseCode === 'OK') {
        this.http.post(url, body, { headers }).subscribe((response: any) => {
          this.setSession(response);
          _innerObserver.next(response.access_token);
        }, err => {
          _innerObserver.error(err);
        });
      }
    });

    return dataObservable;
  }

  canLogin(user: string): Observable<RESTResponse<boolean>> {
    const url = API_CONFIG.canLogin;
    const headers = new HttpHeaders({
      'Content-type': 'charset=utf-8'
    });
    const params = new HttpParams().set('user', user);
    return this.http.get<RESTResponse<boolean>>(url, { params, headers });
  }

  private setSession(authResult) {
    // const expiresIn = moment().add(authResult.expires_in, 'second');

    localStorage.setItem('access_token', authResult.access_token);
    // localStorage.setItem("expires_in", JSON.stringify(expiresIn.valueOf()));
  }

  logout() {
    localStorage.removeItem("access_token");
    // localStorage.removeItem("expires_in");
  }

  public isLoggedIn() {
    // return moment().isBefore(this.getExpiration());
    return localStorage.getItem("access_token")  !== null;
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  // getExpiration() {
  //   const expiration = localStorage.getItem("expires_in");
  //   const expiresAt = JSON.parse(expiration);
  //   return moment(expiresAt);
  // }

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
    localStorage.setItem('close_session_language', this.translateService.currentLang);
    setTimeout(() => {
      window.location.reload();
    }, 100);
  }
}
