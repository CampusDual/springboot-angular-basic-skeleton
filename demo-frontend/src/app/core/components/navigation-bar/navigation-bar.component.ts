import { Component, EventEmitter, Output } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { Router, NavigationEnd } from '@angular/router';
import { LoggerService } from 'src/app/services/logger.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.scss']
})
export class NavigationBarComponent {

  @Output() toggleSidenav = new EventEmitter<void>();

  private returnUrl = '/';
  selectedLanguage = this.translateService.currentLang;
  userName: string;

  constructor(private authService: AuthService, private router: Router, private logger: LoggerService,
    private translateService: TranslateService) {
    this.userName = authService.getUserName();
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.returnUrl = event.url;
        this.logger.info('NavigationBarComponent returnUrl: ' + this.returnUrl);
      }
    });
  }

  logout() {
    this.authService.logout();
    // Redirect the user
    this.router.navigateByUrl('/login');
    localStorage.setItem('close_session', '1');
    localStorage.setItem('close_session_language', this.translateService.currentLang);
    setTimeout(() => {
      window.location.reload();
    }, 100);
  }

  toogleLanguage(lang: string) {
    this.selectedLanguage = lang;
    this.translateService.use(lang);
  }
}
