import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AuthModule } from './auth/auth.module';
import { MatSnackBarComponent } from './components/mat-snack-bar/mat-snack-bar.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NgxSpinnerModule } from 'ngx-spinner';
import { NgIdleKeepaliveModule } from '@ng-idle/keepalive';
import { AuthService } from './auth/auth.service';
import { MainModule } from './main/main.module';
import { FormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { ConfirmationDialogComponent } from './shared/confirmation-dialog/confirmation-dialog.component';
import { InterceptService } from './services/intercept.service';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { CustomMatPaginatorIntl } from './model/custom-mat-paginator';
import { CoreModule } from './core/core.module';

@NgModule({
  declarations: [
    AppComponent,
    ConfirmationDialogComponent,
    MatSnackBarComponent
  ],
  imports: [
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FlexLayoutModule,
    MainModule,
    AuthModule,
    MatDialogModule,
    MatButtonModule,
    CoreModule,
    MatSnackBarModule,
    NgxSpinnerModule,
    NgIdleKeepaliveModule.forRoot()
  ],
  entryComponents: [
    ConfirmationDialogComponent
  ],
  exports: [
    TranslateModule
  ],
  providers: [AuthService, MatSnackBarComponent, InterceptService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptService,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    {
      provide: MatPaginatorIntl,
      useFactory: (translate) => {
        const service = new CustomMatPaginatorIntl();
        service.injectTranslateService(translate);
        return service;
      },
      deps: [TranslateService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

// required for AOT compilation
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}
