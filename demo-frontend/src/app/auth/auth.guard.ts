import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router, private authService: AuthService) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    const url: string = state.url;
    const allowedRoles = next.data['allowedRoles'];

    return this.checkLogin(url, allowedRoles);
  }

  checkLogin(url: string, allowedRoles: Array<string>): boolean {
    if (this.authService.isLoggedIn() && this.isAllowed(allowedRoles)) {
      return true;
    }

    // Store the attempted URL for redirecting
    this.authService.redirectUrl = url;

    // Navigate to the login page with extras
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/main']);
    } else {
      this.router.navigate(['/login']);
    }
    return false;
  }

  isAllowed(allowedRoles: Array<string>): boolean {
    return this.authService.getRoles().some(authoritie => allowedRoles.indexOf(authoritie) > -1);
  }
}
