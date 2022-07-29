import { Component, OnInit, AfterContentInit, QueryList, ContentChildren, ViewChild, ComponentFactoryResolver } from '@angular/core';
import { FormTabComponent } from '../form-tab/form-tab.component';
import { DynamicTabsDirective } from './dynamic-tabs.directive';
import { TranslateService } from '@ngx-translate/core';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';
import { Observable, Observer } from 'rxjs';

export interface IResponseTab {
  data: any;
  new: boolean;
  id: number;
}

@Component({
  selector: 'app-form-tabgroup',
  templateUrl: './form-tabgroup.component.html',
  styleUrls: ['./form-tabgroup.component.scss']
})
export class FormTabgroupComponent implements AfterContentInit {
  dynamicTabs: FormTabComponent[] = [];

  @ContentChildren(FormTabComponent) tabs: QueryList<FormTabComponent>;

  @ViewChild(DynamicTabsDirective) dynamicTabPlaceholder: DynamicTabsDirective;

  constructor(private componentFactoryResolver: ComponentFactoryResolver, private translate: TranslateService, private dialog: MatDialog) { }

  // contentChildren are set
  ngAfterContentInit() {
    // get all active tabs
    const activeTabs = this.tabs.filter(tab => tab.active);

    // if there is no active tab set, activate the first
    if (activeTabs.length === 0) {
      this.selectTab(this.tabs.first);
    }
  }

  openTab(title: string, template: any, data: any, isCloseable = false) {
    const tabsByDataContext = this.dynamicTabs.filter(tab => tab.dataContext === data);
    if (tabsByDataContext.length > 0) {
      this.selectTab(tabsByDataContext[0]);
    } else {
      // get a component factory for our TabComponent
      const componentFactory = this.componentFactoryResolver.resolveComponentFactory(
        FormTabComponent
      );

      // fetch the view container reference from our anchor directive
      const viewContainerRef = this.dynamicTabPlaceholder.viewContainer;

      // create a component instance
      const componentRef = viewContainerRef.createComponent(componentFactory);

      // set the according properties on our component instance
      const instance: FormTabComponent = componentRef.instance;
      instance.title = title;
      instance.template = template;
      instance.dataContext = data;
      instance.isCloseable = isCloseable;
      instance.index = this.dynamicTabs.length;

      this.dynamicTabs.push(componentRef.instance);

      // set it active
      this.selectTab(this.dynamicTabs[this.dynamicTabs.length - 1]);
    }
  }

  selectTab(tab: FormTabComponent) {
    // deactivate all tabs
    this.tabs.toArray().forEach(tabTemp => (tabTemp.active = false));
    this.dynamicTabs.forEach(tabTemp => (tabTemp.active = false));

    // activate the tab the user has clicked on.
    tab.active = true;
  }

  closeTab(tab: FormTabComponent): Observable<boolean> {
    if (tab && tab.modified && tab.cancel) {
      const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
        width: '350px',
        data: this.translate.instant('unsaved-changes-lost-confirmation')
      });
      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          this.doCloseTab(tab);
          return new Observable((observer: Observer<boolean>) => observer.next(true));
        } else {
          return new Observable((observer: Observer<boolean>) => observer.next(false));
        }
      });
    } else {
      this.doCloseTab(tab);
      return new Observable((observer: Observer<boolean>) => observer.next(true));
    }
  }

  doCloseTab(tab: FormTabComponent) {
    for (let i = 0; i < this.dynamicTabs.length; i++) {
      if (this.dynamicTabs[i] === tab) {
        const activeTab = this.getActiveTab();

        // remove the tab from our array
        this.dynamicTabs.splice(i, 1);

        // destroy our dynamically created component again
        const viewContainerRef = this.dynamicTabPlaceholder.viewContainer;
        viewContainerRef.remove(i);

        if (this.dynamicTabs.length > 0 && activeTab) {
          // set tab index to last dynamic tab opened
          this.selectTab(this.dynamicTabs[this.dynamicTabs.length - 1]);
        } else {
          // set tab index to 1st one
          this.selectTab(this.tabs.first);
        }
        break;
      }
    }
  }

  closeActiveTab(): Observable<boolean> {
    const activeTabs = this.dynamicTabs.filter(tab => tab.active);
    if (activeTabs.length > 0) {
      // close the 1st active tab (should only be one at a time)
      return this.closeTab(activeTabs[0]);
    } else {
      return new Observable((observer: Observer<boolean>) => observer.next(true));
    }
  }

  closeTabByDataContext(dataContext: any): Observable<boolean> {
    const tabsByDataContext = this.dynamicTabs.filter(tab => tab.dataContext === dataContext);
    if (tabsByDataContext.length > 0) {
      // close the 1st tab with the same datacontext (should only be one at a time)
      return this.closeTab(tabsByDataContext[0]);
    } else {
      return new Observable((observer: Observer<boolean>) => observer.next(true));
    }
  }

  getActiveTab(): FormTabComponent {
    const activeTabs = this.dynamicTabs.filter(tab => tab.active);
    if (activeTabs.length > 0) {
      return activeTabs[0];
    }
  }

  onModifiedData() {
    const activeTab = this.getActiveTab();
    if (activeTab && !activeTab.modified) {
      activeTab.modified = true;
    }
  }
}
