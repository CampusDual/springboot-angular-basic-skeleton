import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from '../main/main-routing.module';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatBadgeModule } from '@angular/material/badge';

import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { NavComponent } from './components/nav/nav.component';
import { FlexLayoutModule } from '@angular/flex-layout';

import { TranslateModule } from '@ngx-translate/core';
import { FormTabgroupComponent } from './components/form-tabgroup/form-tabgroup.component';
import { FormTabComponent } from './components/form-tab/form-tab.component';
import { DynamicTabsDirective } from './components/form-tabgroup/dynamic-tabs.directive';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    NavigationBarComponent,
    NavComponent,
    FormTabgroupComponent,
    FormTabComponent,
    DynamicTabsDirective],
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
    MatToolbarModule,
    MatListModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSortModule,
    MatTableModule,
    MatCardModule,
    MatExpansionModule,
    MatMenuModule,
    FlexLayoutModule,
    MatBadgeModule,
    BrowserModule,
    ReactiveFormsModule
  ],
  exports: [
    NavigationBarComponent,
    NavComponent,
    FormTabgroupComponent,
    FormTabComponent
  ],
  entryComponents: [FormTabComponent]
})
export class CoreModule { }
