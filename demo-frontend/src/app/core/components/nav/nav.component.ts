import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { LoggerService } from 'src/app/services/logger.service';
import { SidenavService } from 'src/app/services/sidenav.service';
import { AuthGuard } from 'src/app/auth/auth.guard';
import { Router } from '@angular/router';

interface ROUTE {
  icon?: string;
  route?: string;
  title?: string;
  allowedRoles?: Array<string>;
}

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit, OnDestroy {

  @ViewChild('commandbarSidenav') public sidenav: MatSidenav;

  sidenavRoutes: ROUTE[] = [
    {
      icon: 'home',
      route: 'main',
      title: 'menu.home',
      allowedRoles: ['CONTACTS']
    },
    {
      icon: 'people',
      route: 'contacts',
      title: 'menu.contacts',
      allowedRoles: ['CONTACTS']
    },
  ];

  protected subscription: Subscription;

  constructor(private commandBarSidenavService: SidenavService, private authService: AuthService, private logger: LoggerService,
              private authGuard: AuthGuard, private router: Router) { }

  ngOnInit() {
    this.logger.info('NavComponent: ngOnInit()');
    this.commandBarSidenavService.setSidenav(this.sidenav);
  }

  public isAuthenticated() {
    if (!this.authService.isLoggedIn() && !(this.router.url === '/login' || this.router.url === '/')) {
      this.authService.redirectLoginSessionExpiration();
    }
    return this.authService.isLoggedIn();
  }

  public ngOnDestroy() {
    this.logger.info('NavComponent: ngOnDestroy()');
  }

  get allowedRoutes() {
    const allowedRoutes: Array<ROUTE> = [];
    if (this.isAuthenticated()) {
      this.sidenavRoutes.forEach(route => {
        if (this.authGuard.isAllowed(route.allowedRoles)) {
          allowedRoutes.push(route);
        }
      });
    }
    return allowedRoutes;
  }
}
