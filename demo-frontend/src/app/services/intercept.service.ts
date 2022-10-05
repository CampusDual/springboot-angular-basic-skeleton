import { Injectable } from '@angular/core';
import { MatSnackBarComponent } from '../components/mat-snack-bar/mat-snack-bar.component';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpResponse,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, finalize } from 'rxjs/operators';
import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';
import { stringToKeyValue } from '@angular/flex-layout/extended/style/style-transforms';


@Injectable()
export class InterceptService implements HttpInterceptor {

  constructor(private snackBar: MatSnackBarComponent, private translate: TranslateService, /*private busyService: BusyService,*/
              private router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          // shows yellow text if responseCode is a warning
          if (event.body.responseCode === 'WARNING') {
            if (!event.url.includes('isSecurizationTemplateInUse')) {
              var message : string;
              if(event.body.errors!=null){
                for(let error of event.body.errors){
                  if(message==null){
                    message = this.translate.instant(error);
                  }else{
                    message= message+', '+this.translate.instant(error);
                  } 
                }
              }else{
                message = this.translate.instant(event.body.responseMessage);
              }
              this.snackBar.openSnackBar(message,
              this.translate.instant('CLOSE'), 'yellow-snackbar');
            }
          } else if (event.body.responseCode === 'KO') {
            this.snackBar.openSnackBar(this.translate.instant(event.body.responseMessage), this.translate.instant('CLOSE'), 'red-snackbar');
          } else if (event.url.includes('edit') || event.url.includes('create') || event.url.includes('delete')) {
            // shows success snackbar with blue text
            this.snackBar.openSnackBar(this.translate.instant(event.body.responseMessage),
            this.translate.instant('CLOSE'), 'blue-snackbar');
          }
        }
      }, err => {
        // http response status code
        if (err instanceof HttpErrorResponse) {
          if (err.status === 403) {
            this.router.navigateByUrl('/main');
            this.snackBar.openSnackBar(this.translate.instant('UNAUTHORIZED_USER'), this.translate.instant('CLOSE'), 'red-snackbar');
          } else if (err.status === 401) {
            localStorage.removeItem('access_token');
            this.router.navigateByUrl('/login');
            this.snackBar.openSnackBar(this.translate.instant('TOKEN_EXPIRED'), this.translate.instant('CLOSE'), 'red-snackbar');
          }if (err.status === 500) {
            this.snackBar.openSnackBar(this.translate.instant('DATABASE_QUERY_ERROR'), this.translate.instant('CLOSE'), 'red-snackbar');
          } else {
            if (err.url.includes('token')) {
              this.snackBar.openSnackBar(this.translate.instant(err.error.error_description),
                this.translate.instant('CLOSE'), 'red-snackbar');
            } else {
              this.snackBar.openSnackBar(err.message, this.translate.instant('CLOSE'), 'red-snackbar');
            }
          }
        }
      }), finalize(() => {
      })
    );

  }

}
