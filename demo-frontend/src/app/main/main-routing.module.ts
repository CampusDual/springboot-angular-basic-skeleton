import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainHomeComponent } from './main-home/main-home.component';
import { AuthGuard } from '../auth/auth.guard';
import { ContactsComponent } from './contacts/contacts.component';
// import { ProfilesComponent } from './profiles/profiles.component';
// import { UsersComponent } from './userCs/users.component';

const contactsModule = () =>
  import('./contacts/contacts.module').then((x) => x.ContactsModule);

const routes: Routes = [
  {
    path: 'main',
    component: MainHomeComponent,
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['PROFILES', 'USERS', 'CONTACTS'],
    },
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
  {
    path: 'contacts',
    component: ContactsComponent,
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['CONTACTS'],
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainRoutingModule {}
