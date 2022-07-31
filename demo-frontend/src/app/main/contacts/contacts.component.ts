import {
  Component,
  OnInit,
  AfterViewInit,
  ViewChild,
  ElementRef,
} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, fromEvent } from 'rxjs';
import { tap, debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { SelectionModel } from '@angular/cdk/collections';
import { AnyPageFilter, AnyField, SortFilter } from 'src/app/model/rest/filter';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBarComponent } from 'src/app/components/mat-snack-bar/mat-snack-bar.component';
import { ContactDataSource } from '../../model/datasource/contacts.datasource';
import { Contact } from '../../model/contact';
import { ContactService } from '../../services/contact.service';
import { Router } from '@angular/router';

@Component({
  // selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.scss'],
})
export class ContactsComponent implements OnInit, AfterViewInit {
  dataSource: ContactDataSource;
  displayedColumns = [
    'select',
    'name',
    'surname1',
    'surname2',
    'phone',
    'email',
  ];
  fields = ['name', 'surname1', 'surname2', 'phone', 'email'];

  selection = new SelectionModel<Contact>(true, []);
  error = false;

  @ViewChild('edit') editTemplate: any;
  highlightedRow: Contact;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('input') input: ElementRef;

  constructor(
    private contactService: ContactService,
    private snackBar: MatSnackBarComponent,
    private translate: TranslateService,
    private router: Router
  ) {}

  ngOnInit() {
    this.dataSource = new ContactDataSource(this.contactService);
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      20,
      'name'
    );
    this.dataSource.getContacts(pageFilter);
  }

  ngAfterViewInit(): void {
    // server-side search
    fromEvent(this.input.nativeElement, 'keyup')
      .pipe(
        debounceTime(150),
        distinctUntilChanged(),
        tap(() => {
          this.paginator.pageIndex = 0;
          this.loadContactsPage();
        })
      )
      .subscribe();

    // reset the paginator after sorting
    this.sort.sortChange.subscribe(() => {
      this.paginator.pageIndex = 0;
      this.selection.clear();
    });

    // on sort or paginate events, load a new page
    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        tap(() => {
          this.loadContactsPage();
        })
      )
      .subscribe();
  }

  loadContactsPage() {
    this.selection.clear();
    this.error = false;
    const pageFilter = new AnyPageFilter(
      this.input.nativeElement.value,
      this.fields.map((field) => new AnyField(field)),
      this.paginator.pageIndex,
      this.paginator.pageSize
    );
    pageFilter.order = [];
    pageFilter.order.push(
      new SortFilter(this.sort.active, this.sort.direction.toString())
    );
    this.dataSource.getContacts(pageFilter);
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.contactsSubject.value.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.dataSource.contactsSubject.value.forEach((row) =>
          this.selection.select(row)
        );
  }

  delete() {
    const contact = this.selection.selected[0];
    this.selection.deselect(contact);
    if (this.selection.selected && this.selection.selected.length === 0) {
      this.contactService.deleteContact(contact.id).subscribe((response) => {
        if (response.responseCode !== 'OK') {
          this.error = true;
        } else {
          this.loadContactsPage();
        }
      });
    } else {
      this.contactService.deleteContact(contact.id).subscribe((response) => {
        if (response.responseCode !== 'OK') {
          this.error = true;
        }
        this.delete();
      });
    }
  }

  onAdd() {
    this.router.navigate(['/contacts/add']);
  }

  onEdit(row: Contact) {
    this.highlightedRow = row;
    this.router.navigate(['/contacts/edit/' + row.id]);
  }
}
