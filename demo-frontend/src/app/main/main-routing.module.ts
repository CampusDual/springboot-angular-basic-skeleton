import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainHomeComponent } from './main-home/main-home.component';
import { AuthGuard } from '../auth/auth.guard';
// import { ProfilesComponent } from './profiles/profiles.component';
// import { UsersComponent } from './userCs/users.component';

const routes: Routes = [
  {
    path: 'main',
    component: MainHomeComponent,
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['PROFILES', 'USERS', 'CONTACTS']
    }
  },
  // {
  //   path: 'profiles',
  //   component: ProfilesComponent,
  //   canActivate: [AuthGuard],
  //   data: {
  //     allowedRoles: ['PROFILES']
  //   }
  // },
  // {
  //   path: 'users',
  //   component: UsersComponent,
  //   canActivate: [AuthGuard],
  //   data: {
  //     allowedRoles: ['USERS']
  //   }
  // },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
