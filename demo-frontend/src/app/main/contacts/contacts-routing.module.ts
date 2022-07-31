import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContactsLayoutComponent } from './contacts-layout.component';
import { ContactsComponent } from './contacts.component';
import { EditContactComponent } from './edit-contact/edit-contact.component';


const routes: Routes = [
  {
    path: '',
    component: ContactsLayoutComponent,
    children: [
      { path: "", component: ContactsComponent },
      { path: 'add', component: EditContactComponent },
      { path: 'edit/:id', component: EditContactComponent },
    ],
  },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ContactsRoutingModule { }
