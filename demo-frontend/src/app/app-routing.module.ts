import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent,  pathMatch: 'full'},
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'contacts', loadChildren: () => import('./main/contacts/contacts.module').then(x => x.ContactsModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
