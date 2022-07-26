import { Component, OnInit, AfterViewInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBarComponent } from 'src/app/components/mat-snack-bar/mat-snack-bar.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, AfterViewInit {
  constructor(public authService: AuthService, public router: Router,
              private translateService: TranslateService, private snackBar: MatSnackBarComponent) {
  }

  ngOnInit() {
    this.redirectMain();
  }

  ngAfterViewInit() {
    if (localStorage.getItem('close_session') != null) {
      const closeSession = localStorage.getItem('close_session');
      if (closeSession === '0') {
        localStorage.removeItem('close_session');
        this.translateService.use(localStorage.getItem('close_session_language')).subscribe((langChange) => {
          localStorage.removeItem('close_session_language');
          if (localStorage.getItem('expired_session_message') != null) {
            this.translateService.get('SESSION_EXPIRED').subscribe((resultExpired) => {
              this.translateService.get('CLOSE').subscribe((resultClose) => {
                this.snackBar.openSnackBar(resultExpired, resultClose, 'yellow-snackbar');
                localStorage.removeItem('expired_session_message');
              });
            });
          }
        });
      } else {
        localStorage.setItem('close_session', '0');
      }
    }
  }

  login(username: string, password: string, event: Event) {
    event.preventDefault(); // Avoid default action for the submit button of the login form

    this.authService.login(username, password).subscribe(() => {
      this.redirectMain();
    });
  }

  redirectMain() {
    if (this.authService.isLoggedIn()) {
      // Get the redirect URL from our auth service
      // If no redirect has been set, use the default
      const redirect = this.authService.redirectUrl ? this.router.parseUrl(this.authService.redirectUrl) : '/main';

      // Redirect the user
      this.router.navigateByUrl(redirect);
    }
  }
}
