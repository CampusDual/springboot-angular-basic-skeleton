import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { MainHomeComponent } from './main-home/main-home.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatFormFieldModule } from '@angular/material/form-field';
// import { ProfilesComponent } from './profiles/profiles.component';

import { TranslateModule } from '@ngx-translate/core';
// import { UsersComponent } from './users/users.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { CreateUserComponent } from './users/create-user/create-user.component';
import { FlexLayoutModule } from '@angular/flex-layout';
// import { CreateProfileComponent } from './profiles/create-profile/create-profile.component';

// import { PdfJsViewerModule } from 'ng2-pdfjs-viewer'; // <-- Import PdfJsViewerModule module
import { AppComponent } from '../app.component';
// import { FormTabComponent } from '../core/components/form-tab/form-tab.component';
import { BrowserModule } from '@angular/platform-browser';
// import { SignaturesComponent } from './signatures/signatures.component';
// import { EventsComponent } from './events/events.component';
import { LightboxModule } from 'ngx-lightbox';
import { CoreModule } from '../core/core.module';
import { ContactsComponent } from './contacts/contacts.component';
import { FilterItemDirective } from './directives/filter-item.directive';
import { EditContactComponent } from './contacts/edit-contact/edit-contact.component';
// import { ShowHidePasswordModule } from 'ngx-show-hide-password';

@NgModule({
  declarations: [
    MainHomeComponent,
    // ContactsComponent,
    // CreateContactComponent,
    // ProfilesComponent,
    // UsersComponent,
    // CreateUserComponent,
    // CreateProfileComponent,
    FilterItemDirective,
  ],
  imports: [
    TranslateModule,
    CommonModule,
    MainRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule,
    MatTabsModule,
    MatSidenavModule,
    MatTooltipModule,
    MatDialogModule,
    MatToolbarModule,
    MatListModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSortModule,
    MatTableModule,
    MatCardModule,
    MatExpansionModule,
    MatCheckboxModule,
    FormsModule,
    BrowserModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MatChipsModule,
    // PdfJsViewerModule,
    CoreModule,
    MatMenuModule,
    MatNativeDateModule,
    MatDatepickerModule,
    LightboxModule,
    // ShowHidePasswordModule,
  ],
  bootstrap: [AppComponent],
  providers: [{ provide: MAT_DATE_LOCALE, useValue: 'es-ES' }, DatePipe],
})
export class MainModule {}
